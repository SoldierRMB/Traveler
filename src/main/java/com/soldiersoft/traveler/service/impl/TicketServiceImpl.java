package com.soldiersoft.traveler.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soldiersoft.traveler.entity.Ticket;
import com.soldiersoft.traveler.mapper.TicketMapper;
import com.soldiersoft.traveler.service.TicketService;
import org.springframework.stereotype.Service;

/**
* @author Soldier_RMB
* @description 针对表【t_ticket(门票表)】的数据库操作Service实现
* @createDate 2024-04-01 21:06:21
*/
@Service
public class TicketServiceImpl extends ServiceImpl<TicketMapper, Ticket>
    implements TicketService{

}




