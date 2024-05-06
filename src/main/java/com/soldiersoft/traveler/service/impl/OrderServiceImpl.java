package com.soldiersoft.traveler.service.impl;

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
import java.util.stream.Collectors;

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
    public String completePayment(Long orderId, String username) {
        Map<Long, OrderDTO> map = getOrdersMapByUsername(username);
        if (!map.containsKey(orderId)) {
            throw new BizException("订单不存在");
        }
        return updateOrderStatus(orderId, 2) > 0 ? "订单支付成功" : "订单支付失败";
    }

    @Override
    public Page<OrderDTO> getUserOrders(String username, Long current, Long size) {
        MPJLambdaWrapper<Order> wrapper = getOrderMPJLambdaWrapper()
                .eq(User::getUsername, username)
                .ne(Order::getStatus, 6);
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
    public String cancelOrder(Long orderId, String username) {
        Map<Long, OrderDTO> map = getOrdersMapByUsername(username);
        if (!map.containsKey(orderId)) {
            throw new BizException("订单不存在");
        }
        return updateOrderStatus(orderId, 5) > 0 ? "订单取消成功" : "订单取消失败";
    }

    @Override
    public String useTicket(Long orderId, String username) {
        MPJLambdaWrapper<Order> wrapper = getOrderMPJLambdaWrapper()
                .eq(User::getUsername, username);
        AttractionDTO attractionDTO = attractionService.getAttractionByOrderId(orderId);
        Map<Long, AttractionDTO> map = attractionService.getAttractionsMapByUsername(username);
        if (!map.containsKey(attractionDTO.getId())) {
            throw new BizException("景点不存在");
        }
        return Optional.ofNullable(orderMapper.selectJoinList(OrderDTO.class, wrapper))
                .map(orderDTO -> {
                            int rows = updateOrderStatus(orderId, 3);
                            if (rows > 0) {
                                return "门票使用成功";
                            } else {
                                throw new BizException("门票使用失败");
                            }
                        }
                )
                .orElseThrow(() -> new BizException("订单不存在"));
    }

    @Override
    public Map<Long, OrderDTO> getOrdersMapByUsername(String username) {
        MPJLambdaWrapper<Order> wrapper = getOrderMPJLambdaWrapper()
                .eq(User::getUsername, username);
        return orderMapper.selectJoinList(OrderDTO.class, wrapper).stream()
                .peek(orderDTO -> {
                    User user = orderDTO.getUser();
                    user.setPassword(null);
                    user.setIsDisable(null);
                    orderDTO.setUser(user);
                })
                .collect(Collectors.toMap(OrderDTO::getId, orderDTO -> orderDTO));
    }

    @Override
    public String deleteOrder(Long orderId, String username) {
        Map<Long, OrderDTO> map = getOrdersMapByUsername(username);
        if (!map.containsKey(orderId)) {
            throw new BizException("订单不存在");
        }
        return updateOrderStatus(orderId, 6) > 0 ? "删除订单成功" : "删除订单失败";
    }

    private Page<OrderDTO> getOrderDTOPage(Long current, Long size) {
        MPJLambdaWrapper<Order> wrapper = getOrderMPJLambdaWrapper();
        return orderMapper.selectJoinPage(new Page<>(current, size), OrderDTO.class, wrapper);
    }

    private Page<OrderDTO> processOrderDTOPage(Page<OrderDTO> orderDTOPage, Long attractionId, String username) {
        List<OrderDTO> records = orderDTOPage.getRecords().stream()
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
        orderDTOPage.setRecords(records);
        return orderDTOPage;
    }

    private int updateOrderStatus(Long orderId, Integer status) {
        Order order = lambdaQuery()
                .eq(Order::getId, orderId)
                .one();
        order.setStatus(status);
        return orderMapper.updateById(order);
    }
}
