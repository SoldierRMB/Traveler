package com.soldiersoft.traveler.service;

import com.soldiersoft.traveler.model.dto.MailDTO;

public interface MailService {
    Boolean sendEmail(MailDTO mailDTO);

    String generateCode();

    Boolean sendCode(String to);

    Boolean verifyCode(String to, String code);
}
