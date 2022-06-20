package ru.gb.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.gb.market.model.Product;
import ru.gb.market.repositories.ProductRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Page<Product> findAll(int pageIndex, int pageSize) {
        return productRepository.findAll(PageRequest.of(pageIndex, pageSize));
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Product update(Product product) {
        Product baseProduct = productRepository.findByTitle(product.getTitle()).get();
        baseProduct.setPrice(product.getPrice());
        productRepository.save(baseProduct);
        return baseProduct;
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
