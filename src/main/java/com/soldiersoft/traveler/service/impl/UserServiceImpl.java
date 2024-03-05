package com.soldiersoft.traveler.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soldiersoft.traveler.entity.User;
import com.soldiersoft.traveler.mapper.UserMapper;
import com.soldiersoft.traveler.model.dto.UserDTO;
import com.soldiersoft.traveler.model.dto.UserRoleDTO;
import com.soldiersoft.traveler.model.vo.UserDetailsVO;
import com.soldiersoft.traveler.model.vo.UserVO;
import com.soldiersoft.traveler.service.UserRoleService;
import com.soldiersoft.traveler.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Soldier_RMB
 * @description 针对表【t_user_auth(用户验证表)】的数据库操作Service实现
 * @createDate 2024-02-04 16:13:12
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {
    private final UserRoleService userRoleService;

    @Autowired
    public UserServiceImpl(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = lambdaQuery().eq(User::getUsername, username).one();
        Optional<User> optional = Optional.ofNullable(user);
        if (optional.isPresent()) {
            UserRoleDTO userRoleDTO = userRoleService.queryUserRoleByUserId(user.getId());
            return new UserDetailsVO(userRoleDTO);
        } else {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
    }

    @Override
    public UserDTO register(UserVO userVO) {
        User user = User.builder()
                .username(userVO.getUsername())
                .password(userVO.getPassword())
                .email(userVO.getEmail())
                .build();
        save(user);
        return null;
    }
}




