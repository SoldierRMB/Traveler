package com.soldiersoft.traveler.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soldiersoft.traveler.entity.Attraction;
import com.soldiersoft.traveler.entity.Order;
import com.soldiersoft.traveler.entity.Rating;
import com.soldiersoft.traveler.exception.BizException;
import com.soldiersoft.traveler.mapper.RatingMapper;
import com.soldiersoft.traveler.model.dto.AttractionDTO;
import com.soldiersoft.traveler.model.dto.OrderDTO;
import com.soldiersoft.traveler.model.vo.RatingVO;
import com.soldiersoft.traveler.service.AttractionService;
import com.soldiersoft.traveler.service.OrderService;
import com.soldiersoft.traveler.service.RatingService;
import com.soldiersoft.traveler.util.PosteriorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Map;

/**
 * @author Soldier_RMB
 * @description 针对表【t_rating(评分表)】的数据库操作Service实现
 * @createDate 2024-04-29 16:20:40
 */
@Service
public class RatingServiceImpl extends ServiceImpl<RatingMapper, Rating>
        implements RatingService {
    private final OrderService orderService;
    private final AttractionService attractionService;

    @Autowired
    public RatingServiceImpl(OrderService orderService, AttractionService attractionService) {
        this.orderService = orderService;
        this.attractionService = attractionService;
    }

    @Override
    @Transactional
    public String rateCompleteOrder(RatingVO ratingVO, String username) {
        Map<Long, OrderDTO> map = orderService.getOrdersMapByUsername(username);
        if (!map.containsKey(ratingVO.getOrderId())) {
            throw new BizException("订单不存在");
        } else if (map.get(ratingVO.getOrderId()).getStatus() == 1
                || map.get(ratingVO.getOrderId()).getStatus() == 2) {
            throw new BizException("订单未完成");
        } else if (map.get(ratingVO.getOrderId()).getStatus() == 4) {
            throw new BizException("订单已评价");
        } else if (map.get(ratingVO.getOrderId()).getStatus() == 5) {
            throw new BizException("订单已取消");
        }
        Rating rating = Rating.builder()
                .rating(ratingVO.getRating())
                .content(ratingVO.getContent())
                .userId(map.get(ratingVO.getOrderId()).getUser().getId())
                .orderId(ratingVO.getOrderId())
                .build();
        save(rating);
        AttractionDTO attractionDTO = attractionService.getAttractionByOrderId(ratingVO.getOrderId());
        Integer rating1 = ratingVO.getRating();
        BigDecimal rating2 = attractionDTO.getRating();
        BigDecimal newRating;
        if (rating2 == null) {
            newRating = BigDecimal.valueOf(rating1);
        } else {
            double[] ratings = lambdaQuery().eq(Rating::getOrderId, ratingVO.getOrderId()).list().stream()
                    .mapToDouble(rating3 -> rating3.getRating().doubleValue())
                    .toArray();
            double[] updatedRatings = Arrays.copyOf(ratings, ratings.length + 1);
            updatedRatings[updatedRatings.length - 1] = rating2.doubleValue();
            double posteriorMean = PosteriorUtil.calculatePosteriorMean(updatedRatings);
            newRating = BigDecimal.valueOf(posteriorMean);
        }
        orderService.lambdaUpdate()
                .set(Order::getStatus, 4)
                .eq(Order::getId, ratingVO.getOrderId())
                .update();
        attractionService.lambdaUpdate()
                .set(Attraction::getRating, newRating)
                .eq(Attraction::getId, attractionDTO.getId())
                .update();
        return "评分成功";
    }
}




