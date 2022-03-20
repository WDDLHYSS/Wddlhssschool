package com.wddlhyss.eduorder;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

import java.util.prefs.BackingStoreException;

@SpringBootApplication
@ComponentScan(basePackages = {"com.wddlhyss"})
@MapperScan(basePackages = {"com.wddlhyss.eduorder.mapper"})
@EnableDiscoveryClient
@EnableFeignClients
public class OrderMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderMainApplication.class,args);
    }
}
