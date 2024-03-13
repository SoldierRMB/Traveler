package com.soldiersoft.traveler.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MailDTO {
    private String to;
    private String from;
    private String subject;
    private String text;
}
