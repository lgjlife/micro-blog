package com.swagger.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "swagger")
public class SwaggerProperties {

    private String enable;
    private String basePackage = "com";
    private String title = "swagger";

    private  String contactName;
    private  String contactUrl;
    private  String contactEmail;

    private String version;

    private  String description;
    private  String termsOfServiceUrl;
    private  String license;
    private  String licenseUrl;

}
