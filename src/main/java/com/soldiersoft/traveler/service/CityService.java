package com.soldiersoft.traveler.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.soldiersoft.traveler.entity.City;
import com.soldiersoft.traveler.model.vo.CityVO;

import java.util.List;

/**
 * @author Soldier_RMB
 * @description 针对表【t_cities(地级表)】的数据库操作Service
 * @createDate 2024-03-01 17:03:19
 */
public interface CityService extends IService<City> {

    List<CityVO> getCitiesByProvinceCode(Long provinceCode);
}
