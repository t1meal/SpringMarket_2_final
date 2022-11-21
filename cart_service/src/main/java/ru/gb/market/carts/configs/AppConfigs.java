package ru.gb.market.carts.configs;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;
import ru.gb.market.carts.properties.ServicesIntegrationProperties;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableConfigurationProperties(ServicesIntegrationProperties.class)
@RequiredArgsConstructor
public class AppConfigs {

    private final ServicesIntegrationProperties servicesIntegrationProperties;

    @Bean
    public WebClient webClient() {
        TcpClient client = TcpClient
                .create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, servicesIntegrationProperties.getConnectTimeout())
                .doOnConnected(connection -> {
                    connection.addHandlerLast(new ReadTimeoutHandler(servicesIntegrationProperties.getReadTimeout(), TimeUnit.MILLISECONDS));
                    connection.addHandlerLast(new WriteTimeoutHandler(servicesIntegrationProperties.getWriteTimeout(), TimeUnit.MILLISECONDS));
                });
        return WebClient
                .builder()
                .clientConnector(new ReactorClientHttpConnector(HttpClient.from(client)))
                .build();
    }
}
