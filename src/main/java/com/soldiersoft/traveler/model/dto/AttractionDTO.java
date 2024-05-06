package com.soldiersoft.traveler.model.dto;

import com.soldiersoft.traveler.entity.Area;
import com.soldiersoft.traveler.entity.City;
import com.soldiersoft.traveler.entity.Province;
import com.soldiersoft.traveler.entity.Street;
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
    private BigDecimal rating;
    private Province province;
    private City city;
    private Area area;
    private Street street;
    private Integer reviewed;
    private Integer isDeleted;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
