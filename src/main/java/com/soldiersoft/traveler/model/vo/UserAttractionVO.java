package com.soldiersoft.traveler.model.vo;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAttractionVO {
    @Valid
    @NotNull
    private UserVO userVO;
    @Valid
    @NotNull
    private AttractionVO attractionVO;
    private ProvinceVO provinceVO;
    private CityVO cityVO;
    private AreaVO areaVO;
    private StreetVO streetVO;
}
