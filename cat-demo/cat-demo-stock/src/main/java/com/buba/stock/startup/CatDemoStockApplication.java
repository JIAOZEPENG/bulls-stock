package com.buba.stock.startup;

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
public class CatDemoStockApplication {
    public static void main(String[] args) {
        SpringApplication.run(CatDemoStockApplication.class,args);
    }

    /**
     * 提供库存服务接口
     * @return
     * @throws Exception
     */
    @RequestMapping("/stock")
    public String account() throws Exception{
        Thread.sleep(200);
        return "stock success";
    }
}
