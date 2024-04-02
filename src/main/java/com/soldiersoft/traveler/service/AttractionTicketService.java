package com.soldiersoft.traveler.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.soldiersoft.traveler.entity.AttractionTicket;
import com.soldiersoft.traveler.model.dto.AttractionTicketDTO;
import com.soldiersoft.traveler.model.vo.AttractionTicketVO;

import java.util.List;

/**
* @author Soldier_RMB
* @description 针对表【t_attraction_ticket(景点门票表)】的数据库操作Service
* @createDate 2024-04-01 21:06:21
*/
public interface AttractionTicketService extends IService<AttractionTicket> {

    String publishAttractionTicket(AttractionTicketVO attractionTicketVO, String username);

    List<AttractionTicketDTO> getAttractionTicketsByAttractionId(Long attractionId);
}
