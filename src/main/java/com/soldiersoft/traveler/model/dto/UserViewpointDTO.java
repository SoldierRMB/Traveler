package com.soldiersoft.traveler.model.dto;

import com.soldiersoft.traveler.entity.User;
import com.soldiersoft.traveler.entity.Viewpoint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserViewpointDTO {
    private User user;
    private Viewpoint viewpoint;
}
