package com.soldiersoft.traveler.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.soldiersoft.traveler.entity.Order;
import com.soldiersoft.traveler.entity.Ticket;
import com.soldiersoft.traveler.entity.User;
import com.soldiersoft.traveler.exception.BizException;
import com.soldiersoft.traveler.mapper.OrderMapper;
import com.soldiersoft.traveler.model.dto.AttractionDTO;
import com.soldiersoft.traveler.model.dto.OrderDTO;
import com.soldiersoft.traveler.model.dto.UserDTO;
import com.soldiersoft.traveler.model.vo.OrderVO;
import com.soldiersoft.traveler.model.vo.TicketVO;
import com.soldiersoft.traveler.service.AttractionService;
import com.soldiersoft.traveler.service.OrderService;
import com.soldiersoft.traveler.service.TicketService;
import com.soldiersoft.traveler.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Soldier_RMB
 * @description 针对表【t_order(订单表)】的数据库操作Service实现
 * @createDate 2024-04-01 21:06:21
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
        implements OrderService {
    private final OrderMapper orderMapper;
    private final UserService userService;
    private final TicketService ticketService;
    private final AttractionService attractionService;

    @Autowired
    public OrderServiceImpl(OrderMapper orderMapper, UserService userService, TicketService ticketService, AttractionService attractionService) {
        this.orderMapper = orderMapper;
        this.userService = userService;
        this.ticketService = ticketService;
        this.attractionService = attractionService;
    }

    private static MPJLambdaWrapper<Order> getOrderMPJLambdaWrapper() {
        return new MPJLambdaWrapper<Order>()
                .selectAll(Order.class)
                .selectAssociation(User.class, OrderDTO::getUser)
                .selectAssociation(Ticket.class, OrderDTO::getTicket)
                .leftJoin(User.class, User::getId, Order::getUserId)
                .leftJoin(Ticket.class, Ticket::getId, Order::getTicketId);
    }

    @Override
    public OrderVO booking(OrderVO orderVO, String username) {
        UserDTO userDTO = userService.getUserByUsername(username);
        Ticket ticket = ticketService.lambdaQuery().eq(Ticket::getId, orderVO.getTicketId()).one();
        BigDecimal bigDecimal = new BigDecimal(orderVO.getQuantity());
        Order order = Order.builder()
                .userId(userDTO.getId())
                .ticketId(orderVO.getTicketId())
                .status(1)
                .quantity(orderVO.getQuantity())
                .amount(bigDecimal.multiply(ticket.getPrice()))
                .build();
        save(order);
        orderVO.setId(order.getId());
        return orderVO;
    }

    @Override
    public OrderVO completePayment(Long orderId, String username) {
        UserDTO userDTO = userService.getUserByUsername(username);
        Order order = lambdaQuery()
                .eq(Order::getId, orderId)
                .eq(Order::getUserId, userDTO.getId())
                .one();
        order.setStatus(2);
        updateById(order);
        return BeanUtil.copyProperties(order, OrderVO.class);
    }

    @Override
    public Page<OrderDTO> getUserOrders(String username, Long current, Long size) {
        MPJLambdaWrapper<Order> wrapper = getOrderMPJLambdaWrapper()
                .eq(User::getUsername, username);
        Page<OrderDTO> orderDTOPage = orderMapper.selectJoinPage(new Page<>(current, size), OrderDTO.class, wrapper);
        return processOrderDTOPage(orderDTOPage, null, username);
    }

    @Override
    public Page<OrderDTO> getOrdersByAttractionId(Long attractionId, String username, Long current, Long size) {
        return processOrderDTOPage(getOrderDTOPage(current, size), attractionId, username);
    }

    @Override
    public Page<OrderDTO> getAllOrders(Long current, Long size) {
        return processOrderDTOPage(getOrderDTOPage(current, size), null, null);
    }

    @Override
    public String useTicket(Long attractionId, Long orderId, String username) {
        MPJLambdaWrapper<Order> wrapper = getOrderMPJLambdaWrapper()
                .eq(User::getUsername, username);
        Map<Long, AttractionDTO> map = attractionService.getAttractionsMapByUsername(username);
        if (!map.containsKey(attractionId)) {
            throw new BizException("景点不存在");
        }
        return Optional.ofNullable(orderMapper.selectJoinList(OrderDTO.class, wrapper))
                .map(orderTO -> {
                            int rows = orderMapper.update(new LambdaUpdateWrapper<>(Order.class)
                                    .set(Order::getStatus, 3)
                                    .eq(Order::getId, orderId));
                            if (rows > 0) {
                                return "门票使用成功";
                            } else {
                                throw new BizException("门票使用失败");
                            }
                        }
                )
                .orElseThrow(() -> new BizException("订单不存在"));
    }

    private Page<OrderDTO> getOrderDTOPage(Long current, Long size) {
        MPJLambdaWrapper<Order> wrapper = getOrderMPJLambdaWrapper();
        return orderMapper.selectJoinPage(new Page<>(current, size), OrderDTO.class, wrapper);
    }

    private Page<OrderDTO> processOrderDTOPage(Page<OrderDTO> orderDTOPage, Long attractionId, String username) {
        List<OrderDTO> list = orderDTOPage.getRecords().stream()
                .filter(orderDTO -> {
                    if (attractionId != null && username != null) {
                        return ticketService.getTicketsByAttractionId(attractionId, username).stream()
                                .map(TicketVO::getId)
                                .anyMatch(id -> Objects.equals(id, orderDTO.getTicket().getId()));
                    }
                    return true;
                })
                .peek(orderDTO -> {
                    User user = orderDTO.getUser();
                    user.setPassword(null);
                    user.setIsDisable(null);
                }).toList();
        orderDTOPage.setRecords(list);
        orderDTOPage.setTotal(list.size());
        return orderDTOPage;
    }
}
