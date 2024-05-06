package com.soldiersoft.traveler.model.vo;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RatingVO {
    private Long id;
    @Pattern(regexp = "[1-5]")
    private Integer rating;
    private String content;
    private Long userId;
    private Long orderId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
