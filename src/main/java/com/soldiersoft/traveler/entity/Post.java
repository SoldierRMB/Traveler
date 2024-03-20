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
import java.time.LocalDateTime;

/**
 * 动态表
 *
 * @TableName t_post
 */
@TableName(value = "t_post")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post implements Serializable {
    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 动态编号
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 动态标题
     */
    private String title;
    /**
     * 动态内容
     */
    private String content;
    /**
     * 用户编号
     */
    private Long userId;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}