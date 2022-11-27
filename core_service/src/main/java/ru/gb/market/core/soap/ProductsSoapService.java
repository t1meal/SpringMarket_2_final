package ru.gb.market.core.soap;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.market.api.dto.ProductDto;
import ru.gb.market.core.integrations.ProductServiceIntegration;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductsSoapService {
    private final ProductServiceIntegration productRepository;

    public List<Product> getAllProducts (){
        return productRepository.getAllProducts().stream().map(entityToSoap).collect(Collectors.toList());
    }

    public static final Function<ProductDto, Product> entityToSoap = se -> {
        Product p = new Product();
        p.setId(se.getId());
        p.setTitle(se.getTitle());
        p.setPrice(se.getPrice());
        return p;
    };
}
