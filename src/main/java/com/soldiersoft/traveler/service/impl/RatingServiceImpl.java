package com.soldiersoft.traveler.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soldiersoft.traveler.entity.Rating;
import com.soldiersoft.traveler.mapper.RatingMapper;
import com.soldiersoft.traveler.service.RatingService;
import org.springframework.stereotype.Service;

/**
* @author Soldier_RMB
* @description 针对表【t_rating(评分表)】的数据库操作Service实现
* @createDate 2024-04-29 16:20:40
*/
@Service
public class RatingServiceImpl extends ServiceImpl<RatingMapper, Rating>
    implements RatingService{

}




