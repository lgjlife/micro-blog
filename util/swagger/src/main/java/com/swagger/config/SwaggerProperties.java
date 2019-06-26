package com.swagger.config;


import com.sun.istack.internal.NotNull;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "swagger")
public final  class SwaggerProperties {

    @NotNull
    public final  String pathPrefix;
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
