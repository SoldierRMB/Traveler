package com.soldiersoft.traveler;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.soldiersoft.traveler.mapper")
@OpenAPIDefinition(info = @Info(title = "Traveler", version = "1.0", description = "行者旅游在线预订平台"))
public class TravelerApplication {
    public static void main(String[] args) {
        SpringApplication.run(TravelerApplication.class, args);
    }
}
