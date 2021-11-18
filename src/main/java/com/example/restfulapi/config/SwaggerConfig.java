package com.example.restfulapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    //http://localhost:8080/v2/api-docs
    //http://localhost:8080/swagger-ui.html

    private static final Contact DEFAULT_CONTACT = new Contact("TK", "http://", "email");

    private static final ApiInfo DEFAULT_API_INFO = new ApiInfo("API title", "User management API","1.0",
            "urn:", DEFAULT_CONTACT,
            "Apache 2.0", "http://",
            new ArrayList<>());


    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2);
    }

}
