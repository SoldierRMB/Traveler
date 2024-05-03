package com.soldiersoft.traveler.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.soldiersoft.traveler.entity.Announcement;
import com.soldiersoft.traveler.model.vo.AnnouncementVO;

/**
 * @author Soldier_RMB
 * @description 针对表【t_announcement(公告表)】的数据库操作Service
 * @createDate 2024-04-29 16:20:40
 */
public interface AnnouncementService extends IService<Announcement> {

    Page<Announcement> getAnnouncements(Long current, Long size);

    String publishAnnouncement(AnnouncementVO announcementVO);

    String updateAnnouncement(AnnouncementVO announcementVO);

    String completeDeleteAnnouncement(Long announcementId);
}
