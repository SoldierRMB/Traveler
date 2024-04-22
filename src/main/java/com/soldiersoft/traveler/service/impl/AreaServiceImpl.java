package com.soldiersoft.traveler.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soldiersoft.traveler.entity.Area;
import com.soldiersoft.traveler.mapper.AreaMapper;
import com.soldiersoft.traveler.model.vo.AreaVO;
import com.soldiersoft.traveler.service.AreaService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Soldier_RMB
 * @description 针对表【t_areas(县级表)】的数据库操作Service实现
 * @createDate 2024-03-01 17:03:19
 */
@Service
public class AreaServiceImpl extends ServiceImpl<AreaMapper, Area>
        implements AreaService {

    @Override
    public List<AreaVO> getAreasByCityCode(Long cityCode) {
        return Optional.ofNullable(lambdaQuery().eq(Area::getCityCode, cityCode).list())
                .map(list -> list.stream().map(area -> {
                    AreaVO areaVO = new AreaVO();
                    BeanUtils.copyProperties(area, areaVO);
                    return areaVO;
                }).toList())
                .orElse(null);
    }
}




