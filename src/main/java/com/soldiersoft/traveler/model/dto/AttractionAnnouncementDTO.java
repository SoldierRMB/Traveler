package com.soldiersoft.traveler.model.dto;

import com.soldiersoft.traveler.entity.Attraction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttractionAnnouncementDTO {
    private Long id;
    private String title;
    private String content;
    private Attraction attraction;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
