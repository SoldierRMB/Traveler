package com.soldiersoft.traveler.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.soldiersoft.traveler.entity.*;
import com.soldiersoft.traveler.exception.BizException;
import com.soldiersoft.traveler.mapper.AttractionMapper;
import com.soldiersoft.traveler.model.dto.AttractionDTO;
import com.soldiersoft.traveler.model.dto.UserDTO;
import com.soldiersoft.traveler.model.vo.AttractionVO;
import com.soldiersoft.traveler.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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
    private final OrderService orderService;
    private final AttractionTicketService attractionTicketService;

    @Lazy
    @Autowired
    public AttractionServiceImpl(UserService userService, UserAttractionService userAttractionService, AttractionMapper attractionMapper, OrderService orderService, AttractionTicketService attractionTicketService) {
        this.userService = userService;
        this.userAttractionService = userAttractionService;
        this.attractionMapper = attractionMapper;
        this.orderService = orderService;
        this.attractionTicketService = attractionTicketService;
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
    public Page<AttractionDTO> getAttractions(Long current, Long size) {
        MPJLambdaWrapper<Attraction> wrapper = getAttractionMPJLambdaWrapper()
                .eq(Attraction::getReviewed, 1)
                .eq(Attraction::getIsDeleted, 0);
        return attractionMapper.selectJoinPage(new Page<>(current, size), AttractionDTO.class, wrapper);
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

    @Override
    public AttractionDTO getAttractionByOrderId(Long orderId) {
        Long ticketId = orderService.lambdaQuery().eq(Order::getId, orderId).one().getTicketId();
        Long attractionId = attractionTicketService.lambdaQuery().eq(AttractionTicket::getTicketId, ticketId).one().getAttractionId();
        Attraction attraction = lambdaQuery().eq(Attraction::getId, attractionId).one();
        AttractionDTO attractionDTO = new AttractionDTO();
        BeanUtils.copyProperties(attraction, attractionDTO);
        return attractionDTO;
    }

    @Override
    public Page<AttractionDTO> getAttractionsByKeyword(String attractionName, Long cityCode, Long current, Long size) {
        Map<SFunction<Attraction, ?>, Object> map = new HashMap<>();
        map.put(Attraction::getCityCode, cityCode);
        map.put(Attraction::getReviewed, 1);
        map.put(Attraction::getIsDeleted, 0);
        MPJLambdaWrapper<Attraction> wrapper = getAttractionMPJLambdaWrapper()
                .like(Attraction::getAttractionName, attractionName)
                .allEq(map, false);
        return attractionMapper.selectJoinPage(new Page<>(current, size), AttractionDTO.class, wrapper);
    }

    private static MPJLambdaWrapper<Attraction> getAttractionMPJLambdaWrapper() {
        return new MPJLambdaWrapper<>(Attraction.class)
                .selectAll(Attraction.class)
                .selectAssociation(Province.class, AttractionDTO::getProvince)
                .selectAssociation(City.class, AttractionDTO::getCity)
                .selectAssociation(Area.class, AttractionDTO::getArea)
                .selectAssociation(Street.class, AttractionDTO::getStreet)
                .leftJoin(Province.class, Province::getCode, Attraction::getProvinceCode)
                .leftJoin(City.class, City::getCode, Attraction::getCityCode)
                .leftJoin(Area.class, Area::getCode, Attraction::getAreaCode)
                .leftJoin(Street.class, Street::getCode, Attraction::getStreetCode);
    }
}




