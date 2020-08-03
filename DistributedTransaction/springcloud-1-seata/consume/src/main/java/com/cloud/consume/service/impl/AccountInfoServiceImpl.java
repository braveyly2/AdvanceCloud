package com.cloud.consume.service.impl;


import com.cloud.consume.dao.AccountInfoDao;
import com.cloud.consume.service.AccountInfoService;
//import io.seata.core.context.RootContext;
//import io.seata.spring.annotation.GlobalTransactional;
import com.cloud.consume.util.ComputerClient;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

/**
 * @author Administrator
 * @version 1.0
 **/
@Service
@Slf4j
public class AccountInfoServiceImpl implements AccountInfoService {

    @Autowired
    AccountInfoDao accountInfoDao;

    @Autowired
    ComputerClient computerClient;

    @Autowired
    RestTemplate restTemplate;

   // @Transactional
   // @GlobalTransactional//开启全局事务
    @Override
    public void updateAccountBalance(String accountNo, Double amount) {
        log.info("bank1 service begin,XID：{}", RootContext.getXID());
        //扣减张三的金额
        accountInfoDao.updateAccountBalance("1",amount *-1);
        //调用李四微服务，转账
        //String transfer = bank2Client.transfer(amount);
        //String restUrl = "http://computer-service/transfer?amount=" + amount;
        //String transfer = restTemplate.getForEntity(restUrl, String.class).getBody();

        String transferResult = computerClient.transfer(amount);
        if("fallback".equals(transferResult)){
            //调用李四微服务异常
            throw new RuntimeException("调用李四微服务异常");
        }

        if(amount == 2){
            //人为制造异常
            throw new RuntimeException("bank1 make exception..");
        }
    }
}
