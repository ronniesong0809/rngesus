package com.ronsong.rngesus.common.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    public ApiInfo apiInfo() {
        ApiInfo build = new ApiInfoBuilder()
                .title("RNGesus API Documentation")
                .description("This is the API Documentation for the RNGesus Community")
                .version("v1.0")
                .contact(new Contact("Ronnie Song", "https://www.ronsong.me", "ronniesong0809@gmail.com"))
                .build();
        return build;
    }

    @Bean
    public Docket webApiConfig() {
        Docket build = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ronsong.rngesus.controller"))
                .paths(PathSelectors.any())
                .build();
        return build;
    }
}
