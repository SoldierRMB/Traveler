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
 * 图片表
 *
 * @TableName t_image
 */
@TableName(value = "t_image")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Image implements Serializable {
    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 图片编号
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 图片路径
     */
    private String imagePath;
}