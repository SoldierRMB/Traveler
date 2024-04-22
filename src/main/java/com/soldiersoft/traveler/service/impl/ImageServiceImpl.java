package com.soldiersoft.traveler.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soldiersoft.traveler.entity.Image;
import com.soldiersoft.traveler.mapper.ImageMapper;
import com.soldiersoft.traveler.service.ImageService;
import org.springframework.stereotype.Service;

/**
 * @author Soldier_RMB
 * @description 针对表【t_image(图片表)】的数据库操作Service实现
 * @createDate 2024-04-17 15:09:44
 */
@Service
public class ImageServiceImpl extends ServiceImpl<ImageMapper, Image>
        implements ImageService {

}




