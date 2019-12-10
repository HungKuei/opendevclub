package com.opendev.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Primary
public class SwaggerConfig implements SwaggerResourcesProvider {

    @Override
    public List<SwaggerResource> get() {
        List resources = new ArrayList();
        resources.add(swaggerResource("user-server", "/user-server/v2/api-docs", "1.0"));
        resources.add(swaggerResource("pay-server", "/pay-server/v2/api-docs", "1.0"));
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location, String version){
        SwaggerResource resource = new SwaggerResource();
        resource.setName(name);
        resource.setLocation(location);
        resource.setSwaggerVersion(version);
        return resource;
    }
}
