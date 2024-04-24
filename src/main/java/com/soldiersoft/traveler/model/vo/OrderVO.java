package com.soldiersoft.traveler.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderVO {
    private Long id;
    private Long userId;
    private Long ticketId;
    private Integer status;
    private Integer quantity;
    private BigDecimal amount;
    private LocalDateTime orderTime;
}
