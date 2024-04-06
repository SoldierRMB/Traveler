package com.soldiersoft.traveler.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    private Long userId;
    private Long ticketId;
    private Integer status;
    private Integer quantity;
    private BigDecimal amount;
    private LocalDateTime orderTime;
}
