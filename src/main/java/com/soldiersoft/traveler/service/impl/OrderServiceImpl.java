package com.soldiersoft.traveler.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soldiersoft.traveler.entity.Order;
import com.soldiersoft.traveler.mapper.OrderMapper;
import com.soldiersoft.traveler.service.OrderService;
import org.springframework.stereotype.Service;

/**
* @author Soldier_RMB
* @description 针对表【t_order(订单表)】的数据库操作Service实现
* @createDate 2024-04-01 21:06:21
*/
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
    implements OrderService{

}




