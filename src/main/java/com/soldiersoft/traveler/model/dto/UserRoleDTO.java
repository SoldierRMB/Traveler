package com.soldiersoft.traveler.model.dto;

import com.soldiersoft.traveler.entity.Role;
import com.soldiersoft.traveler.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleDTO {
    private Integer id;
    private User user;
    private Role role;
}
