package com.soldiersoft.traveler.model.dto;

import com.soldiersoft.traveler.entity.Menu;
import com.soldiersoft.traveler.entity.Role;
import com.soldiersoft.traveler.entity.RoleMenu;
import com.soldiersoft.traveler.entity.User;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserRoleDTO {
    private Integer id;
    private User user;
    private Role role;
    private RoleMenu roleMenu;
    private List<Menu> menuList;
}
