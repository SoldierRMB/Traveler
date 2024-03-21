package com.soldiersoft.traveler.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.soldiersoft.traveler.entity.Attraction;
import com.soldiersoft.traveler.entity.User;
import com.soldiersoft.traveler.entity.UserAttraction;
import com.soldiersoft.traveler.exception.BizException;
import com.soldiersoft.traveler.mapper.UserAttractionMapper;
import com.soldiersoft.traveler.model.dto.UserAttractionDTO;
import com.soldiersoft.traveler.service.UserAttractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Soldier_RMB
 * @description 针对表【t_user_attraction(用户景点表)】的数据库操作Service实现
 * @createDate 2024-03-13 15:04:11
 */
@Service
public class UserAttractionServiceImpl extends ServiceImpl<UserAttractionMapper, UserAttraction>
        implements UserAttractionService {
    private final UserAttractionMapper userAttractionMapper;

    @Autowired
    public UserAttractionServiceImpl(UserAttractionMapper userAttractionMapper) {
        this.userAttractionMapper = userAttractionMapper;
    }

    @Override
    public Boolean saveUserAttractionFromAttraction(UserAttractionDTO userAttractionDTO) {
        try {
            UserAttraction userAttraction = UserAttraction.builder()
                    .userId(userAttractionDTO.getUser().getId())
                    .attractionId(userAttractionDTO.getAttraction().getId())
                    .build();
            save(userAttraction);
            return true;
        } catch (Exception e) {
            throw new BizException(userAttractionDTO);
        }
    }

    @Override
    public List<UserAttractionDTO> getUserAttractionByUserId(Long userId) {
        MPJLambdaWrapper<UserAttraction> wrapper = new MPJLambdaWrapper<>(UserAttraction.class)
                .selectAll(UserAttraction.class)
                .selectAssociation(User.class, UserAttractionDTO::getUser)
                .selectAssociation(Attraction.class, UserAttractionDTO::getAttraction)
                .leftJoin(User.class, User::getId, UserAttraction::getUserId)
                .leftJoin(Attraction.class, com.soldiersoft.traveler.entity.Attraction::getId, UserAttraction::getAttractionId)
                .eq(UserAttraction::getUserId, userId);
        return userAttractionMapper.selectJoinList(UserAttractionDTO.class, wrapper);
    }
}




