package com.tuhu.websocketsample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sound.midi.Soundbank;

/**
 * SpringBoot 集成 WebSocket 实现前后端消息互传
 * @author chendesheng
 * @since 2019-08-06
 */
@SpringBootApplication
public class WebsocketSampleApplication {


    public static void main(String[] args) {
        SpringApplication.run(WebsocketSampleApplication.class, args);
    }

}
