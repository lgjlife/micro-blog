package com.cloud.frame.scheduler.quartz.config.swagger;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @program: top-parent
 * @description: SwaggerConfig 配置类
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-11-19 14:19
 **/
@EnableSwagger2                // Swagger的开关，表示已经启用Swagger
@Configuration                 // 声明当前配置类
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //为当前包路径
                .apis(RequestHandlerSelectors.basePackage("com.cloud.frame.scheduler.quartz.controller"))
                .paths(PathSelectors.any())
                .build();
    }
    //构建 api文档的详细信息函数,注意这里的注解引用的是哪个
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("Cloud-Frame Scheduler-Quartz 任务调度模块 RESTful API 文档")
                //创建人
                .contact(new Contact("lgj-blog", "https://blog.csdn.net/u011676300", ""))
                //版本号
                .version("1.0")
                //描述
                .description("API 描述")
                .build()
                ;
    }

    @Bean
    UiConfiguration uiConfig() {
        return new UiConfiguration(null, "list", "alpha", "schema",
                UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS, false, true, 60000L);
    }


}
