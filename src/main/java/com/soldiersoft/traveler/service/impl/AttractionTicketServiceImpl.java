package com.soldiersoft.traveler.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.soldiersoft.traveler.entity.Attraction;
import com.soldiersoft.traveler.entity.AttractionTicket;
import com.soldiersoft.traveler.entity.Order;
import com.soldiersoft.traveler.entity.Ticket;
import com.soldiersoft.traveler.mapper.AttractionTicketMapper;
import com.soldiersoft.traveler.mapper.OrderMapper;
import com.soldiersoft.traveler.mapper.TicketMapper;
import com.soldiersoft.traveler.model.dto.AttractionTicketDTO;
import com.soldiersoft.traveler.service.AttractionTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Soldier_RMB
 * @description 针对表【t_attraction_ticket(景点门票表)】的数据库操作Service实现
 * @createDate 2024-04-01 21:06:21
 */
@Service
public class AttractionTicketServiceImpl extends ServiceImpl<AttractionTicketMapper, AttractionTicket>
        implements AttractionTicketService {
    private final AttractionTicketMapper attractionTicketMapper;
    private final TicketMapper ticketMapper;
    private final OrderMapper orderMapper;

    @Autowired
    public AttractionTicketServiceImpl(AttractionTicketMapper attractionTicketMapper, TicketMapper ticketMapper, OrderMapper orderMapper) {
        this.attractionTicketMapper = attractionTicketMapper;
        this.ticketMapper = ticketMapper;
        this.orderMapper = orderMapper;
    }

    @Override
    public List<AttractionTicketDTO> getAttractionTicketsByAttractionId(Long attractionId) {
        MPJLambdaWrapper<AttractionTicket> wrapper = new MPJLambdaWrapper<>(AttractionTicket.class)
                .selectAll(AttractionTicket.class)
                .selectAssociation(Attraction.class, AttractionTicketDTO::getAttraction)
                .selectAssociation(Ticket.class, AttractionTicketDTO::getTicket)
                .leftJoin(Attraction.class, Attraction::getId, AttractionTicket::getAttractionId)
                .leftJoin(Ticket.class, Ticket::getId, AttractionTicket::getTicketId)
                .eq(Ticket::getIsDeleted, 0)
                .eq(AttractionTicket::getAttractionId, attractionId);
        return attractionTicketMapper.selectJoinList(AttractionTicketDTO.class, wrapper);
    }

    @Override
    public int deleteAttractionTicketsByAttractionId(Long attractionId) {
        return getAttractionTicketsByAttractionId(attractionId).stream()
                .mapToInt(attractionTicketDTO -> {
                    remove(new LambdaQueryWrapper<AttractionTicket>().eq(AttractionTicket::getAttractionId, attractionId));
                    Long ticketId = attractionTicketDTO.getTicket().getId();
                    orderMapper.delete(new LambdaQueryWrapper<Order>().eq(Order::getTicketId, ticketId));
                    return ticketMapper.delete(new LambdaQueryWrapper<Ticket>().eq(Ticket::getId, ticketId));
                }).sum();
    }
}




