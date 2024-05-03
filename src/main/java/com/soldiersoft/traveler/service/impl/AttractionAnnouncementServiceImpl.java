package com.soldiersoft.traveler.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.soldiersoft.traveler.entity.Attraction;
import com.soldiersoft.traveler.entity.AttractionAnnouncement;
import com.soldiersoft.traveler.exception.BizException;
import com.soldiersoft.traveler.mapper.AttractionAnnouncementMapper;
import com.soldiersoft.traveler.model.dto.AttractionAnnouncementDTO;
import com.soldiersoft.traveler.model.dto.AttractionDTO;
import com.soldiersoft.traveler.model.vo.AttractionAnnouncementVO;
import com.soldiersoft.traveler.service.AttractionAnnouncementService;
import com.soldiersoft.traveler.service.AttractionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Soldier_RMB
 * @description 针对表【t_attraction_announcement(景点公告表)】的数据库操作Service实现
 * @createDate 2024-04-29 16:20:40
 */
@Service
public class AttractionAnnouncementServiceImpl extends ServiceImpl<AttractionAnnouncementMapper, AttractionAnnouncement>
        implements AttractionAnnouncementService {
    private final AttractionService attractionService;
    private final AttractionAnnouncementMapper attractionAnnouncementMapper;

    @Autowired
    public AttractionAnnouncementServiceImpl(AttractionService attractionService, AttractionAnnouncementMapper attractionAnnouncementMapper) {
        this.attractionService = attractionService;
        this.attractionAnnouncementMapper = attractionAnnouncementMapper;
    }

    @Override
    public String publishAttractionAnnouncement(AttractionAnnouncementVO attractionAnnouncementVO, String username) {
        Map<Long, AttractionDTO> map = attractionService.getAttractionsMapByUsername(username);
        if (!map.containsKey(attractionAnnouncementVO.getAttractionId())) {
            throw new BizException("景点不存在");
        }
        try {
            AttractionAnnouncement attractionAnnouncement = new AttractionAnnouncement();
            BeanUtils.copyProperties(attractionAnnouncementVO, attractionAnnouncement);
            save(attractionAnnouncement);
        } catch (Exception e) {
            throw new BizException("景点公告发布失败");
        }
        return "景点公告发布成功";
    }

    @Override
    public Page<AttractionAnnouncementDTO> getAttractionAnnouncementsByAttractionId(Long attractionId, Long current, Long size) {
        MPJLambdaWrapper<AttractionAnnouncement> wrapper = getAttractionAnnouncementMPJLambdaWrapper()
                .eq(AttractionAnnouncement::getAttractionId, attractionId);
        return null;
    }

    private static MPJLambdaWrapper<AttractionAnnouncement> getAttractionAnnouncementMPJLambdaWrapper() {
        return new MPJLambdaWrapper<>(AttractionAnnouncement.class)
                .selectAll(AttractionAnnouncement.class)
                .selectAssociation(Attraction.class, AttractionAnnouncementDTO::getAttraction)
                .leftJoin(Attraction.class, Attraction::getId, AttractionAnnouncement::getAttractionId);
    }

    @Override
    public String updateAttractionAnnouncement(AttractionAnnouncementVO attractionAnnouncementVO, String username) {
        Map<Long, AttractionDTO> map = attractionService.getAttractionsMapByUsername(username);
        if (!map.containsKey(attractionAnnouncementVO.getAttractionId())) {
            throw new BizException("景点不存在");
        }
        try {
            AttractionAnnouncement attractionAnnouncement = new AttractionAnnouncement();
            BeanUtils.copyProperties(attractionAnnouncementVO, attractionAnnouncement);
            updateById(attractionAnnouncement);
        } catch (Exception e) {
            throw new BizException("景点公告更新失败");
        }
        return "景点公告更新成功";
    }

    @Override
    public String completeDeleteAttractionAnnouncement(Long attractionAnnouncementId, Long attractionId, String username) {
        Map<Long, AttractionDTO> map = attractionService.getAttractionsMapByUsername(username);
        if (!map.containsKey(attractionId)) {
            throw new BizException("景点不存在");
        }
        return attractionAnnouncementMapper.deleteById(attractionAnnouncementId) > 0 ? "景点公告删除成功" : "景点公告删除失败";
    }

    @Override
    public Page<AttractionAnnouncementDTO> getAttractionAnnouncementsByUsername(Long current, Long size, String username) {
        Map<Long, AttractionDTO> map = attractionService.getAttractionsMapByUsername(username);
        MPJLambdaWrapper<AttractionAnnouncement> wrapper = getAttractionAnnouncementMPJLambdaWrapper()
                .in(AttractionAnnouncement::getAttractionId, map.keySet());
        return attractionAnnouncementMapper.selectJoinPage(new Page<>(current, size), AttractionAnnouncementDTO.class, wrapper);
    }
}




