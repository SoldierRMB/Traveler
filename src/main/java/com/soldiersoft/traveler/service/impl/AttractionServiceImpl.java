package com.soldiersoft.traveler.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soldiersoft.traveler.entity.Attraction;
import com.soldiersoft.traveler.entity.UserAttraction;
import com.soldiersoft.traveler.exception.BizException;
import com.soldiersoft.traveler.mapper.AttractionMapper;
import com.soldiersoft.traveler.model.dto.AttractionDTO;
import com.soldiersoft.traveler.model.dto.UserDTO;
import com.soldiersoft.traveler.model.vo.AttractionVO;
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
    public Boolean getAttractionIsPresent(Long attractionId) {
        Attraction attraction = lambdaQuery().eq(Attraction::getId, attractionId).one();
        return Optional.ofNullable(attraction).isPresent();
    }

    @Override
    public Map<Long, AttractionDTO> getAttractionsMapByUsername(String username) {
        return userAttractionService.getUserAttractionsByUsername(username).stream()
                .collect(Collectors.toMap(userAttractionVO -> userAttractionVO.getAttractionVO().getId(),
                        userAttractionVO -> {
                            AttractionVO attractionVO = userAttractionVO.getAttractionVO();
                            AttractionDTO attractionDTO = new AttractionDTO();
                            BeanUtils.copyProperties(attractionVO, attractionDTO);
                            return attractionDTO;
                        }));
    }

    @Override
    @Transactional
    public Attraction publishAttraction(AttractionVO attractionVO, String username) {
        try {
            UserDTO userDTO = userService.getUserByUsername(username);
            Attraction attraction = new Attraction();
            BeanUtils.copyProperties(attractionVO, attraction);
            save(attraction);
            UserAttraction userAttraction = UserAttraction.builder()
                    .userId(userDTO.getId())
                    .attractionId(attraction.getId())
                    .build();
            userAttractionService.save(userAttraction);
            return attraction;
        } catch (BizException e) {
            throw new BizException("景点发布失败，请联系管理员");
        }
    }

    @Override
    public Attraction updateAttraction(AttractionVO attractionVO, String username) {
        Map<Long, AttractionDTO> map = getAttractionsMapByUsername(username);
        if (!map.containsKey(attractionVO.getId())) {
            throw new BizException("景点不存在");
        }
        Attraction attraction = new Attraction();
        BeanUtils.copyProperties(attractionVO, attraction);
        updateById(attraction);
        return attraction;
    }

    @Override
    public String deleteAttraction(Long attractionId, String username) {
        Map<Long, AttractionDTO> map = getAttractionsMapByUsername(username);
        if (!map.containsKey(attractionId)) {
            throw new BizException("景点不存在");
        }
        lambdaUpdate()
                .set(Attraction::getIsDeleted, 1)
                .eq(Attraction::getId, attractionId)
                .update();
        return "景点删除成功";
    }

    @Override
    @Transactional
    public String reviewAttractions(Long[] attractionIds, Boolean pass) {
        try {
            Long[] attractions = Arrays.stream(attractionIds)
                    .filter(this::getAttractionIsPresent)
                    .map(id -> Attraction.builder().id(id).build())
                    .map(Attraction::getId)
                    .toArray(Long[]::new);
            if (attractions.length == 0)
                return "没有需要审核的景点";
            LambdaUpdateWrapper<Attraction> wrapper = new LambdaUpdateWrapper<Attraction>()
                    .func(attraction -> attraction.set(Attraction::getReviewed, pass ? 1 : 2));
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
    public List<AttractionVO> getAttractions() {
        return Optional.ofNullable(lambdaQuery()
                        .eq(Attraction::getReviewed, 1)
                        .eq(Attraction::getIsDeleted, 0)
                        .list())
                .map(attractions -> attractions.stream().map(attraction -> {
                    AttractionVO attractionVO = new AttractionVO();
                    BeanUtils.copyProperties(attraction, attractionVO);
                    return attractionVO;
                }).toList())
                .orElse(null);
    }

    @Override
    public String restoreAttraction(Long attractionId, String username) {
        Map<Long, AttractionDTO> map = getAttractionsMapByUsername(username);
        if (!map.containsKey(attractionId)) {
            throw new BizException("景点不存在");
        }
        lambdaUpdate()
                .set(Attraction::getIsDeleted, 0)
                .eq(Attraction::getId, attractionId)
                .update();
        return "景点已恢复";
    }

    @Override
    @Transactional
    public String completeDeleteUserAttraction(Long attractionId) {
        return attractionMapper.deleteById(attractionId) > 0 ? "删除成功" : "删除失败";
    }
}




