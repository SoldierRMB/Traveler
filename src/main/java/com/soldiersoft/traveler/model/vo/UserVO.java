package com.soldiersoft.traveler.model.vo;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserVO {
    private Long id;
    private String username;
    private String password;
    @Email
    private String email;
    private String nickname;
    // 2.Staff 3.Tourist
    private Integer userType;
    private String code;
}
