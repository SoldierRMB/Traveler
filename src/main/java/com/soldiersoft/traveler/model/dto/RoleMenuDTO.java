package com.soldiersoft.traveler.model.dto;

import com.soldiersoft.traveler.entity.Menu;
import com.soldiersoft.traveler.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleMenuDTO {
    private Integer id;
    private Role role;
    private List<Menu> menuList;
}
