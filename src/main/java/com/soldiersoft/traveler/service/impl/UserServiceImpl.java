package com.soldiersoft.traveler.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soldiersoft.traveler.entity.User;
import com.soldiersoft.traveler.entity.UserRole;
import com.soldiersoft.traveler.mapper.UserMapper;
import com.soldiersoft.traveler.model.dto.UserRoleDTO;
import com.soldiersoft.traveler.model.vo.UserDetailsVO;
import com.soldiersoft.traveler.model.vo.UserVO;
import com.soldiersoft.traveler.service.UserRoleService;
import com.soldiersoft.traveler.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.IntStream;

/**
 * @author Soldier_RMB
 * @description 针对表【t_user_auth(用户验证表)】的数据库操作Service实现
 * @createDate 2024-02-04 16:13:12
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRoleService userRoleService;

    @Lazy
    @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRoleService userRoleService) {
        this.passwordEncoder = passwordEncoder;
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
    @Transactional
    public String register(UserVO userVO) {
        User user = lambdaQuery().eq(User::getEmail, userVO.getEmail()).one();
        Optional<User> optional = Optional.ofNullable(user);
        if (optional.isEmpty()) {
            Integer userType = userVO.getUserType();
            User newUser = User.builder()
                    .username(userVO.getUsername())
                    .password("{bcrypt}" + passwordEncoder.encode(userVO.getPassword()))
                    .email(userVO.getEmail())
                    .build();
            save(newUser);
            IntStream.rangeClosed(1, 3)
                    .filter(userType::equals)
                    .forEach(roleId -> {
                        UserRole userRole = UserRole.builder()
                                .userId(newUser.getId())
                                .roleId(roleId)
                                .build();
                        userRoleService.save(userRole);
                    });
            return "注册成功";
        } else {
            return "用户名已存在";
        }
    }
}




