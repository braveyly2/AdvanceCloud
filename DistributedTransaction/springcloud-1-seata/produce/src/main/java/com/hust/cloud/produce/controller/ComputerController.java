package com.hust.cloud.produce.controller;

import com.hust.cloud.produce.service.AccountInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ComputerController {
    @Autowired
    AccountInfoService accountInfoService;

    @GetMapping(value="/add")
    public Integer add(@RequestParam Integer a, @RequestParam Integer b){
        Integer r = a + b;
        System.out.println("ComputerController:add");
        return r;
    }

    @GetMapping(value="/transfer")
    public String transfer(@RequestParam Double amount){
        accountInfoService.updateAccountBalance("2",amount);
        return "bank2"+amount;
    }
}
