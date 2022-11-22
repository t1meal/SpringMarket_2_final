package ru.gb.market.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties (prefix = "integrations.services")
@Data
public class CartServiceIntegrationProperties {
    private String cartUrl;
    private Integer readTimeout;
    private Integer writeTimeout;
    private Integer connectTimeout;

}
