package com.opendev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.opendev.mapper")
public class OpenDevApplication {
    public static void main(String[] args) {
        SpringApplication.run(OpenDevApplication.class, args);
    }
}
