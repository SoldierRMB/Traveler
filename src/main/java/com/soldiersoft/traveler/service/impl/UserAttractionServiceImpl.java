package com.soldiersoft.traveler.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.soldiersoft.traveler.entity.*;
import com.soldiersoft.traveler.mapper.UserAttractionMapper;
import com.soldiersoft.traveler.model.dto.UserAttractionDTO;
import com.soldiersoft.traveler.model.dto.UserDTO;
import com.soldiersoft.traveler.model.vo.*;
import com.soldiersoft.traveler.service.UserAttractionService;
import com.soldiersoft.traveler.service.UserService;
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
    private final UserService userService;

    @Autowired
    public UserAttractionServiceImpl(UserAttractionMapper userAttractionMapper, UserService userService) {
        this.userAttractionMapper = userAttractionMapper;
        this.userService = userService;
    }

    private static MPJLambdaWrapper<UserAttraction> getUserAttractionMPJLambdaWrapper() {
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
    public List<UserAttractionVO> getUserAttractions(Boolean reviewed) {
        MPJLambdaWrapper<UserAttraction> wrapper = getUserAttractionMPJLambdaWrapper()
                .func(attraction -> attraction.in(Attraction::getReviewed,
                        reviewed ? 1 : 0, reviewed ? 2 : null));
        return userAttractionMapper.selectJoinList(UserAttractionDTO.class, wrapper).stream()
                .map(this::mapToUserAttractionVO).toList();
    }

    @Override
    public List<UserAttractionVO> getUserAttractionsByUsername(String username) {
        UserDTO userDTO = userService.getUserByUsername(username);
        MPJLambdaWrapper<UserAttraction> wrapper = getUserAttractionMPJLambdaWrapper()
                .eq(UserAttraction::getUserId, userDTO.getId());
        return userAttractionMapper.selectJoinList(UserAttractionDTO.class, wrapper).stream()
                .map(this::mapToUserAttractionVO).toList();
    }
}




