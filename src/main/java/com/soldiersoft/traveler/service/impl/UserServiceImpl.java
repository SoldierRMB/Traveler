package com.soldiersoft.traveler.service.impl;


import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.signers.JWTSigner;
import cn.hutool.jwt.signers.JWTSignerUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soldiersoft.traveler.entity.User;
import com.soldiersoft.traveler.exception.BizException;
import com.soldiersoft.traveler.mapper.UserMapper;
import com.soldiersoft.traveler.model.dto.UserDTO;
import com.soldiersoft.traveler.model.vo.LoginVO;
import com.soldiersoft.traveler.model.vo.UserDetailsVO;
import com.soldiersoft.traveler.model.vo.UserVO;
import com.soldiersoft.traveler.service.MailService;
import com.soldiersoft.traveler.service.UserRoleService;
import com.soldiersoft.traveler.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Soldier_RMB
 * @description 针对表【t_user_auth(用户验证表)】的数据库操作Service实现
 * @createDate 2024-02-04 16:13:12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleService userRoleService;
    private final MailService mailService;

    @Lazy
    @Autowired
    public UserServiceImpl(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, UserRoleService userRoleService, MailService mailService) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userRoleService = userRoleService;
        this.mailService = mailService;
    }

    @Override
    public String login(LoginVO loginVO) {
        Authentication authentication;
        try {
            // 调用UserDetailsService的loadUserByUsername方法获取用户信息
            UsernamePasswordAuthenticationToken authenticationToken = UsernamePasswordAuthenticationToken.unauthenticated(loginVO.getUsername(), loginVO.getPassword());
            authentication = authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (AuthenticationException e) {
            throw new UsernameNotFoundException("用户名或密码错误");
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
        user.put("sub", userDetailsVO.getUsername());
        user.put("aud", authorities);
        payload.putAll(user);
        byte[] key = "1234567890".getBytes();
        final JWTSigner signer = JWTSignerUtil.hs256(key);
        return JWTUtil.createToken(payload, signer);
    }

    @Override
    public Boolean getUserIsPresent(String username) {
        User user = lambdaQuery().eq(User::getUsername, username).one();
        return Optional.ofNullable(user).isPresent();
    }

    @Override
    public String sendCode(UserVO userVO) {
        return mailService.sendCode(userVO.getEmail()) ? "邮箱验证码发送成功" : "邮箱验证码发送失败";
    }

    @Override
    @Transactional
    public String register(UserVO userVO) {
        String email = userVO.getEmail();
        if (mailService.verifyCode(email, userVO.getCode())) {
            UserDTO userDTO = UserDTO.builder()
                    .username(userVO.getUsername())
                    .email(email)
                    .password(userVO.getPassword())
                    .userType(userVO.getUserType())
                    .build();
            return saveUser(userDTO);
        } else
            return "验证码错误";
    }

    @Override
    @Transactional
    public String saveUser(UserDTO userDTO) {
        try {
            String username = userDTO.getUsername();
            String email = userDTO.getEmail();
            String password = "{bcrypt}" + passwordEncoder.encode(userDTO.getPassword());
            User user = User.builder()
                    .username(username)
                    .password(password)
                    .email(email)
                    .build();
            save(user);
            userDTO.setId(user.getId());
            if (userRoleService.saveUserRoleFromUser(userDTO))
                return "注册成功";
            else
                return null;
        } catch (Exception e) {
            throw new BizException("注册失败，请联系管理员");
        }
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        return Optional.ofNullable(lambdaQuery().eq(User::getUsername, username).one())
                .map(user -> {
                    UserDTO userDTO = new UserDTO();
                    BeanUtils.copyProperties(user, userDTO);
                    return userDTO;
                }).orElse(null);
    }
}




