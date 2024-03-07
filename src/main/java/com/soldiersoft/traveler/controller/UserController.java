package com.soldiersoft.traveler.controller;

import com.soldiersoft.traveler.model.vo.LoginVO;
import com.soldiersoft.traveler.model.vo.ResultVO;
import com.soldiersoft.traveler.model.vo.UserVO;
import com.soldiersoft.traveler.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "用户控制器")
@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(description = "用户登录接口")
    @PostMapping("/login")
    public ResultVO<String> login(@RequestBody LoginVO loginVO) {
        return ResultVO.ok(userService.login(loginVO));
    }

    @Operation(description = "注册用户")
    @PostMapping("/register")
    public ResultVO<String> register(@RequestBody UserVO userVO) {
        return ResultVO.ok(userService.register(userVO));
    }

    @Operation(description = "获取用户权限")
    @GetMapping("/queryUserAuthentication")
    public ResultVO<Authentication> queryUserAuthentication(Authentication authentication) {
        return ResultVO.ok(authentication);
    }
}
