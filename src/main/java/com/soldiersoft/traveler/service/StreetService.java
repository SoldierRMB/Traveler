package com.soldiersoft.traveler.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.soldiersoft.traveler.entity.Street;
import com.soldiersoft.traveler.model.vo.LocationVO;
import com.soldiersoft.traveler.model.vo.StreetVO;

import java.util.List;

/**
 * @author Soldier_RMB
 * @description 针对表【t_streets(乡级表)】的数据库操作Service
 * @createDate 2024-03-01 17:03:19
 */
public interface StreetService extends IService<Street> {
    List<StreetVO> getStreetsByAreaCode(Long areaCode);

    LocationVO getPositionByStreetCode(Long streetCode);
}
