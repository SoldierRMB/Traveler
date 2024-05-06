package com.soldiersoft.traveler.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.soldiersoft.traveler.entity.Rating;
import com.soldiersoft.traveler.model.vo.RatingVO;

/**
 * @author Soldier_RMB
 * @description 针对表【t_rating(评分表)】的数据库操作Service
 * @createDate 2024-04-29 16:20:40
 */
public interface RatingService extends IService<Rating> {

    String rateCompleteOrder(RatingVO ratingVO, String username);
}
