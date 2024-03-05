package com.soldiersoft.traveler.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * 角色菜单表
 *
 * @TableName t_role_menu
 */
@TableName(value = "t_role_menu")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleMenu implements Serializable {
    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 菜单角色编号
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 角色编号
     */
    private Integer roleId;
    /**
     * 菜单编号
     */
    private Integer menuId;
}