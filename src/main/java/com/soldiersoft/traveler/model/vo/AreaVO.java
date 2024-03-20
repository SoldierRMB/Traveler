package com.soldiersoft.traveler.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AreaVO {
    private Long code;
    private String name;
    private Long provinceCode;
    private Long cityCode;
}
