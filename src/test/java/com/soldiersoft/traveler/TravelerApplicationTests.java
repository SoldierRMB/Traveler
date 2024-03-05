package com.soldiersoft.traveler;

import com.soldiersoft.traveler.service.UserRoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TravelerApplicationTests {
    @Autowired
    private UserRoleService userRoleService;

    @Test
    void queryUserRoleByUserId() {
        System.out.println(userRoleService.queryUserRoleByUserId(1L));
    }
}
