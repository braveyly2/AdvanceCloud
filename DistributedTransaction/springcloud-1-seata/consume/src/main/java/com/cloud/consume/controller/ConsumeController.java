package com.cloud.consume.controller;

import com.cloud.consume.service.AccountInfoService;
import com.cloud.consume.util.ComputerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumeController {
    //@Autowired
    //RestTemplate restTemplate;

    //@Autowired
    //ComputerClient computerClient;

    @Autowired
    AccountInfoService accountInfoService;

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String add(){
        //return restTemplate.getForEntity("http://computer-service/add?a=10&b=20", String.class).getBody();
        return "liwei";
    }

    @RequestMapping(value="/transfer", method= RequestMethod.POST)
    public String transfer(@RequestParam("accountNo") String accountNo, @RequestParam("amount") Double amount){

        //accountInfoService.updateAccountBalance(accountNo, amount);
        accountInfoService.updateAccountBalance(accountNo, amount);


        return "ok";
    }

}
