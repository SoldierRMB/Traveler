package com.soldiersoft.traveler.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soldiersoft.traveler.entity.Announcement;
import com.soldiersoft.traveler.exception.BizException;
import com.soldiersoft.traveler.mapper.AnnouncementMapper;
import com.soldiersoft.traveler.model.vo.AnnouncementVO;
import com.soldiersoft.traveler.service.AnnouncementService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Soldier_RMB
 * @description 针对表【t_announcement(公告表)】的数据库操作Service实现
 * @createDate 2024-04-29 16:20:40
 */
@Service
public class AnnouncementServiceImpl extends ServiceImpl<AnnouncementMapper, Announcement>
        implements AnnouncementService {
    private final AnnouncementMapper announcementMapper;

    @Autowired
    public AnnouncementServiceImpl(AnnouncementMapper announcementMapper) {
        this.announcementMapper = announcementMapper;
    }

    @Override
    public Page<Announcement> getAnnouncements(Long current, Long size) {
        return announcementMapper.selectPage(new Page<>(current, size), null);
    }

    @Override
    public String publishAnnouncement(AnnouncementVO announcementVO) {
        try {
            Announcement announcement = new Announcement();
            BeanUtils.copyProperties(announcementVO, announcement);
            save(announcement);
        } catch (Exception e) {
            throw new BizException("系统公告发布失败");
        }
        return "系统公告发布成功";
    }

    @Override
    public String updateAnnouncement(AnnouncementVO announcementVO) {
        try {
            Announcement announcement = new Announcement();
            BeanUtils.copyProperties(announcementVO, announcement);
            updateById(announcement);
        } catch (Exception e) {
            throw new BizException("系统公告更新失败");
        }
        return "系统公告更新成功";
    }

    @Override
    public String completeDeleteAnnouncement(Long announcementId) {
        return announcementMapper.deleteById(announcementId) > 0 ? "系统公告删除成功" : "系统公告删除失败";
    }
}




