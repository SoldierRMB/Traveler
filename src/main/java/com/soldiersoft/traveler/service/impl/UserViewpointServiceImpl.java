package com.soldiersoft.traveler.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soldiersoft.traveler.entity.UserViewpoint;
import com.soldiersoft.traveler.exception.BizException;
import com.soldiersoft.traveler.mapper.UserViewpointMapper;
import com.soldiersoft.traveler.model.dto.UserViewpointDTO;
import com.soldiersoft.traveler.service.UserViewpointService;
import org.springframework.stereotype.Service;

/**
 * @author Soldier_RMB
 * @description 针对表【t_user_viewpoint(用户景点表)】的数据库操作Service实现
 * @createDate 2024-03-13 15:04:11
 */
@Service
public class UserViewpointServiceImpl extends ServiceImpl<UserViewpointMapper, UserViewpoint>
        implements UserViewpointService {

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
}




