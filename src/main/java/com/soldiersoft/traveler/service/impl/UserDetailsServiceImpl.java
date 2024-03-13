package com.soldiersoft.traveler.service.impl;

import com.soldiersoft.traveler.entity.User;
import com.soldiersoft.traveler.model.dto.RoleMenuDTO;
import com.soldiersoft.traveler.model.dto.UserRoleDTO;
import com.soldiersoft.traveler.model.vo.UserDetailsVO;
import com.soldiersoft.traveler.service.RoleMenuService;
import com.soldiersoft.traveler.service.UserRoleService;
import com.soldiersoft.traveler.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;
    private final UserRoleService userRoleService;
    private final RoleMenuService roleMenuService;

    @Autowired
    public UserDetailsServiceImpl(UserService userService, UserRoleService userRoleService, RoleMenuService roleMenuService) {
        this.userService = userService;
        this.userRoleService = userRoleService;
        this.roleMenuService = roleMenuService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userService.lambdaQuery().eq(User::getUsername, username).one();
        return Optional.ofNullable(user)
                .map(DTOS -> {
                    UserRoleDTO userRoleDTO = userRoleService.getUserRoleByUserId(user.getId());
                    List<RoleMenuDTO> roleMenuDTOS = roleMenuService.getRoleMenuByRoleId(userRoleDTO.getRole().getId());
                    return new UserDetailsVO(userRoleDTO, roleMenuDTOS);
                })
                .orElse(null);
    }
}