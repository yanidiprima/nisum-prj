package com.sinum.user.configuration;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;

@Configuration
@SecurityScheme(
        type = SecuritySchemeType.APIKEY,
        name = "Authorization",
        paramName = "Authorization",
        in = SecuritySchemeIn.HEADER)
public class OpenApiConfig {
	
	/**
	 * 
	 * 
	 * */
 	@Bean
    public OpenAPI getOpenApiDefinition(){
        return new OpenAPI()
                .info(new Info()
                        .title("Nisum Application")
                        .version("1.0")
                        .license(new License().name("(C) Copyright Nisum").url("https://nisum.com/"))
                )
                .security(Collections.singletonList(new SecurityRequirement().addList("Authorization")));
    }
}
