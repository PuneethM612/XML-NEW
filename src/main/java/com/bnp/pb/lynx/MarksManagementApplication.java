package com.bnp.pb.lynx;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ImportResource;

public class MarksManagementApplication {

    public static void main(String[] args) {
        // Load XML configuration instead of using annotations
        SpringApplication.run(new String[]{"classpath:spring-boot-config.xml"}, args);
    }
} 