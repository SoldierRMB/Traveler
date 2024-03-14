package com.soldiersoft.traveler.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soldiersoft.traveler.entity.City;
import com.soldiersoft.traveler.mapper.CityMapper;
import com.soldiersoft.traveler.service.CityService;
import org.springframework.stereotype.Service;

/**
 * @author Soldier_RMB
 * @description 针对表【t_cities(地级表)】的数据库操作Service实现
 * @createDate 2024-03-01 17:03:19
 */
@Service
public class CityServiceImpl extends ServiceImpl<CityMapper, City>
        implements CityService {

}




