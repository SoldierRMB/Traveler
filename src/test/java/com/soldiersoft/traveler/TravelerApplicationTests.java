package com.soldiersoft.traveler;

import com.soldiersoft.traveler.model.dto.MailDTO;
import com.soldiersoft.traveler.service.MailService;
import com.soldiersoft.traveler.service.RoleMenuService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TravelerApplicationTests {
    @Autowired
    private MailService mailService;
    @Autowired
    private RoleMenuService roleMenuService;

    @Test
    void testSendEmail() {
        String code = mailService.generateCode();
        MailDTO mailDTO = MailDTO.builder()
                .to("soldierrmb0510@foxmail.com")
                .from("行者")
                .subject("【行者】登录验证码")
                .text("您的验证码是：" + code)
                .build();
        mailService.sendEmail(mailDTO);
    }

    @Test
    void getRoleMenuByRoleId() {
        System.out.println(roleMenuService.getRoleMenuByRoleId(1));
    }
}
