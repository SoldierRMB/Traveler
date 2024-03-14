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
 * 省级表
 *
 * @TableName t_provinces
 */
@TableName(value = "t_provinces")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Province implements Serializable {
    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 省级编号
     */
    @TableId(type = IdType.INPUT)
    private Long code;
    /**
     * 省级名称
     */
    private String name;
}