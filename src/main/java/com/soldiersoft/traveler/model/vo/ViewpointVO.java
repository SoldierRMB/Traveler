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
public class ViewpointVO {
    private Long id;
    private String viewpointName;
    private String Description;
    private Double score;
    private Long provinceCode;
    private Long cityCode;
    private Long areaCode;
    private Long streetCode;
    private Integer reviewed;
    private Integer isDeleted;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
