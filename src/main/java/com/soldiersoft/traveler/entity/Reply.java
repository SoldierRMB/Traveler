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
 * 回复表
 *
 * @TableName t_Reply
 */
@TableName(value = "t_reply")
@Data
public class Reply implements Serializable {
    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 回复编号
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 评论编号
     */
    private Long commentId;
    /**
     * 回复内容
     */
    private String content;
    /**
     * 回复用户编号
     */
    private Long fromUserId;
    /**
     * 目标用户编号
     */
    private Long toUserId;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}