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
 * 乡级表
 *
 * @TableName t_streets
 */
@TableName(value = "t_streets")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Street implements Serializable {
    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 乡级编号
     */
    @TableId(type = IdType.INPUT)
    private String code;
    /**
     * 乡级名称
     */
    private String name;
    /**
     * 省级编号
     */
    private String provinceCode;
    /**
     * 地级编号
     */
    private String cityCode;
    /**
     * 县级编号
     */
    private String areaCode;
}