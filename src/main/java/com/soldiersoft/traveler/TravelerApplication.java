package com.soldiersoft.traveler;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.soldiersoft.traveler.mapper")
public class TravelerApplication {
    public static void main(String[] args) {
        SpringApplication.run(TravelerApplication.class, args);
    }
}
