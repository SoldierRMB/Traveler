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
 * 景点图片表
 *
 * @TableName t_attraction_image
 */
@TableName(value = "t_attraction_image")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttractionImage implements Serializable {
    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 景点图片编号
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 景点编号
     */
    private Long attractionId;
    /**
     * 图片编号
     */
    private Long imageId;
}