package ru.gb.market.carts.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties (prefix = "integrations.services")
@Data
public class ServicesIntegrationProperties {
    private String coreUrl;
    private String authUrl;
    private Integer readTimeout;
    private Integer writeTimeout;
    private Integer connectTimeout;

}
