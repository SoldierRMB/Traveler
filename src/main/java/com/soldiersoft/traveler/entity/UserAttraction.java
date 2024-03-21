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
 * 用户景点表
 *
 * @TableName t_user_attraction
 */
@TableName(value = "t_user_attraction")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAttraction implements Serializable {
    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 用户景点编号
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 用户编号
     */
    private Long userId;
    /**
     * 景点编号
     */
    private Long attractionId;
}