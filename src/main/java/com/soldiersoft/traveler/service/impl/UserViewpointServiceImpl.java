package com.soldiersoft.traveler.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.soldiersoft.traveler.entity.User;
import com.soldiersoft.traveler.entity.UserViewpoint;
import com.soldiersoft.traveler.entity.Viewpoint;
import com.soldiersoft.traveler.exception.BizException;
import com.soldiersoft.traveler.mapper.UserViewpointMapper;
import com.soldiersoft.traveler.model.dto.UserViewpointDTO;
import com.soldiersoft.traveler.service.UserViewpointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Soldier_RMB
 * @description 针对表【t_user_viewpoint(用户景点表)】的数据库操作Service实现
 * @createDate 2024-03-13 15:04:11
 */
@Service
public class UserViewpointServiceImpl extends ServiceImpl<UserViewpointMapper, UserViewpoint>
        implements UserViewpointService {
    private final UserViewpointMapper userViewpointMapper;

    @Autowired
    public UserViewpointServiceImpl(UserViewpointMapper userViewpointMapper) {
        this.userViewpointMapper = userViewpointMapper;
    }

    @Override
    public Boolean saveUserViewpointFromViewpoint(UserViewpointDTO userViewpointDTO) {
        try {
            UserViewpoint userViewpoint = UserViewpoint.builder()
                    .userId(userViewpointDTO.getUser().getId())
                    .viewpointId(userViewpointDTO.getViewpoint().getId())
                    .build();
            save(userViewpoint);
            return true;
        } catch (Exception e) {
            throw new BizException(userViewpointDTO);
        }
    }

    @Override
    public List<UserViewpointDTO> getUserViewpointByUserId(Long userId) {
        MPJLambdaWrapper<UserViewpoint> wrapper = new MPJLambdaWrapper<>(UserViewpoint.class)
                .selectAll(UserViewpoint.class)
                .selectAssociation(User.class, UserViewpointDTO::getUser)
                .selectAssociation(Viewpoint.class, UserViewpointDTO::getViewpoint)
                .leftJoin(User.class, User::getId, UserViewpoint::getUserId)
                .leftJoin(Viewpoint.class, Viewpoint::getId, UserViewpoint::getViewpointId)
                .eq(UserViewpoint::getUserId, userId);
        return userViewpointMapper.selectJoinList(UserViewpointDTO.class, wrapper);
    }
}




