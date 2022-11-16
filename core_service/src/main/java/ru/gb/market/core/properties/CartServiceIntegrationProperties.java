package ru.gb.market.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties (prefix = "integrations.cart-service")
@Data
public class CartServiceIntegrationProperties {
    private String url;
    private Integer readTimeout;
    private Integer writeTimeout;
    private Integer connectTimeout;

}
