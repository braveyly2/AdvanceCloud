package club.mydlq.k8s.controller;

import club.mydlq.feign.TestInterface;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements TestInterface {

    static int count = 1;
    @Override
    public String getInfo() {
        return "Hello World!";
    }

    @GetMapping("/health")
    public String health() {
        count++;
        if(count > 10) {
            int i = 1 / 0;
        }
        return "OK";
    }

}
