package com.soldiersoft.traveler.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.soldiersoft.traveler.entity.Area;
import com.soldiersoft.traveler.entity.City;
import com.soldiersoft.traveler.entity.Province;
import com.soldiersoft.traveler.entity.Street;
import com.soldiersoft.traveler.mapper.StreetMapper;
import com.soldiersoft.traveler.model.dto.StreetDTO;
import com.soldiersoft.traveler.model.vo.PositionVO;
import com.soldiersoft.traveler.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Soldier_RMB
 * @description 针对表【t_streets(乡级表)】的数据库操作Service实现
 * @createDate 2024-03-01 17:03:19
 */
@Service
public class StreetServiceImpl extends ServiceImpl<StreetMapper, Street>
        implements StreetService {
    private final StreetMapper streetMapper;

    @Autowired
    public StreetServiceImpl(StreetMapper streetMapper) {
        this.streetMapper = streetMapper;
    }

    @Override
    public PositionVO getPositionByStreetCode(Long streetCode) {
        MPJLambdaWrapper<Street> wrapper = new MPJLambdaWrapper<Street>()
                .selectAll(Street.class)
                .selectAssociation(Province.class, StreetDTO::getProvince)
                .selectAssociation(City.class, StreetDTO::getCity)
                .selectAssociation(Area.class, StreetDTO::getArea)
                .leftJoin(Province.class, Province::getCode, Street::getProvinceCode)
                .leftJoin(City.class, City::getCode, Street::getCityCode)
                .leftJoin(Area.class, Area::getCode, Street::getAreaCode)
                .eq(Street::getCode, streetCode);
        StreetDTO streetDTO = streetMapper.selectJoinOne(StreetDTO.class, wrapper);
        Street street = Street.builder()
                .code(streetDTO.getCode())
                .name(streetDTO.getName())
                .provinceCode(streetDTO.getProvince().getCode())
                .cityCode(streetDTO.getCity().getCode())
                .areaCode(streetDTO.getArea().getCode())
                .build();
        return PositionVO.builder()
                .province(streetDTO.getProvince())
                .city(streetDTO.getCity())
                .area(streetDTO.getArea())
                .street(street)
                .build();
    }
}




