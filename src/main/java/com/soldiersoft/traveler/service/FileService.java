package com.soldiersoft.traveler.service;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

public interface FileService {
    ResponseEntity<Resource> getImage(String imageName);
}
