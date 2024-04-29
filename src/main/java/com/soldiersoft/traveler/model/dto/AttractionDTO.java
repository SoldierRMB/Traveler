package com.soldiersoft.traveler.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttractionDTO {
    private Long id;
    private String attractionName;
    private String description;
    private String location;
    private BigDecimal rate;
    private Long provinceCode;
    private Long cityCode;
    private Long areaCode;
    private Long streetCode;
    private Integer reviewed;
    private Integer isDeleted;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
