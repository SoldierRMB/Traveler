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

/**
 * 门票表
 *
 * @TableName t_ticket
 */
@TableName(value = "t_ticket")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ticket implements Serializable {
    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 门票编号
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 门票名称
     */
    private String ticketName;
    /**
     * 门票价格
     */
    private BigDecimal price;
    /**
     * 门票类型 1.全价票 2.优惠票
     */
    private Integer ticketType;
    /**
     * 门票描述
     */
    private String description;
    /**
     * 是否删除 0.未删除 1.已删除
     */
    private Integer isDeleted;
}