package com.soldiersoft.traveler.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class RedisDTO {
    private Object data;
    private LocalDateTime expireTime;
}
