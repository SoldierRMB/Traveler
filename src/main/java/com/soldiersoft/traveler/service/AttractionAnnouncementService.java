package com.soldiersoft.traveler.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.soldiersoft.traveler.entity.AttractionAnnouncement;
import com.soldiersoft.traveler.model.dto.AttractionAnnouncementDTO;
import com.soldiersoft.traveler.model.vo.AttractionAnnouncementVO;

/**
 * @author Soldier_RMB
 * @description 针对表【t_attraction_announcement(景点公告表)】的数据库操作Service
 * @createDate 2024-04-29 16:20:40
 */
public interface AttractionAnnouncementService extends IService<AttractionAnnouncement> {

    String publishAttractionAnnouncement(AttractionAnnouncementVO attractionAnnouncementVO, String username);

    Page<AttractionAnnouncementDTO> getAttractionAnnouncementsByAttractionId(Long attractionId, Long current, Long size);

    String updateAttractionAnnouncement(AttractionAnnouncementVO attractionAnnouncementVO, String username);

    String completeDeleteAttractionAnnouncement(Long attractionAnnouncementId, Long attractionId, String username);

    Page<AttractionAnnouncementDTO> getAttractionAnnouncementsByUsername(Long current, Long size, String username);
}
