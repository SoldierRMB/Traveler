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
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 景点表
 *
 * @TableName t_attraction
 */
@TableName(value = "t_attraction")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Attraction implements Serializable {
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
    private String attractionName;
    /**
     * 景点描述
     */
    private String description;
    /**
     * 详细地址
     */
    private String location;
    /**
     * 景点评分
     */
    private BigDecimal score;
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
    /**
     * 是否审核 0.审核中 1.审核通过 2.审核不通过
     */
    private Integer reviewed;
    /**
     * 是否删除 0.未删除 1.已删除
     */
    private Integer isDeleted;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}