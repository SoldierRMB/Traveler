package com.soldiersoft.traveler.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soldiersoft.traveler.entity.Street;
import com.soldiersoft.traveler.mapper.StreetMapper;
import com.soldiersoft.traveler.service.StreetService;
import org.springframework.stereotype.Service;

/**
 * @author Soldier_RMB
 * @description 针对表【t_streets(乡级表)】的数据库操作Service实现
 * @createDate 2024-03-01 17:03:19
 */
@Service
public class StreetServiceImpl extends ServiceImpl<StreetMapper, Street>
        implements StreetService {

}




