package com.soldiersoft.traveler.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soldiersoft.traveler.entity.RoleMenu;
import com.soldiersoft.traveler.mapper.RoleMenuMapper;
import com.soldiersoft.traveler.service.RoleMenuService;
import org.springframework.stereotype.Service;

/**
 * @author Soldier_RMB
 * @description 针对表【t_role_menu(角色菜单表)】的数据库操作Service实现
 * @createDate 2024-02-04 16:13:12
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu>
        implements RoleMenuService {

}