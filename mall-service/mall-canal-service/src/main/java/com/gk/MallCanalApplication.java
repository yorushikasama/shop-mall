package com.gk;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableFeignClients(basePackages = {"com.gk.goods.feign","com.gk.search.feign","com.gk.page.feign"})
public class MallCanalApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallCanalApplication.class,args);
    }
}