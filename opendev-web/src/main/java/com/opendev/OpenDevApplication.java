package com.opendev;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.opendev.mapper")
public class OpenDevApplication {
    public static void main(String[] args) {
        SpringApplication.run(OpenDevApplication.class, args);
    }
}
