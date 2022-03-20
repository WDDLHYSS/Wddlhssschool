package com.wddlhyss.eduststics;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = {"com.wddlhyss"})
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan(basePackages = {"com.wddlhyss.eduststics.mapper"})
@EnableScheduling
public class StaticsApplication {
    public static void main(String[] args) {
        SpringApplication.run(StaticsApplication.class,args);
    }
}
