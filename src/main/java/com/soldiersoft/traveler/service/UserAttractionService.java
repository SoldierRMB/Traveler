package com.soldiersoft.traveler.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.soldiersoft.traveler.entity.UserAttraction;
import com.soldiersoft.traveler.model.dto.UserAttractionDTO;
import com.soldiersoft.traveler.model.vo.UserAttractionVO;

import java.util.List;

/**
 * @author Soldier_RMB
 * @description 针对表【t_user_attraction(用户景点表)】的数据库操作Service
 * @createDate 2024-03-13 15:04:11
 */
public interface UserAttractionService extends IService<UserAttraction> {
    Boolean saveUserAttractionFromAttraction(UserAttractionDTO userAttractionDTO);

    List<UserAttractionDTO> getUserAttractionByUserId(Long userId);

    List<UserAttractionVO> getUserAttractions(Boolean reviewed);
}
