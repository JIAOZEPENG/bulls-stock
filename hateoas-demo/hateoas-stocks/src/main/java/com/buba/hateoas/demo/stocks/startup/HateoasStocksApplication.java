package com.buba.hateoas.demo.stocks.startup;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.buba"})
@EntityScan(basePackages = {"com.buba"})
@EnableJpaRepositories(basePackages = {"com.buba"})
public class HateoasStocksApplication {
    public static void main(String[] args) {
        SpringApplication.run(HateoasStocksApplication.class,args);
    }

    /**
     * Hibernate初始化
     * @return
     */
    @Bean
    public Hibernate5Module hibernate5Module(){
        return new Hibernate5Module();
    }

    /**
     * json格式
     * @return
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer(){
        return builder -> builder.indentOutput(true);
    }
}
