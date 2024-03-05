package com.soldiersoft.traveler.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 景点表
 *
 * @TableName t_viewpoint
 */
@TableName(value = "t_viewpoint")
@Data
public class Viewpoint implements Serializable {
    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 景点编号
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 景点名称
     */
    private String viewpointName;
    /**
     * 景点描述
     */
    private String description;
    /**
     * 景点评分
     */
    private Double score;
    /**
     * 省级编号
     */
    private Long provinceCode;
    /**
     * 地级编号
     */
    private Long cityCode;
    /**
     * 县级编号
     */
    private Long areaCode;
    /**
     * 乡级编号
     */
    private Long streetCode;
}