package com.wddlhyss.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)//默认不启动数据库
@ComponentScan(basePackages = {"com.wddlhyss"})
public class OssMain_App {
    public static void main(String[] args) {
        SpringApplication.run(OssMain_App.class,args);
    }
}
