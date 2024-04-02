package com.soldiersoft.traveler.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.soldiersoft.traveler.entity.Ticket;
import com.soldiersoft.traveler.model.vo.AttractionTicketVO;
import com.soldiersoft.traveler.model.vo.TicketVO;

import java.util.List;

/**
* @author Soldier_RMB
* @description 针对表【t_ticket(门票表)】的数据库操作Service
* @createDate 2024-04-01 21:06:21
*/
public interface TicketService extends IService<Ticket> {

    List<TicketVO> getTicketsByAttractionId(Long attractionId, String username);

    String publishTicket(AttractionTicketVO attractionTicketVO, String username);

    String updateTicket(AttractionTicketVO attractionTicketVO, String username);
}
