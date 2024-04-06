package com.soldiersoft.traveler.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soldiersoft.traveler.entity.Order;
import com.soldiersoft.traveler.entity.Ticket;
import com.soldiersoft.traveler.mapper.OrderMapper;
import com.soldiersoft.traveler.model.dto.UserDTO;
import com.soldiersoft.traveler.model.vo.OrderVO;
import com.soldiersoft.traveler.service.OrderService;
import com.soldiersoft.traveler.service.TicketService;
import com.soldiersoft.traveler.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author Soldier_RMB
 * @description 针对表【t_order(订单表)】的数据库操作Service实现
 * @createDate 2024-04-01 21:06:21
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
        implements OrderService {
    private final UserService userService;
    private final TicketService ticketService;

    @Autowired
    public OrderServiceImpl(UserService userService, TicketService ticketService) {
        this.userService = userService;
        this.ticketService = ticketService;
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
}




