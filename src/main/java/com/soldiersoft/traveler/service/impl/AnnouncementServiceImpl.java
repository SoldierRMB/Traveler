package com.soldiersoft.traveler.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soldiersoft.traveler.entity.Announcement;
import com.soldiersoft.traveler.mapper.AnnouncementMapper;
import com.soldiersoft.traveler.service.AnnouncementService;
import org.springframework.stereotype.Service;

/**
* @author Soldier_RMB
* @description 针对表【t_announcement(公告表)】的数据库操作Service实现
* @createDate 2024-04-29 16:20:40
*/
@Service
public class AnnouncementServiceImpl extends ServiceImpl<AnnouncementMapper, Announcement>
    implements AnnouncementService{

}




