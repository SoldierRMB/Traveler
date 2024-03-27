package com.soldiersoft.traveler.model.dto;

import com.soldiersoft.traveler.entity.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAttractionDTO {
    private User user;
    private Attraction attraction;
    private Province province;
    private City city;
    private Area area;
    private Street street;
}
