package com.bnpp.pb.Lynx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:spring-boot-config.xml")
@ComponentScan(basePackages = {"com.bnpp.pb"})
@EntityScan(basePackages = {"com.bnpp.pb"})
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
} 