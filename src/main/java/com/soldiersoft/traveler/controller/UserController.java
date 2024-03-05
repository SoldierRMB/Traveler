package com.soldiersoft.traveler.controller;

import com.soldiersoft.traveler.model.dto.UserDTO;
import com.soldiersoft.traveler.model.vo.ResultVO;
import com.soldiersoft.traveler.model.vo.UserDetailsVO;
import com.soldiersoft.traveler.model.vo.UserVO;
import com.soldiersoft.traveler.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "用户控制器")
@RestController
public class UserController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    @Autowired
    public UserController(AuthenticationManager authenticationManager, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @Operation(description = "用户登录接口")
    @PostMapping("/login")
    public ResultVO<UserDetails> login(@RequestBody UserDetailsVO userDetailsVO) {
        UsernamePasswordAuthenticationToken unauthenticated = UsernamePasswordAuthenticationToken.unauthenticated(userDetailsVO.getUsername(), userDetailsVO.getPassword());
        Authentication authenticate = authenticationManager.authenticate(unauthenticated);
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        return ResultVO.ok(userService.loadUserByUsername(userDetailsVO.getUsername()));
    }

    @Operation(description = "注册用户")
    @PostMapping("/register")
    public ResultVO<UserDTO> register(@RequestBody UserVO userVO) {
        return ResultVO.ok(userService.register(userVO));
    }

    @Operation(description = "获取用户权限")
    @GetMapping("/queryUserAuthentication")
    public ResultVO<Authentication> queryUserAuthentication(Authentication authentication) {
        return ResultVO.ok(authentication);
    }

    @GetMapping("/test")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String test() {
        return "test";
    }
}
