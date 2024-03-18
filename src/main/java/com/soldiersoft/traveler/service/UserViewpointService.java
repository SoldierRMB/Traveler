package com.soldiersoft.traveler.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.soldiersoft.traveler.entity.UserViewpoint;
import com.soldiersoft.traveler.model.dto.UserViewpointDTO;

import java.util.List;

/**
 * @author Soldier_RMB
 * @description 针对表【t_user_viewpoint(用户景点表)】的数据库操作Service
 * @createDate 2024-03-13 15:04:11
 */
public interface UserViewpointService extends IService<UserViewpoint> {
    Boolean saveUserViewpointFromViewpoint(UserViewpointDTO userViewpointDTO);

    List<UserViewpointDTO> getUserViewpointByUserId(Long userId);
}
