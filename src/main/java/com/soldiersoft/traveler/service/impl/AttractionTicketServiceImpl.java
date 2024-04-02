package com.soldiersoft.traveler.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.soldiersoft.traveler.entity.Attraction;
import com.soldiersoft.traveler.entity.AttractionTicket;
import com.soldiersoft.traveler.entity.Ticket;
import com.soldiersoft.traveler.mapper.AttractionTicketMapper;
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

    @Autowired
    public AttractionTicketServiceImpl(AttractionTicketMapper attractionTicketMapper) {
        this.attractionTicketMapper = attractionTicketMapper;
    }

    @Override
    public List<AttractionTicketDTO> getAttractionTicketsByAttractionId(Long attractionId) {
        MPJLambdaWrapper<AttractionTicket> wrapper = new MPJLambdaWrapper<>(AttractionTicket.class)
                .selectAll(AttractionTicket.class)
                .selectAssociation(Attraction.class, AttractionTicketDTO::getAttraction)
                .selectAssociation(Ticket.class, AttractionTicketDTO::getTicket)
                .leftJoin(Attraction.class, Attraction::getId, AttractionTicket::getAttractionId)
                .leftJoin(Ticket.class, Ticket::getId, AttractionTicket::getTicketId)
                .eq(AttractionTicket::getAttractionId, attractionId);
        return attractionTicketMapper.selectJoinList(AttractionTicketDTO.class, wrapper);
    }
}




