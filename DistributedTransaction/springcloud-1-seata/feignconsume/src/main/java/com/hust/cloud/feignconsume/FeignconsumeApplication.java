package com.hust.cloud.feignconsume;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.hust.cloud.feignconsume.util"})
@SpringBootApplication
public class FeignconsumeApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignconsumeApplication.class, args);
    }

}
