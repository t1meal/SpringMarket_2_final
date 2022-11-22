package ru.gb.market.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


@SpringBootApplication
@EnableWebSecurity

public class MarketAuthApplication {
	public static void main(String[] args) {
		SpringApplication.run(MarketAuthApplication.class, args);
	}

}
