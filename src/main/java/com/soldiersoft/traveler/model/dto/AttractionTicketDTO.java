package com.soldiersoft.traveler.model.dto;

import com.soldiersoft.traveler.entity.Attraction;
import com.soldiersoft.traveler.entity.Ticket;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttractionTicketDTO {
    private Long id;
    private Attraction attraction;
    private Ticket ticket;
}
