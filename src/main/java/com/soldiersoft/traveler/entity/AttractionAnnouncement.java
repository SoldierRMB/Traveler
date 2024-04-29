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
 * 景点公告表
 *
 * @TableName t_attraction_announcement
 */
@TableName(value = "t_attraction_announcement")
@Data
public class AttractionAnnouncement implements Serializable {
    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 景点公告编号
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 景点公告标题
     */
    private String title;
    /**
     * 景点公告内容
     */
    private String content;
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