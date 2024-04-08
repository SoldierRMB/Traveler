package com.soldiersoft.traveler.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.soldiersoft.traveler.entity.Order;
import com.soldiersoft.traveler.model.vo.OrderTicketVO;
import com.soldiersoft.traveler.model.vo.OrderVO;

import java.util.List;

/**
* @author Soldier_RMB
* @description 针对表【t_order(订单表)】的数据库操作Service
* @createDate 2024-04-01 21:06:21
*/
public interface OrderService extends IService<Order> {

    OrderVO booking(OrderVO orderVO, String username);

    OrderVO completePayment(Long orderId, String username);

    List<OrderTicketVO> getUserOrders(String username);

    List<OrderTicketVO> getOrdersByAttractionId(Long attractionId, String username);

    List<OrderTicketVO> getStaffOrders(String username);
}
