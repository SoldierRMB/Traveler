package com.soldiersoft.traveler.model.vo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserVO {
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    @Email
    private String email;
}
