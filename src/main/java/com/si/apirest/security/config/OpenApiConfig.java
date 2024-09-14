package com.si.apirest.security.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(
    info=@Info(
        title="Gestión documental"
    )
)
public class OpenApiConfig {
    
}
