package com.soldiersoft.traveler.model.vo;

import com.soldiersoft.traveler.entity.Area;
import com.soldiersoft.traveler.entity.City;
import com.soldiersoft.traveler.entity.Province;
import com.soldiersoft.traveler.entity.Street;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PositionVO {
    private Province province;
    private City city;
    private Area area;
    private Street street;
}
