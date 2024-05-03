package com.soldiersoft.traveler.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttractionAnnouncementVO {
    private Long id;
    private String title;
    private String content;
    private Long attractionId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
