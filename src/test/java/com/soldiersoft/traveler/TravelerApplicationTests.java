package com.soldiersoft.traveler;

import com.soldiersoft.traveler.entity.Attraction;
import com.soldiersoft.traveler.model.dto.MailDTO;
import com.soldiersoft.traveler.service.AttractionTicketService;
import com.soldiersoft.traveler.service.MailService;
import com.soldiersoft.traveler.service.StreetService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Objects;

@SpringBootTest
class TravelerApplicationTests {
    @Autowired
    private MailService mailService;
    @Autowired
    private StreetService streetService;
    @Autowired
    private AttractionTicketService attractionTicketService;

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
    void testGetPositionByStreetCode() {
        System.out.println(streetService.getPositionByStreetCode(110101002L));
    }

    @Test
    void idsToBeanIds() {
        List<Long> attractionIds = List.of(1L, 2L, 3L);
        List<Attraction> attractions = attractionIds.stream()
                .map(id -> com.soldiersoft.traveler.entity.Attraction.builder().id(id).build())
                .filter(Objects::nonNull)
                .toList();
        System.out.println(attractions);
    }

    @Test
    void testGetAttractionTicketsByAttractionId() {
        System.out.println(attractionTicketService.getAttractionTicketsByAttractionId(1L));
    }
}
