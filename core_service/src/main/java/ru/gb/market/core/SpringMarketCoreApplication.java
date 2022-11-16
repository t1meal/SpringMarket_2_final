package ru.gb.market.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


@SpringBootApplication
@EnableCaching
@EnableWebSecurity
@EnableAspectJAutoProxy

public class SpringMarketCoreApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringMarketCoreApplication.class, args);
	}

}
