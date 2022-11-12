package ru.gb.market.carts;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
@EnableCaching
@EnableRedisRepositories

public class SpringMarketCartsApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringMarketCartsApplication.class, args);
    }

}