package com.buba.account.startup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.buba"})
@RestController
public class CatDemoAccountApplication {
    public static void main(String[] args) {
        SpringApplication.run(CatDemoAccountApplication.class,args);
    }

    /**
     * 提供账户服务接口
     * @return
     * @throws Exception
     */
    @RequestMapping("/account")
    public String account() throws Exception{
        Thread.sleep(150);
        return "account success";
    }
}
