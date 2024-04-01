package com.soldiersoft.traveler.model.vo;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
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
public class AttractionVO {
    private Long id;
    @NotBlank
    private String attractionName;
    @NotBlank
    private String description;
    @NotBlank
    private String location;
    @Digits(integer = 5, fraction = 2)
    private BigDecimal score;
    @NotBlank
    private Long provinceCode;
    @NotBlank
    private Long cityCode;
    @NotBlank
    private Long areaCode;
    @NotBlank
    private Long streetCode;
    private Integer reviewed;
    private Integer isDeleted;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
