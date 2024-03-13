package com.soldiersoft.traveler.service.impl;

import com.soldiersoft.traveler.exception.BizException;
import com.soldiersoft.traveler.model.dto.MailDTO;
import com.soldiersoft.traveler.service.MailService;
import com.soldiersoft.traveler.util.RedisUtil;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class MailServiceImpl implements MailService {
    private final JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    public MailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public Boolean sendEmail(MailDTO mailDTO) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(mailDTO.getTo());
            helper.setFrom(from, mailDTO.getFrom());
            helper.setSubject(mailDTO.getSubject());
            helper.setText(mailDTO.getText(), false);
            javaMailSender.send(message);
            return true;
        } catch (Exception e) {
            throw new BizException("发送邮件失败", e);
        }
    }

    @Override
    public String generateCode() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(6);
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    @Override
    public Boolean sendCode(String to) {
        String code = generateCode();
        RedisUtil.setString("registerCode:email:" + to, code, 5L, TimeUnit.MINUTES);
        MailDTO mailDTO = MailDTO.builder()
                .to(to)
                .from("行者")
                .subject("【行者】登录验证码")
                .text("您的验证码是：" + code + "\n" + "请勿外传，验证码5分钟内有效")
                .build();
        return sendEmail(mailDTO);
    }

    @Override
    public Boolean verifyCode(String to, String code) {
        String mailCode = RedisUtil.getString("registerCode:email:" + to);
        RedisUtil.delete("registerCode:email:" + to);
        return mailCode.equals(code);
    }
}
