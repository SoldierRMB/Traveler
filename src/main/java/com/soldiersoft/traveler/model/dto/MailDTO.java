package com.soldiersoft.traveler.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MailDTO {
    private String to;
    private String from;
    private String subject;
    private String text;
}
