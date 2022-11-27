package ru.gb.market.products;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import ru.gb.market.api.dto.ProductDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class RestServerRunTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getProductTest() {
        ProductDto products = restTemplate.getForObject("/api/v1/products/1", ProductDto.class);
        assertThat(products).isNotNull();
    }

    @Test
    public void getFullProductTest() {
        List <ProductDto> products = restTemplate.getForObject("/api/v1/products_", List.class);
        assertThat(products).isNotNull().isNotEmpty().hasSize(15);

    }
}
