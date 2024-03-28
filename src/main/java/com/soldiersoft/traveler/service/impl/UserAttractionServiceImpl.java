package com.soldiersoft.traveler.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.soldiersoft.traveler.entity.*;
import com.soldiersoft.traveler.exception.BizException;
import com.soldiersoft.traveler.mapper.UserAttractionMapper;
import com.soldiersoft.traveler.model.dto.UserAttractionDTO;
import com.soldiersoft.traveler.model.vo.*;
import com.soldiersoft.traveler.service.UserAttractionService;
import org.springframework.beans.BeanUtils;
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

    private MPJLambdaWrapper<UserAttraction> createWrapper() {
        return new MPJLambdaWrapper<>(UserAttraction.class)
                .selectAll(UserAttraction.class)
                .selectAssociation(User.class, UserAttractionDTO::getUser)
                .selectAssociation(Attraction.class, UserAttractionDTO::getAttraction)
                .selectAssociation(Province.class, UserAttractionDTO::getProvince)
                .selectAssociation(City.class, UserAttractionDTO::getCity)
                .selectAssociation(Area.class, UserAttractionDTO::getArea)
                .selectAssociation(Street.class, UserAttractionDTO::getStreet)
                .leftJoin(User.class, User::getId, UserAttraction::getUserId)
                .leftJoin(Attraction.class, Attraction::getId, UserAttraction::getAttractionId)
                .leftJoin(Province.class, Province::getCode, Attraction::getProvinceCode)
                .leftJoin(City.class, City::getCode, Attraction::getCityCode)
                .leftJoin(Area.class, Area::getCode, Attraction::getAreaCode)
                .leftJoin(Street.class, Street::getCode, Attraction::getStreetCode);
    }

    private UserAttractionVO mapToUserAttractionVO(UserAttractionDTO userAttractionDTO) {
        User user = userAttractionDTO.getUser();
        Attraction attraction = userAttractionDTO.getAttraction();
        Province province = userAttractionDTO.getProvince();
        City city = userAttractionDTO.getCity();
        Area area = userAttractionDTO.getArea();
        Street street = userAttractionDTO.getStreet();

        UserVO userVO = UserVO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();

        AttractionVO attractionVO = new AttractionVO();
        ProvinceVO provinceVO = new ProvinceVO();
        CityVO cityVO = new CityVO();
        AreaVO areaVO = new AreaVO();
        StreetVO streetVO = new StreetVO();

        BeanUtils.copyProperties(attraction, attractionVO);
        BeanUtils.copyProperties(province, provinceVO);
        BeanUtils.copyProperties(city, cityVO);
        BeanUtils.copyProperties(area, areaVO);
        BeanUtils.copyProperties(street, streetVO);

        return new UserAttractionVO(userVO, attractionVO, provinceVO, cityVO, areaVO, streetVO);
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

    @Override
    public List<UserAttractionVO> getAllUserAttractions() {
        MPJLambdaWrapper<UserAttraction> wrapper = createWrapper();
        return userAttractionMapper.selectJoinList(UserAttractionDTO.class, wrapper).stream()
                .map(this::mapToUserAttractionVO).toList();
    }

    @Override
    public List<UserAttractionVO> getAllUnreviewedUserAttractions() {
        MPJLambdaWrapper<UserAttraction> wrapper = createWrapper()
                .eq(Attraction::getReviewed, 0);
        return userAttractionMapper.selectJoinList(UserAttractionDTO.class, wrapper).stream()
                .map(this::mapToUserAttractionVO).toList();
    }
}




