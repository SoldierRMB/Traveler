package com.soldiersoft.traveler.model.dto;

import com.soldiersoft.traveler.entity.Ticket;
import com.soldiersoft.traveler.entity.User;
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
public class OrderDTO {
    private Long id;
    private User user;
    private Ticket ticket;
    private Integer status;
    private Integer quantity;
    private BigDecimal amount;
    private LocalDateTime orderTime;
}
