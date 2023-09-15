package com.gk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.gk.search.feign")
public class MallWebSearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallWebSearchApplication.class,args);
    }
}