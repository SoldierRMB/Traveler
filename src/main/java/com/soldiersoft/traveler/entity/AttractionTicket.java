package com.soldiersoft.traveler.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 景点门票表
 *
 * @TableName t_attraction_ticket
 */
@TableName(value = "t_attraction_ticket")
@Data
public class AttractionTicket implements Serializable {
    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 景点门票编号
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 景点编号
     */
    private Long attractionId;
    /**
     * 门票编号
     */
    private Long ticketId;
}