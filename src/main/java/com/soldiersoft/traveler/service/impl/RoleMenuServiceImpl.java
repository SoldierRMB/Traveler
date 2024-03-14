package com.soldiersoft.traveler.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.soldiersoft.traveler.entity.Menu;
import com.soldiersoft.traveler.entity.Role;
import com.soldiersoft.traveler.entity.RoleMenu;
import com.soldiersoft.traveler.mapper.RoleMenuMapper;
import com.soldiersoft.traveler.model.dto.RoleMenuDTO;
import com.soldiersoft.traveler.service.RoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Soldier_RMB
 * @description 针对表【t_role_menu(角色菜单表)】的数据库操作Service实现
 * @createDate 2024-02-04 16:13:12
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu>
        implements RoleMenuService {
    private final RoleMenuMapper roleMenuMapper;

    @Autowired
    public RoleMenuServiceImpl(RoleMenuMapper roleMenuMapper) {
        this.roleMenuMapper = roleMenuMapper;
    }

    @Override
    public List<RoleMenuDTO> getRoleMenuByRoleId(Integer roleId) {
        MPJLambdaWrapper<RoleMenu> wrapper = new MPJLambdaWrapper<RoleMenu>()
                .selectAll(RoleMenu.class)
                .selectAssociation(Role.class, RoleMenuDTO::getRole)
                .selectCollection(Menu.class, RoleMenuDTO::getMenuList)
                .leftJoin(Role.class, Role::getId, RoleMenu::getRoleId)
                .leftJoin(Menu.class, Menu::getId, RoleMenu::getMenuId)
                .eq(RoleMenu::getRoleId, roleId);
        return roleMenuMapper.selectJoinList(RoleMenuDTO.class, wrapper);
    }
}