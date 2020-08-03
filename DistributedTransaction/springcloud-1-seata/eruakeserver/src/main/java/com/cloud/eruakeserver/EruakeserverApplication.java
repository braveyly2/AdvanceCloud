package com.cloud.eruakeserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EruakeserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(EruakeserverApplication.class, args);
    }

}
