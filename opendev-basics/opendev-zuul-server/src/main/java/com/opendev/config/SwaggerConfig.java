package com.opendev.config;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Primary
public class SwaggerConfig implements SwaggerResourcesProvider {

    @ApolloConfig
    private Config config;

    @Override
    public List<SwaggerResource> get() {
        return getSwaggerResources();
    }

    private SwaggerResource swaggerResource(String name, String location, String version){
        SwaggerResource resource = new SwaggerResource();
        resource.setName(name);
        resource.setLocation(location);
        resource.setSwaggerVersion(version);
        return resource;
    }

    private String getSwaggerDocument(){
        return config.getProperty("opendevclub.zuul.swaggerDocument","");
    }

    private List<SwaggerResource> getSwaggerResources(){
        List resources = new ArrayList();
        String swaggerDocument = getSwaggerDocument();
        JSONArray jsonArray = JSONArray.parseArray(swaggerDocument);
        for (Object obj : jsonArray){
            JSONObject jsonObject = (JSONObject) obj;
            String name = jsonObject.getString("name");
            String location = jsonObject.getString("location");
            String version = jsonObject.getString("version");
            resources.add(swaggerResource(name,location,version));
        }
        return resources;
    }

}
