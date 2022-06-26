package ru.gb.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.market.dto.ProductDto;
import ru.gb.market.exceptions.ResourceNotFoundException;
import ru.gb.market.model.Product;
import ru.gb.market.repositories.CartProductRepository;
import ru.gb.market.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CartProductRepository cartProductRepository;

    public Page<Product> findAll(int pageIndex, int pageSize) {
        return productRepository.findAll(PageRequest.of(pageIndex, pageSize));
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    @Transactional
    public void updateProduct(ProductDto productDto) {
        Product product = findById(productDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Product id: " + productDto.getId() + " cannot be found!"));
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
///////////////////////////////////////////////////////////////////////////////////////////
    public List<Product> findAllInCart() {
        return cartProductRepository.findAllInCart();
    }
    public void addProductInCart(Product product){
        cartProductRepository.addProduct(product);
    }
    public void deleteProductFromCart(Long id){
        cartProductRepository.deleteProductInCart(id);
    }
}
