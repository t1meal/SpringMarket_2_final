package ru.gb.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.market.entities.ProductEntity;
import ru.gb.market.repositories.ProductRepository;
import ru.gb.market.soap.Product;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductsSoapService {
    private final ProductRepository productRepository;

    public List<Product> getAllProducts (){
        return productRepository.findAll().stream().map(entityToSoap).collect(Collectors.toList());
    }

    public static final Function<ProductEntity, Product> entityToSoap = se -> {
        Product p = new Product();
        p.setId(se.getId());
        p.setTitle(se.getTitle());
        p.setPrice(se.getPrice());
        return p;
    };
}
