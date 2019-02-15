package com.cloud.frame.cloudzuul.config.swagger;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: top-parent
 * @description:
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-11-19 14:47
 **/
@Component
@Primary
public class DocumentationConfig implements SwaggerResourcesProvider {
    @Override
    public List<SwaggerResource> get() {
        List resources = new ArrayList<>();
        resources.add(swaggerResource("用户权限系统", "/v10/server-api/v2/api-docs", "2.0"));
      //  resources.add(swaggerResource("网关中心", "/", "2.0"));
        resources.add(swaggerResource("用户权限服务提供系统", "/v10/security-provider-api/v2/api-docs", "2.0"));
        resources.add(swaggerResource("任务调度模块", "/v10/scheduler-quartz-api/v2/api-docs", "2.0"));

        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }
}

