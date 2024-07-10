package com.almod.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Multifunctional project Api",
                description = "The project to reinforce knowledge", version = "1.0.0",
                contact = @Contact(
                        name = "Alex",
                        email = "jykov21@mail.ru",
                        url = "http://localhost:8080"
                )
        )
)
public class OpenApiConfig {

}