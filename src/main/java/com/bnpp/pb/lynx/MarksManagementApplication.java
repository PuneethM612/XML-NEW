package com.bnpp.pb.lynx;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ImportResource;

@ImportResource("classpath:spring-boot-config.xml")
public class MarksManagementApplication {

    public static void main(String[] args) {
        // Load XML configuration instead of using annotations
        SpringApplication.run(MarksManagementApplication.class, args);
    }
} 