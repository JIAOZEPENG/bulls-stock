package com.buba.hateoas.demo.order.startup;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.hateoas.hal.Jackson2HalModule;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@ComponentScan(basePackages = {"com.buba"})
@EntityScan(basePackages = {"com.buba"})
@EnableJpaRepositories(basePackages = {"com.buba"})
public class HateoasOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(HateoasOrderApplication.class,args);
    }

    /**
     * 采用JACKSON作为JSON处理组件
     * @return
     */
    @Bean
    public Jackson2HalModule jackson2HalModule() {
        return new Jackson2HalModule();
    }

    /**
     * 设置HTTP连接池参数
     * @return
     */
    @Bean
    public HttpComponentsClientHttpRequestFactory requestFactory() {
        PoolingHttpClientConnectionManager connectionManager =
                new PoolingHttpClientConnectionManager(30, TimeUnit.SECONDS);
        connectionManager.setMaxTotal(200);
        connectionManager.setDefaultMaxPerRoute(20);

        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .evictIdleConnections(30, TimeUnit.SECONDS)
                .disableAutomaticRetries()
                //  Keep-Alive 策略
                .setKeepAliveStrategy(new RemoteConnectionKeepAliveStrategy())
                .build();

        HttpComponentsClientHttpRequestFactory requestFactory =
                new HttpComponentsClientHttpRequestFactory(httpClient);

        return requestFactory;
    }

    /**
     * 设置RestTemplate参数
     * @param builder
     * @return
     */
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .setConnectTimeout(Duration.ofMillis(2000))
                .setReadTimeout(Duration.ofMillis(1800))
                .requestFactory(this::requestFactory)
                .build();
    }
}
