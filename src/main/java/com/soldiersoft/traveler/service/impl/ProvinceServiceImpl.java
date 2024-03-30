package com.soldiersoft.traveler.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soldiersoft.traveler.entity.Province;
import com.soldiersoft.traveler.mapper.ProvinceMapper;
import com.soldiersoft.traveler.model.vo.ProvinceVO;
import com.soldiersoft.traveler.service.ProvinceService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Soldier_RMB
 * @description 针对表【t_provinces(省级表)】的数据库操作Service实现
 * @createDate 2024-03-01 17:03:19
 */
@Service
public class ProvinceServiceImpl extends ServiceImpl<ProvinceMapper, Province>
        implements ProvinceService {

    @Override
    public List<ProvinceVO> getProvinces() {
        return Optional.ofNullable(lambdaQuery().list())
                .map(provinces -> provinces.stream().map(province ->{
                    ProvinceVO provinceVO = new ProvinceVO();
                    BeanUtils.copyProperties(province, provinceVO);
                    return provinceVO;
                }).toList())
                .orElse(null);
    }
}




