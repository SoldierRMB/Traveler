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
 * 评论表
 *
 * @TableName t_comment
 */
@TableName(value = "t_comment")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment implements Serializable {
    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 评论编号
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 评论类型 1.主题评论 2.景点评论
     */
    private Integer commentType;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 评论用户编号
     */
    private Long fromUserId;
    /**
     * 主题编号 0.非主题评论
     */
    private Long topicId;
    /**
     * 景点评论 0.非景点评论
     */
    private Long viewpointId;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}