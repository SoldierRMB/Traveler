package com.soldiersoft.traveler.service.impl;


import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.signers.JWTSigner;
import cn.hutool.jwt.signers.JWTSignerUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soldiersoft.traveler.entity.User;
import com.soldiersoft.traveler.entity.UserRole;
import com.soldiersoft.traveler.exception.BizException;
import com.soldiersoft.traveler.mapper.UserMapper;
import com.soldiersoft.traveler.model.vo.LoginVO;
import com.soldiersoft.traveler.model.vo.UserDetailsVO;
import com.soldiersoft.traveler.model.vo.UserVO;
import com.soldiersoft.traveler.service.UserRoleService;
import com.soldiersoft.traveler.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleService userRoleService;

    @Lazy
    @Autowired
    public UserServiceImpl(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, UserRoleService userRoleService) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userRoleService = userRoleService;
    }

    @Override
    public String login(LoginVO loginVO) {
        Authentication authentication;
        try {
            UsernamePasswordAuthenticationToken authenticationToken = UsernamePasswordAuthenticationToken.unauthenticated(loginVO.getUsername(), loginVO.getPassword());
            authentication = authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (AuthenticationException e) {
            throw new BizException("登录失败，用户名或密码错误");
        }
        UserDetailsVO userDetailsVO = (UserDetailsVO) authentication.getPrincipal();
        return createToken(userDetailsVO);
    }

    @Override
    public String createToken(UserDetailsVO userDetailsVO) {
        Map<String, Object> payload = new HashMap<>() {
            {
                LocalDateTime now = LocalDateTime.now();
                LocalDateTime expireTime = now.plusDays(15);
                //签发时间
                put(JWTPayload.ISSUED_AT, now);
                //过期时间
                put(JWTPayload.EXPIRES_AT, expireTime);
                //生效时间
                put(JWTPayload.NOT_BEFORE, now);
            }
        };
        Map<String, Object> user = new HashMap<>();
        userDetailsVO = (UserDetailsVO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<String> authorities = userDetailsVO.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        user.put("username", userDetailsVO.getUsername());
        user.put("authorities", authorities);
        payload.putAll(user);
        byte[] key = "1234567890".getBytes();
        final JWTSigner signer = JWTSignerUtil.hs256(key);
        return JWTUtil.createToken(payload, signer);
    }

    @Override
    public Boolean getUserIsPresent(String email) {
        User user = lambdaQuery().eq(User::getEmail, email).one();
        return Optional.ofNullable(user).isPresent();
    }

    @Override
    @Transactional
    public String register(UserVO userVO) {
        if (getUserIsPresent(userVO.getEmail()))
            throw new BizException("邮箱已被注册");
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
    }
}




