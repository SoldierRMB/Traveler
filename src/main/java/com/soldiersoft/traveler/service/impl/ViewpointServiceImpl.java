package com.soldiersoft.traveler.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soldiersoft.traveler.entity.User;
import com.soldiersoft.traveler.entity.Viewpoint;
import com.soldiersoft.traveler.exception.BizException;
import com.soldiersoft.traveler.mapper.ViewpointMapper;
import com.soldiersoft.traveler.model.dto.UserDTO;
import com.soldiersoft.traveler.model.dto.UserViewpointDTO;
import com.soldiersoft.traveler.model.vo.UserVO;
import com.soldiersoft.traveler.model.vo.UserViewpointVO;
import com.soldiersoft.traveler.model.vo.ViewpointVO;
import com.soldiersoft.traveler.service.UserService;
import com.soldiersoft.traveler.service.UserViewpointService;
import com.soldiersoft.traveler.service.ViewpointService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Soldier_RMB
 * @description 针对表【t_viewpoint(景点表)】的数据库操作Service实现
 * @createDate 2024-03-04 15:12:12
 */
@Service
public class ViewpointServiceImpl extends ServiceImpl<ViewpointMapper, Viewpoint>
        implements ViewpointService {
    private final UserService userService;
    private final UserViewpointService userViewpointService;
    private final ViewpointMapper viewpointMapper;

    @Autowired
    public ViewpointServiceImpl(UserService userService, UserViewpointService userViewpointService, ViewpointMapper viewpointMapper) {
        this.userService = userService;
        this.userViewpointService = userViewpointService;
        this.viewpointMapper = viewpointMapper;
    }

    @Override
    public ViewpointVO staffGetViewpointById(Long viewpointId, String username) {
        UserDTO userDTO = userService.getUserByUsername(username);
        return Optional.ofNullable(userDTO)
                .flatMap(user -> lambdaQuery().eq(Viewpoint::getId, viewpointId).oneOpt())
                .flatMap(vp -> userViewpointService.getUserViewpointByUserId(userDTO.getId()).stream()
                        .filter(userViewpointDTO -> userViewpointDTO.getViewpoint().equals(vp))
                        .findFirst()
                        .map(userViewpointDTO -> {
                            ViewpointVO vo = new ViewpointVO();
                            BeanUtils.copyProperties(vp, vo);
                            return vo;
                        }))
                .orElse(null);
    }

    @Override
    public Boolean getViewpointIsPresent(Long viewpointId) {
        Viewpoint viewpoint = lambdaQuery().eq(Viewpoint::getId, viewpointId).one();
        return Optional.ofNullable(viewpoint).isPresent();
    }

    @Override
    @Transactional
    public String postViewpoint(UserViewpointVO userViewpointVO) {
        try {
            UserVO userVO = userViewpointVO.getUserVO();
            ViewpointVO viewpointVO = userViewpointVO.getViewpointVO();
            User user = User.builder()
                    .id(userVO.getId())
                    .build();
            Viewpoint viewpoint = Viewpoint.builder()
                    .viewpointName(viewpointVO.getViewpointName())
                    .description(viewpointVO.getDescription())
                    .location(viewpointVO.getLocation())
                    .provinceCode(viewpointVO.getProvinceCode())
                    .cityCode(viewpointVO.getCityCode())
                    .areaCode(viewpointVO.getAreaCode())
                    .streetCode(viewpointVO.getStreetCode())
                    .build();
            save(viewpoint);
            UserViewpointDTO userViewpointDTO = UserViewpointDTO.builder()
                    .user(user)
                    .viewpoint(viewpoint)
                    .build();
            if (userViewpointService.saveUserViewpointFromViewpoint(userViewpointDTO))
                return "景点发布成功，待管理员审核";
            else
                return null;
        } catch (BizException e) {
            throw new BizException("景点发布失败，请联系管理员", userViewpointVO);
        }
    }

    @Override
    public String updateViewpoint(ViewpointVO viewpointVO) {
        try {
            Boolean viewpointIsPresent = getViewpointIsPresent(viewpointVO.getId());
            if (viewpointIsPresent) {
                lambdaUpdate()
                        .set(Viewpoint::getViewpointName, viewpointVO.getViewpointName())
                        .set(Viewpoint::getDescription, viewpointVO.getDescription())
                        .set(Viewpoint::getLocation, viewpointVO.getLocation())
                        .set(Viewpoint::getProvinceCode, viewpointVO.getProvinceCode())
                        .set(Viewpoint::getCityCode, viewpointVO.getCityCode())
                        .set(Viewpoint::getAreaCode, viewpointVO.getAreaCode())
                        .set(Viewpoint::getStreetCode, viewpointVO.getStreetCode())
                        .eq(Viewpoint::getId, viewpointVO.getId())
                        .update();
                return "景点更新成功";
            } else throw new BizException("景点不存在");
        } catch (BizException e) {
            throw new BizException("景点更新失败，请联系管理员");
        }
    }

    @Override
    public String deleteViewpoint(Long viewpointId) {
        try {
            Boolean viewpointIsPresent = getViewpointIsPresent(viewpointId);
            if (viewpointIsPresent) {
                lambdaUpdate()
                        .set(Viewpoint::getIsDeleted, 1)
                        .eq(Viewpoint::getId, viewpointId)
                        .update();
                return "景点删除成功";
            } else throw new BizException("景点不存在");
        } catch (BizException e) {
            throw new BizException("景点删除失败，请联系管理员");
        }
    }

    @Override
    @Transactional
    public String reviewViewpoints(Long[] viewpointIds) {
        try {
            Long[] viewpoints = Arrays.stream(viewpointIds)
                    .filter(this::getViewpointIsPresent)
                    .map(id -> Viewpoint.builder().id(id).build())
                    .map(Viewpoint::getId)
                    .toArray(Long[]::new);
            if (viewpoints.length == 0)
                return "没有需要审核的景点";
            LambdaUpdateWrapper<Viewpoint> wrapper = new LambdaUpdateWrapper<Viewpoint>()
                    .set(Viewpoint::getReviewed, 1);
            Arrays.stream(viewpoints).forEach(viewpointId ->
                    wrapper.or().eq(Viewpoint::getId, viewpointId));
            int rows = viewpointMapper.update(wrapper);
            return String.format("%d个景点审核成功", rows);
        } catch (BizException e) {
            throw new BizException("景点审核失败");
        }
    }

    @Override
    public ViewpointVO getViewpointById(Long viewpointId) {
        Map<SFunction<Viewpoint, ?>, Object> map = new HashMap<>();
        map.put(Viewpoint::getId, viewpointId);
        map.put(Viewpoint::getReviewed, 1);
        return Optional.ofNullable(lambdaQuery().allEq(map).one())
                .map(viewpoint -> {
                    ViewpointVO vo = new ViewpointVO();
                    BeanUtils.copyProperties(viewpoint, vo);
                    return vo;
                })
                .orElse(null);
    }

    @Override
    public List<ViewpointVO> getViewpointsByCityCode(Long cityCode) {
        Map<SFunction<Viewpoint, ?>, Object> map = new HashMap<>();
        map.put(Viewpoint::getCityCode, cityCode);
        map.put(Viewpoint::getReviewed, 1);
        return Optional.ofNullable(lambdaQuery().allEq(map).list())
                .map(viewpoints -> viewpoints.stream()
                        .map(viewpoint -> {
                            ViewpointVO vo = new ViewpointVO();
                            BeanUtils.copyProperties(viewpoint, vo);
                            return vo;
                        })
                        .collect(Collectors.toList())
                )
                .orElse(null);
    }
}




