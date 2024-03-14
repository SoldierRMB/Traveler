package com.soldiersoft.traveler.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(info())
                .addSecurityItem(securityItem())
                .schemaRequirement("JWT", securityScheme());
    }

    private Info info() {
        return new Info()
                .title("Traveler - API文档")
                .description("行者旅游在线预订平台")
                .version("1.0");
    }

    private SecurityRequirement securityItem() {
        return new SecurityRequirement().addList("JWT");
    }

    private SecurityScheme securityScheme() {
        return new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .name("JWT")
                .scheme("Bearer")
                .in(SecurityScheme.In.HEADER);
    }
}
