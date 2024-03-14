package com.soldiersoft.traveler.model.dto;

import com.soldiersoft.traveler.entity.Area;
import com.soldiersoft.traveler.entity.City;
import com.soldiersoft.traveler.entity.Province;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StreetDTO {
    private String code;
    private String name;
    private Province province;
    private City city;
    private Area area;
}
