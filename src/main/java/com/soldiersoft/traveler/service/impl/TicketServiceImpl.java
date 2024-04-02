package com.soldiersoft.traveler.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soldiersoft.traveler.entity.AttractionTicket;
import com.soldiersoft.traveler.entity.Ticket;
import com.soldiersoft.traveler.exception.BizException;
import com.soldiersoft.traveler.mapper.TicketMapper;
import com.soldiersoft.traveler.model.dto.AttractionDTO;
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
 * @description 针对表【t_ticket(门票表)】的数据库操作Service实现
 * @createDate 2024-04-01 21:06:21
 */
@Service
public class TicketServiceImpl extends ServiceImpl<TicketMapper, Ticket>
        implements TicketService {
    private final AttractionService attractionService;
    private final AttractionTicketService attractionTicketService;

    @Autowired
    public TicketServiceImpl(AttractionService attractionService, AttractionTicketService attractionTicketService) {
        this.attractionService = attractionService;
        this.attractionTicketService = attractionTicketService;
    }

    @Override
    public List<TicketVO> getTicketsByAttractionId(Long attractionId, String username) {
        Map<Long, AttractionDTO> map = attractionService.getAttractionsMapByUsername(username);
        if (!map.containsKey(attractionId)) {
            return null;
        }
        return attractionTicketService.getAttractionTicketsByAttractionId(attractionId).stream()
                .map(dto -> {
                    TicketVO ticketVO = new TicketVO();
                    BeanUtils.copyProperties(dto.getTicket(), ticketVO);
                    return ticketVO;
                }).toList();
    }

    @Override
    @Transactional
    public String publishTicket(AttractionTicketVO attractionTicketVO, String username) {
        Map<Long, AttractionDTO> map = attractionService.getAttractionsMapByUsername(username);
        AttractionDTO attractionDTO = map.get(attractionTicketVO.getAttractionVO().getId());
        if (attractionDTO == null) {
            throw new BizException("景点不存在");
        }
        TicketVO ticketVO = attractionTicketVO.getTicketVO();
        Ticket ticket = new Ticket();
        BeanUtils.copyProperties(ticketVO, ticket);
        save(ticket);
        AttractionTicket attractionTicket = AttractionTicket.builder()
                .attractionId(attractionTicketVO.getAttractionVO().getId())
                .ticketId(ticket.getId())
                .build();
        attractionTicketService.save(attractionTicket);
        return "门票发布成功";
    }

    @Override
    public String updateTicket(AttractionTicketVO attractionTicketVO, String username) {
        Map<Long, AttractionDTO> map = attractionService.getAttractionsMapByUsername(username);
        AttractionDTO attractionDTO = map.get(attractionTicketVO.getAttractionVO().getId());
        if (attractionDTO == null) {
            throw new BizException("景点不存在");
        }
        TicketVO ticketVO = attractionTicketVO.getTicketVO();
        Ticket ticket = new Ticket();
        BeanUtils.copyProperties(ticketVO, ticket);
        updateById(ticket);
        return "门票更新成功";
    }
}




