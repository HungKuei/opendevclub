package com.opendev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class WeChatServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeChatServerApplication.class, args);
    }
}
