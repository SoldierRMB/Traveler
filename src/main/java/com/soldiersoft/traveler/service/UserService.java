package com.soldiersoft.traveler.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.soldiersoft.traveler.entity.User;
import com.soldiersoft.traveler.model.dto.UserDTO;
import com.soldiersoft.traveler.model.vo.LoginVO;
import com.soldiersoft.traveler.model.vo.UserDetailsVO;
import com.soldiersoft.traveler.model.vo.UserVO;

/**
 * @author Soldier_RMB
 * @description 针对表【t_user_auth(用户验证表)】的数据库操作Service
 * @createDate 2024-02-04 16:13:12
 */
public interface UserService extends IService<User> {
    String login(LoginVO loginVO);

    String createToken(UserDetailsVO userDetailsVO);

    Boolean getUserIsPresent(String username);

    String sendCode(UserVO userVO);

    String register(UserVO userVO);

    String saveUser(UserDTO userDTO);
}
