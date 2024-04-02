package com.soldiersoft.traveler.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.soldiersoft.traveler.entity.Attraction;
import com.soldiersoft.traveler.entity.AttractionTicket;
import com.soldiersoft.traveler.entity.Ticket;
import com.soldiersoft.traveler.exception.BizException;
import com.soldiersoft.traveler.mapper.AttractionTicketMapper;
import com.soldiersoft.traveler.model.dto.AttractionDTO;
import com.soldiersoft.traveler.model.dto.AttractionTicketDTO;
import com.soldiersoft.traveler.model.dto.TicketDTO;
import com.soldiersoft.traveler.model.vo.AttractionTicketVO;
import com.soldiersoft.traveler.model.vo.TicketVO;
import com.soldiersoft.traveler.service.AttractionService;
import com.soldiersoft.traveler.service.AttractionTicketService;
import com.soldiersoft.traveler.service.TicketService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author Soldier_RMB
 * @description 针对表【t_attraction_ticket(景点门票表)】的数据库操作Service实现
 * @createDate 2024-04-01 21:06:21
 */
@Service
public class AttractionTicketServiceImpl extends ServiceImpl<AttractionTicketMapper, AttractionTicket>
        implements AttractionTicketService {
    private final AttractionService attractionService;
    private final TicketService ticketService;
    private final AttractionTicketMapper attractionTicketMapper;

    @Autowired
    public AttractionTicketServiceImpl(AttractionService attractionService, TicketService ticketService, AttractionTicketMapper attractionTicketMapper) {
        this.attractionService = attractionService;
        this.ticketService = ticketService;
        this.attractionTicketMapper = attractionTicketMapper;
    }

    @Override
    @Transactional
    public String publishAttractionTicket(AttractionTicketVO attractionTicketVO, String username) {
        Map<Long, AttractionDTO> map = attractionService.getAttractionsMapByUsername(username);
        AttractionDTO attractionDTO = map.get(attractionTicketVO.getAttractionVO().getId());
        if (attractionDTO == null) {
            throw new BizException("景点不存在");
        }
        TicketVO ticketVO = attractionTicketVO.getTicketVO();
        Ticket ticket = new Ticket();
        BeanUtils.copyProperties(ticketVO, ticket);
        AttractionTicketDTO attractionTicketDTO = AttractionTicketDTO.builder()
                .ticket(ticket)
                .build();
        TicketDTO ticketDTO = ticketService.saveTicketFromAttractionTicket(attractionTicketDTO);
        AttractionTicket attractionTicket = AttractionTicket.builder()
                .attractionId(attractionDTO.getId())
                .ticketId(ticketDTO.getId())
                .build();
        save(attractionTicket);
        return "景点门票发布成功";
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




