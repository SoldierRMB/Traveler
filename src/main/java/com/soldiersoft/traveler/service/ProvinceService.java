package com.soldiersoft.traveler.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.soldiersoft.traveler.entity.Province;
import com.soldiersoft.traveler.model.vo.ProvinceVO;

import java.util.List;

/**
 * @author Soldier_RMB
 * @description 针对表【t_provinces(省级表)】的数据库操作Service
 * @createDate 2024-03-01 17:03:19
 */
public interface ProvinceService extends IService<Province> {

    List<ProvinceVO> getProvinces();
}
