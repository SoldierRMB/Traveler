package com.soldiersoft.traveler.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.soldiersoft.traveler.entity.UserRole;
import com.soldiersoft.traveler.model.dto.UserRoleDTO;

/**
 * @author Soldier_RMB
 * @description 针对表【t_user_role(用户角色表)】的数据库操作Service
 * @createDate 2024-02-04 16:13:12
 */
public interface UserRoleService extends IService<UserRole> {
    UserRoleDTO getUserRoleByUserId(Long userId);
}
