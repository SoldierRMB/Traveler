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
public class UserViewpointVO {
    @Valid
    @NotNull
    private UserVO userVO;
    @Valid
    @NotNull
    private ViewpointVO viewpointVO;
}
