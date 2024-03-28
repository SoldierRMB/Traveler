package com.soldiersoft.traveler.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soldiersoft.traveler.entity.Attraction;
import com.soldiersoft.traveler.entity.User;
import com.soldiersoft.traveler.exception.BizException;
import com.soldiersoft.traveler.mapper.AttractionMapper;
import com.soldiersoft.traveler.model.dto.UserAttractionDTO;
import com.soldiersoft.traveler.model.dto.UserDTO;
import com.soldiersoft.traveler.model.vo.AttractionVO;
import com.soldiersoft.traveler.model.vo.UserAttractionVO;
import com.soldiersoft.traveler.model.vo.UserVO;
import com.soldiersoft.traveler.service.AttractionService;
import com.soldiersoft.traveler.service.UserAttractionService;
import com.soldiersoft.traveler.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Soldier_RMB
 * @description 针对表【t_attraction(景点表)】的数据库操作Service实现
 * @createDate 2024-03-04 15:12:12
 */
@Service
public class AttractionServiceImpl extends ServiceImpl<AttractionMapper, Attraction>
        implements AttractionService {
    private final UserService userService;
    private final UserAttractionService userAttractionService;
    private final AttractionMapper attractionMapper;

    @Autowired
    public AttractionServiceImpl(UserService userService, UserAttractionService userAttractionService, AttractionMapper attractionMapper) {
        this.userService = userService;
        this.userAttractionService = userAttractionService;
        this.attractionMapper = attractionMapper;
    }

    @Override
    public AttractionVO staffGetAttractionById(Long attractionId, String username) {
        UserDTO userDTO = userService.getUserByUsername(username);
        return Optional.ofNullable(userDTO)
                .flatMap(user -> lambdaQuery().eq(Attraction::getId, attractionId).oneOpt())
                .flatMap(vp -> userAttractionService.getUserAttractionByUserId(userDTO.getId()).stream()
                        .filter(userAttractionDTO -> userAttractionDTO.getAttraction().equals(vp))
                        .findFirst()
                        .map(userAttractionDTO -> {
                            AttractionVO vo = new AttractionVO();
                            BeanUtils.copyProperties(vp, vo);
                            return vo;
                        }))
                .orElse(null);
    }

    @Override
    public Boolean getAttractionIsPresent(Long attractionId) {
        Attraction attraction = lambdaQuery().eq(Attraction::getId, attractionId).one();
        return Optional.ofNullable(attraction).isPresent();
    }

    @Override
    @Transactional
    public String postAttraction(UserAttractionVO userAttractionVO, String username) {
        try {
            UserVO userVO = userAttractionVO.getUserVO();
            AttractionVO attractionVO = userAttractionVO.getAttractionVO();
            User user = User.builder()
                    .id(userVO.getId())
                    .build();
            Attraction attraction = Attraction.builder()
                    .attractionName(attractionVO.getAttractionName())
                    .description(attractionVO.getDescription())
                    .location(attractionVO.getLocation())
                    .provinceCode(attractionVO.getProvinceCode())
                    .cityCode(attractionVO.getCityCode())
                    .areaCode(attractionVO.getAreaCode())
                    .streetCode(attractionVO.getStreetCode())
                    .build();
            save(attraction);
            UserAttractionDTO userAttractionDTO = UserAttractionDTO.builder()
                    .user(user)
                    .attraction(attraction)
                    .build();
            if (userAttractionService.saveUserAttractionFromAttraction(userAttractionDTO))
                return "景点发布成功，待管理员审核";
            else
                return null;
        } catch (BizException e) {
            throw new BizException("景点发布失败，请联系管理员", userAttractionVO);
        }
    }

    @Override
    public String updateAttraction(AttractionVO attractionVO, String username) {
        try {
            Boolean attractionIsPresent = getAttractionIsPresent(attractionVO.getId());
            if (attractionIsPresent) {
                lambdaUpdate()
                        .set(Attraction::getAttractionName, attractionVO.getAttractionName())
                        .set(Attraction::getDescription, attractionVO.getDescription())
                        .set(Attraction::getLocation, attractionVO.getLocation())
                        .set(Attraction::getProvinceCode, attractionVO.getProvinceCode())
                        .set(Attraction::getCityCode, attractionVO.getCityCode())
                        .set(Attraction::getAreaCode, attractionVO.getAreaCode())
                        .set(Attraction::getStreetCode, attractionVO.getStreetCode())
                        .eq(Attraction::getId, attractionVO.getId())
                        .update();
                return "景点更新成功";
            } else throw new BizException("景点不存在");
        } catch (BizException e) {
            throw new BizException("景点更新失败，请联系管理员");
        }
    }

    @Override
    public String deleteAttraction(Long attractionId, String username) {
        try {
            Boolean attractionIsPresent = getAttractionIsPresent(attractionId);
            if (attractionIsPresent) {
                lambdaUpdate()
                        .set(Attraction::getIsDeleted, 1)
                        .eq(Attraction::getId, attractionId)
                        .update();
                return "景点删除成功";
            } else throw new BizException("景点不存在");
        } catch (BizException e) {
            throw new BizException("景点删除失败，请联系管理员");
        }
    }

    @Override
    @Transactional
    public String reviewAttractions(Long[] attractionIds) {
        try {
            Long[] attractions = Arrays.stream(attractionIds)
                    .filter(this::getAttractionIsPresent)
                    .map(id -> Attraction.builder().id(id).build())
                    .map(Attraction::getId)
                    .toArray(Long[]::new);
            if (attractions.length == 0)
                return "没有需要审核的景点";
            LambdaUpdateWrapper<Attraction> wrapper = new LambdaUpdateWrapper<Attraction>()
                    .set(Attraction::getReviewed, 1);
            Arrays.stream(attractions).forEach(attractionId ->
                    wrapper.or().eq(Attraction::getId, attractionId));
            int rows = attractionMapper.update(wrapper);
            return String.format("%d个景点审核成功", rows);
        } catch (BizException e) {
            throw new BizException("景点审核失败");
        }
    }

    @Override
    public AttractionVO getAttractionById(Long attractionId) {
        Map<SFunction<Attraction, ?>, Object> map = new HashMap<>();
        map.put(Attraction::getId, attractionId);
        map.put(Attraction::getReviewed, 1);
        return Optional.ofNullable(lambdaQuery().allEq(map).one())
                .map(attraction -> {
                    AttractionVO vo = new AttractionVO();
                    BeanUtils.copyProperties(attraction, vo);
                    return vo;
                })
                .orElse(null);
    }

    @Override
    public List<AttractionVO> getAttractionsByCityCode(Long cityCode) {
        Map<SFunction<Attraction, ?>, Object> map = new HashMap<>();
        map.put(Attraction::getCityCode, cityCode);
        map.put(Attraction::getReviewed, 1);
        return Optional.ofNullable(lambdaQuery().allEq(map).list())
                .map(attractions -> attractions.stream()
                        .map(attraction -> {
                            AttractionVO vo = new AttractionVO();
                            BeanUtils.copyProperties(attraction, vo);
                            return vo;
                        })
                        .collect(Collectors.toList())
                )
                .orElse(null);
    }

    @Override
    public List<AttractionVO> getAllAttractions() {
        return Optional.ofNullable(lambdaQuery().list())
                .map(attractions -> attractions.stream().map(attraction -> {
                    AttractionVO attractionVO = new AttractionVO();
                    BeanUtils.copyProperties(attraction, attractionVO);
                    return attractionVO;
                }).toList())
                .orElse(null);
    }
}




