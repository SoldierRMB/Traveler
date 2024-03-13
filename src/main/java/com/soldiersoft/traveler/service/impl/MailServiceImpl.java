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

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.soldiersoft.traveler.constant.RedisKeyConstants.EMAIL_CODE_KEY;

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
            throw new BizException("发送邮件失败");
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
        RedisUtil.setString(EMAIL_CODE_KEY + to, code, 5L, TimeUnit.MINUTES);
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
        return Optional.ofNullable(RedisUtil.getString(EMAIL_CODE_KEY + to))
                .map(mailCode -> {
                    if (mailCode.equals(code)) {
                        RedisUtil.delete(EMAIL_CODE_KEY + to);
                        return true;
                    }
                    return false;
                })
                .orElseThrow(() -> new BizException("验证码已过期", to));
    }
}
