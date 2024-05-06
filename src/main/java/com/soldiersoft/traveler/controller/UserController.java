package com.soldiersoft.traveler.controller;

import com.soldiersoft.traveler.model.vo.LoginVO;
import com.soldiersoft.traveler.model.vo.PasswordVO;
import com.soldiersoft.traveler.model.vo.ResultVO;
import com.soldiersoft.traveler.model.vo.UserVO;
import com.soldiersoft.traveler.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Tag(name = "UserController", description = "用户接口")
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(description = "用户登录")
    @PostMapping("/login")
    public ResultVO<String> login(@Valid @RequestBody LoginVO loginVO) {
        return ResultVO.ok(userService.login(loginVO));
    }

    @Operation(description = "用户注销")
    @GetMapping("/logout")
    public ResultVO<?> logout() {
        return ResultVO.ok();
    }

    @Operation(description = "获取用户是否存在")
    @GetMapping("/getUserIsPresent")
    public ResultVO<Boolean> getUserIsPresent(@RequestParam String username) {
        return ResultVO.ok(userService.getUserIsPresent(username));
    }

    @Operation(description = "发送注册验证码")
    @PostMapping("/sendCode")
    public ResultVO<String> sendCode(@Valid @RequestBody UserVO userVO) {
        return ResultVO.ok(userService.sendCode(userVO.getEmail()));
    }

    @Operation(description = "注册用户")
    @PostMapping("/register")
    public ResultVO<String> register(@Valid @RequestBody UserVO userVO) {
        return ResultVO.ok(userService.register(userVO));
    }

    @Operation(description = "获取用户权限")
    @GetMapping("/getUserAuthentication")
    public ResultVO<Authentication> getUserAuthentication(Authentication authentication) {
        return ResultVO.ok(authentication);
    }

    @Operation(description = "修改用户密码")
    @PutMapping("/changePassword")
    public ResultVO<String> changePassword(@RequestBody PasswordVO passwordVO, @RequestParam String username) {
        return ResultVO.ok(userService.changePassword(passwordVO, username));
    }
}
