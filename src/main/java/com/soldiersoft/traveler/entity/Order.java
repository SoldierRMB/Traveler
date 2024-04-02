package com.soldiersoft.traveler.entity;

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
 * 订单表
 *
 * @TableName t_order
 */
@TableName(value = "t_order")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {
    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 订单编号
     */
    @TableId
    private byte[] id;
    /**
     * 用户编号
     */
    private Long userId;
    /**
     * 门票编号
     */
    private Long ticketId;
    /**
     * 订单状态 1.待支付 2.已支付 3.已完成 4.已取消
     */
    private Integer status;
    /**
     * 门票数量
     */
    private Integer quantity;
    /**
     * 订单金额
     */
    private BigDecimal amount;
    /**
     * 订单时间
     */
    private LocalDateTime orderTime;
}