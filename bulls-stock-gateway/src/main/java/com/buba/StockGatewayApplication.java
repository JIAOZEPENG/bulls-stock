package com.buba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient//Nacos
@ComponentScan(basePackages = {"com"})
public class StockGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockGatewayApplication.class, args);
    }
}
