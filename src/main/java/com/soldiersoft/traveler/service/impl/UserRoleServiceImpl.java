package com.soldiersoft.traveler.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.soldiersoft.traveler.entity.Role;
import com.soldiersoft.traveler.entity.User;
import com.soldiersoft.traveler.entity.UserRole;
import com.soldiersoft.traveler.mapper.UserRoleMapper;
import com.soldiersoft.traveler.model.dto.UserRoleDTO;
import com.soldiersoft.traveler.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Soldier_RMB
 * @description 针对表【t_user_role(用户角色表)】的数据库操作Service实现
 * @createDate 2024-02-04 16:13:12
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole>
        implements UserRoleService {
    private final UserRoleMapper userRoleMapper;

    @Autowired
    public UserRoleServiceImpl(UserRoleMapper userRoleMapper) {
        this.userRoleMapper = userRoleMapper;
    }

    @Override
    public UserRoleDTO getUserRoleByUserId(Long userId) {
        MPJLambdaWrapper<UserRole> wrapper = getUserRoleMPJLambdaWrapper()
                .eq(UserRole::getUserId, userId);
        return userRoleMapper.selectJoinOne(UserRoleDTO.class, wrapper);
    }

    @Override
    public Page<UserRoleDTO> getUserRoles(Long current, Long size) {
        MPJLambdaWrapper<UserRole> wrapper = getUserRoleMPJLambdaWrapper();
        return userRoleMapper.selectJoinPage(new Page<>(current, size), UserRoleDTO.class, wrapper);
    }

    private MPJLambdaWrapper<UserRole> getUserRoleMPJLambdaWrapper() {
        return new MPJLambdaWrapper<>(UserRole.class)
                .selectAll(UserRole.class)
                .selectAssociation(User.class, UserRoleDTO::getUser)
                .selectAssociation(Role.class, UserRoleDTO::getRole)
                .leftJoin(User.class, User::getId, UserRole::getUserId)
                .leftJoin(Role.class, Role::getId, UserRole::getRoleId);
    }
}
