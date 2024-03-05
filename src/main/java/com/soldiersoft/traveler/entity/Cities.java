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
 * 地级表
 *
 * @TableName t_cities
 */
@TableName(value = "t_cities")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cities implements Serializable {
    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 地级编号
     */
    @TableId(type = IdType.INPUT)
    private String code;
    /**
     * 地级名称
     */
    private String name;
    /**
     * 省级编号
     */
    private String provinceCode;
}