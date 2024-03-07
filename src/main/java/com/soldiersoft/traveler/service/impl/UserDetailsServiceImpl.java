package com.soldiersoft.traveler.service.impl;

import com.soldiersoft.traveler.entity.User;
import com.soldiersoft.traveler.model.vo.UserDetailsVO;
import com.soldiersoft.traveler.service.UserRoleService;
import com.soldiersoft.traveler.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;
    private final UserRoleService userRoleService;

    @Autowired
    public UserDetailsServiceImpl(UserService userService, UserRoleService userRoleService) {
        this.userService = userService;
        this.userRoleService = userRoleService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userService.lambdaQuery().eq(User::getUsername, username).one();
        return Optional.ofNullable(user)
                .map(userRoleDTO -> new UserDetailsVO(userRoleService.queryUserRoleByUserId(user.getId())))
                .orElse(null);
    }
}
