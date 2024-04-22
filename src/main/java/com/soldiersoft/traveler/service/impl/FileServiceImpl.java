package com.soldiersoft.traveler.service.impl;

import cn.hutool.core.io.FileUtil;
import com.soldiersoft.traveler.exception.BizException;
import com.soldiersoft.traveler.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileServiceImpl implements FileService {
    @Value("${upload-path}")
    private String uploadPath;

    @Override
    public ResponseEntity<Resource> getImage(String imageName) {
        String imagePath = uploadPath + "/image/" + imageName;
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
