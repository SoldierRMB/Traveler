package com.soldiersoft.traveler.service.impl;

import cn.hutool.core.io.FileUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soldiersoft.traveler.entity.AttractionImage;
import com.soldiersoft.traveler.entity.Image;
import com.soldiersoft.traveler.exception.BizException;
import com.soldiersoft.traveler.mapper.AttractionImageMapper;
import com.soldiersoft.traveler.model.dto.AttractionDTO;
import com.soldiersoft.traveler.service.AttractionImageService;
import com.soldiersoft.traveler.service.AttractionService;
import com.soldiersoft.traveler.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Soldier_RMB
 * @description 针对表【t_attraction_image(景点图片表)】的数据库操作Service实现
 * @createDate 2024-04-17 15:09:44
 */
@Service
public class AttractionImageServiceImpl extends ServiceImpl<AttractionImageMapper, AttractionImage>
        implements AttractionImageService {
    private final AttractionService attractionService;
    private final ImageService imageService;

    @Value("${upload-path}")
    private String uploadPath;

    @Autowired
    public AttractionImageServiceImpl(AttractionService attractionService, ImageService imageService) {
        this.attractionService = attractionService;
        this.imageService = imageService;
    }

    @Override
    @Transactional
    public String uploadAttractionImage(MultipartFile file, Long attractionId, String username) {
        Map<Long, AttractionDTO> map = attractionService.getAttractionsMapByUsername(username);
        if (!map.containsKey(attractionId)) {
            throw new BizException("景点不存在");
        }
        Optional.ofNullable(lambdaQuery().eq(AttractionImage::getAttractionId, attractionId).one())
                .ifPresent(attractionImages -> {
                    throw new BizException("景点图片已存在");
                });
        File imageFilePath = FileUtil.mkdir(uploadPath + "/image");
        String extName = "." + FileUtil.extName(file.getOriginalFilename());
        String imageName = UUID.randomUUID() + extName;
        String imagePath;
        try {
            File imageFile = FileUtil.touch(imageFilePath, imageName);
            file.transferTo(imageFile);
            imagePath = imageFile.getPath();
            Image image = Image.builder()
                    .imagePath(imagePath)
                    .build();
            imageService.save(image);
            AttractionImage attractionImage = AttractionImage.builder()
                    .attractionId(attractionId)
                    .imageId(image.getId())
                    .build();
            save(attractionImage);
        } catch (Exception e) {
            throw new BizException("图片上传失败");
        }
        return "图片上传成功";
    }

    @Override
    public String updateAttractionImage(MultipartFile file, Long attractionId, String username) {
        Map<Long, AttractionDTO> map = attractionService.getAttractionsMapByUsername(username);
        if (!map.containsKey(attractionId)) {
            throw new BizException("景点不存在");
        }
        File imageFilePath = FileUtil.mkdir(uploadPath + "/image");
        String extName = "." + FileUtil.extName(file.getOriginalFilename());
        String imageName = UUID.randomUUID() + extName;
        AttractionImage attractionImage = lambdaQuery().eq(AttractionImage::getAttractionId, attractionId).oneOpt()
                .orElseThrow(() -> new BizException("图片不存在"));
        Image image = imageService.lambdaQuery().eq(Image::getId, attractionImage.getImageId()).one();
        String imagePath;
        try {
            FileUtil.del(FileUtil.file(image.getImagePath()));
            File imageFile = FileUtil.touch(imageFilePath, imageName);
            file.transferTo(imageFile);
            imagePath = imageFile.getPath();
            Image newImage = Image.builder()
                    .id(image.getId())
                    .imagePath(imagePath)
                    .build();
            imageService.updateById(newImage);
            updateById(attractionImage);
        } catch (Exception e) {
            throw new BizException("图片更新失败");
        }
        return "图片更新成功";
    }

    @Override
    public ResponseEntity<Resource> getAttractionImageByAttractionId(Long attractionId) {
        Long imageId = lambdaQuery().eq(AttractionImage::getAttractionId, attractionId).one().getImageId();
        String imagePath = imageService.lambdaQuery().eq(Image::getId, imageId).one().getImagePath();
        if (FileUtil.exist(imagePath)) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", String.valueOf(MediaType.IMAGE_JPEG));
            headers.add("Content-Type", String.valueOf(MediaType.IMAGE_PNG));
            Path path = Paths.get(imagePath);
            Resource resource;
            try {
                resource = new UrlResource(path.toUri());
            } catch (MalformedURLException e) {
                throw new BizException();
            }
            return ResponseEntity.ok().headers(headers).body(resource);
        } else {
            throw new BizException("图片获取失败");
        }
    }
}




