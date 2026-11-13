package com.sanm.gestionvet.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        // Esto agrega el botón "Authorize" en Swagger para pegar el token JWT
        String esquema = "bearerAuth";

        return new OpenAPI()
                .info(new Info()
                        .title("API Gestión Veterinaria San Marcos")
                        .version("1.0")
                        .description("API para la gestión de dueños, mascotas, " +
                                "servicios y consultas de la veterinaria")
                )
                .addSecurityItem(new SecurityRequirement().addList(esquema))
                .components(new Components()
                        .addSecuritySchemes(esquema, new SecurityScheme()
                                .name(esquema)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                        )
                );
    }
}
