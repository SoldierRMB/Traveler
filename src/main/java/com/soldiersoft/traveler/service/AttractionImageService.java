package com.soldiersoft.traveler.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.soldiersoft.traveler.entity.AttractionImage;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Soldier_RMB
 * @description 针对表【t_attraction_image(景点图片表)】的数据库操作Service
 * @createDate 2024-04-17 15:09:44
 */
public interface AttractionImageService extends IService<AttractionImage> {

    String uploadAttractionImage(MultipartFile file, Long attractionId, String username);

    String updateAttractionImage(MultipartFile file, Long attractionId, String username);

    ResponseEntity<Resource> getAttractionImageByAttractionId(Long attractionId);
}
