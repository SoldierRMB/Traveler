package com.soldiersoft.traveler.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
    private String username;
    private String password;
    private String email;
    // 1.admin 2. staff 3.tourist
    private Integer userType;
}
