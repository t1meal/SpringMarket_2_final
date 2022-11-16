package ru.gb.market.core.configs;

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
import ru.gb.market.core.properties.CartServiceIntegrationProperties;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableConfigurationProperties(CartServiceIntegrationProperties.class)
@RequiredArgsConstructor
public class AppConfigs {

    private final CartServiceIntegrationProperties cartServiceIntegrationProperties;

    @Bean
    public WebClient cartServiceWebClient (){
        TcpClient client = TcpClient
                .create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS,cartServiceIntegrationProperties.getConnectTimeout())
                .doOnConnected(connection -> {
                    connection.addHandlerLast(new ReadTimeoutHandler(cartServiceIntegrationProperties.getReadTimeout(), TimeUnit.MILLISECONDS));
                    connection.addHandlerLast(new WriteTimeoutHandler(cartServiceIntegrationProperties.getWriteTimeout(), TimeUnit.MILLISECONDS));
                });
        return WebClient
                .builder()
                .baseUrl(cartServiceIntegrationProperties.getUrl())
                .clientConnector(new ReactorClientHttpConnector(HttpClient.from(client)))
                .build();
    }
}
