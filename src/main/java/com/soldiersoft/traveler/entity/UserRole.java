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
 * 用户角色表
 *
 * @TableName t_user_role
 */
@TableName(value = "t_user_role")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRole implements Serializable {
    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 用户角色编号
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 用户编号
     */
    private Long userId;
    /**
     * 角色编号
     */
    private Integer roleId;
}