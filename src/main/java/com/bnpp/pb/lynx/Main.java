package com.bnpp.pb.Lynx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ImportResource("classpath:spring-boot-config.xml")
@ComponentScan(basePackages = {"com.bnpp.pb.lynx", "com.bnpp.pb.Lynx"})
@EntityScan(basePackages = {"com.bnpp.pb.lynx.model", "com.bnpp.pb.Lynx.model"})
@EnableJpaRepositories(basePackages = {"com.bnpp.pb.lynx.repository", "com.bnpp.pb.Lynx.repository"})
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
} 