package com.opendev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CMSManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CMSManagerApplication.class, args);
    }
}
