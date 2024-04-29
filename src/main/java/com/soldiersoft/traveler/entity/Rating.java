package com.soldiersoft.traveler.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 评分表
 *
 * @TableName t_rating
 */
@TableName(value = "t_rating")
@Data
public class Rating implements Serializable {
    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 评分编号
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 评分
     */
    private Object rating;
    /**
     * 评价内容
     */
    private String content;
    /**
     * 用户编号
     */
    private Long userId;
    /**
     * 景点编号
     */
    private Long attractionId;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}