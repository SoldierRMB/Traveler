package com.soldiersoft.traveler.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttractionTicketVO {
    private AttractionVO attractionVO;
    private TicketVO ticketVO;
}
