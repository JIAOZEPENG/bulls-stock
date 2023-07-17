package com.buba.order.startup;

import com.buba.order.catutils.CatContext;
import com.buba.order.catutils.CatRestInterceptor;
import com.dianping.cat.Cat;
import com.dianping.cat.CatConstants;
import com.dianping.cat.message.Transaction;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Stack;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.buba"})
@RestController
@Log4j2
public class CatDemoOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(CatDemoOrderApplication.class,args);
    }

    @Autowired
    RestTemplate restTemplate;
    //账户服务
    @Value("${server3.address:localhost:8083}")
    private String serviceAddress3;
    //库存服务
    @Value("${server3.address:localhost:8084}")
    private String serviceAddress4;
    //异常测试端口
    private static final int MOCK_PORT = 8765;


    /**
     * 提供下单接口
     * @return
     * @throws InterruptedException
     */
    @RequestMapping("/order")
    public String service2MethodInController() throws InterruptedException{
        Thread.sleep(200);
        // 调用账户服务以及库存服务
        String service3 = restTemplate.getForObject("http://" + serviceAddress3 + "/account", String.class);
        String service4 = restTemplate.getForObject("http://" + serviceAddress4 + "/stock", String.class);
        return String.format("Calling order service[order success] ==> Calling Account Service [%s] ==> Calling Customer Service [%s]",service3,service4);
    }

    /**
     * 模拟异常超时接口
     * @return
     * @throws InterruptedException
     */
    @RequestMapping("/readtimeout")
    public String connectionTimeout() throws InterruptedException{
        Transaction t = Cat.newTransaction(CatConstants.TYPE_CALL, "connectionTimeout");
        Thread.sleep(500);
        try {
            log.info("Calling a missing service");
            restTemplate.getForObject("http://localhost:" + MOCK_PORT + "/readtimeout",String.class);
            return "Should blow up";
        } catch (Exception e) {
            t.setStatus(e);
            Cat.getProducer().logError(e);
            throw e;
        } finally {
            t.complete();
        }
    }

    @Bean
    RestTemplate restTemplate() {

        RestTemplate restTemplate = new RestTemplate();

        // 保存和传递调用链上下文
        restTemplate.setInterceptors(Collections.singletonList(new CatRestInterceptor()));

        return restTemplate;
    }
}
