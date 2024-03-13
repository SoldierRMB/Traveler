package com.soldiersoft.traveler.model.vo;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginVO {
    @NotNull
    private String username;
    @NotNull
    private String password;
}
