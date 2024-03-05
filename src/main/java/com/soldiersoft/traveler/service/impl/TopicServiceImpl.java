package com.soldiersoft.traveler.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soldiersoft.traveler.entity.Topic;
import com.soldiersoft.traveler.mapper.TopicMapper;
import com.soldiersoft.traveler.service.TopicService;
import org.springframework.stereotype.Service;

/**
 * @author Soldier_RMB
 * @description 针对表【t_topic(主题表)】的数据库操作Service实现
 * @createDate 2024-03-04 15:12:12
 */
@Service
public class TopicServiceImpl extends ServiceImpl<TopicMapper, Topic>
        implements TopicService {

}




