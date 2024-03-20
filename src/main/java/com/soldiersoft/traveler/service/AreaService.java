package com.soldiersoft.traveler.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.soldiersoft.traveler.entity.Area;
import com.soldiersoft.traveler.model.vo.AreaVO;

import java.util.List;

/**
 * @author Soldier_RMB
 * @description 针对表【t_areas(县级表)】的数据库操作Service
 * @createDate 2024-03-01 17:03:19
 */
public interface AreaService extends IService<Area> {

    List<AreaVO> getAreasByCityCode(Long cityCode);
}
