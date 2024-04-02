package com.soldiersoft.traveler.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soldiersoft.traveler.entity.Ticket;
import com.soldiersoft.traveler.exception.BizException;
import com.soldiersoft.traveler.mapper.TicketMapper;
import com.soldiersoft.traveler.model.dto.AttractionDTO;
import com.soldiersoft.traveler.model.dto.AttractionTicketDTO;
import com.soldiersoft.traveler.model.dto.TicketDTO;
import com.soldiersoft.traveler.model.vo.TicketVO;
import com.soldiersoft.traveler.service.AttractionService;
import com.soldiersoft.traveler.service.AttractionTicketService;
import com.soldiersoft.traveler.service.TicketService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

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

    @Lazy
    @Autowired
    public TicketServiceImpl(AttractionService attractionService, AttractionTicketService attractionTicketService) {
        this.attractionService = attractionService;
        this.attractionTicketService = attractionTicketService;
    }

    @Override
    public TicketDTO saveTicketFromAttractionTicket(AttractionTicketDTO attractionTicketDTO) {
        try {
            Ticket ticket = attractionTicketDTO.getTicket();
            save(ticket);
            TicketDTO ticketDTO = new TicketDTO();
            BeanUtils.copyProperties(ticket, ticketDTO);
            return ticketDTO;
        } catch (BizException e) {
            throw new BizException("保存门票失败，请联系管理员");
        }
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
}




