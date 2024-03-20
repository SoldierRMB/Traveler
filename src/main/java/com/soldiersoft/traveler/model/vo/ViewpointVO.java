package com.soldiersoft.traveler.model.vo;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank
    private String viewpointName;
    @NotBlank
    private String description;
    @NotBlank
    private String location;
    @Digits(integer = 5, fraction = 2)
    private Double score;
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
