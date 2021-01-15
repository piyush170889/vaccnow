package com.vaccnow.vaccinationplans.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .groupName("vn-api")
        .select()
        .apis(
        		RequestHandlerSelectors
        			.basePackage("com.vaccnow.vaccinationplans.controllers"))
        .paths(
        		PathSelectors.any())
        .build()
        .apiInfo(
        		apiInfo());
  }

  private ApiInfo apiInfo() {
    return new ApiInfo(
        "Rest API for VaccNow",
        "API for use of within VaccNow",
        "1.0",
        "Terms of service",
        new Contact("IT team", "www.vaccnow.com", "admin@vaccnow.com"),
        "License of API",
        "www.vaccnow.com",
        Collections.emptyList());
  }
}
