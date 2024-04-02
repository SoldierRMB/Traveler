package com.soldiersoft.traveler.model.vo;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketVO {
    private Long id;
    @NotBlank
    private String ticketName;
    @NotBlank
    @Digits(integer=10, fraction=2)
    private BigDecimal price;
    @NotBlank
    private Integer ticketType;
    @NotBlank
    private String description;
}
