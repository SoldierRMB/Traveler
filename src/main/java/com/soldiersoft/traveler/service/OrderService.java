package com.soldiersoft.traveler.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.soldiersoft.traveler.entity.Order;
import com.soldiersoft.traveler.model.dto.OrderDTO;
import com.soldiersoft.traveler.model.vo.OrderVO;

/**
 * @author Soldier_RMB
 * @description 针对表【t_order(订单表)】的数据库操作Service
 * @createDate 2024-04-01 21:06:21
 */
public interface OrderService extends IService<Order> {

    OrderVO booking(OrderVO orderVO, String username);

    OrderVO completePayment(Long orderId, String username);

    Page<OrderDTO> getUserOrders(String username, Long current, Long size);

    Page<OrderDTO> getOrdersByAttractionId(Long attractionId, String username, Long current, Long size);

    Page<OrderDTO> getAllOrders(Long current, Long size);
}
