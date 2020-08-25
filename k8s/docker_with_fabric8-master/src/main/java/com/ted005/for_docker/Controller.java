package com.ted005.for_docker;

import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.stereotype.Controller
public class Controller {

    @RequestMapping("/")
    public String index(){
        return "test";
    }


}
