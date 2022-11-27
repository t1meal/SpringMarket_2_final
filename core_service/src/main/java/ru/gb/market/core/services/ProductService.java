package ru.gb.market.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.market.api.dto.ProductDto;
import ru.gb.market.api.exceptions.ResourceNotFoundException;
import ru.gb.market.core.mappers.ProductConverter;
import ru.gb.market.core.entities.ProductEntity;

import ru.gb.market.core.repositories.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final ProductConverter productConverter;

    public Page<ProductDto> findAll(int pageIndex, int pageSize) {
        if (pageIndex < 1) {
            pageIndex = 1;
        }
        return productRepository.findAll(PageRequest.of(pageIndex - 1, pageSize))
                .map(productConverter::entityToDto);
    }

    public List<ProductDto> findAllProducts() {
        return productRepository.findAll().stream().map(productConverter::entityToDto).collect(Collectors.toList());
    }

    public ProductDto findProductDtoById(Long id) {
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product id: " + id + " not found"));
        return productConverter.entityToDto(product);
    }

    public ProductEntity findProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product id: " + id + " not found"));
    }

    public void createNewProduct(ProductDto productDto) {
        ProductEntity product = productConverter.dtoToEntity(productDto);
        productRepository.save(product);
    }

    @Transactional
    public void updateProduct(ProductDto productDto) {
        ProductEntity product = productConverter.dtoToEntity(productDto);
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setCategory(categoryService.findCategoryByTitle(productDto.getCategoryTitle()));
        productRepository.save(product);
    }

    public void deleteById(Long productId) {
        productRepository.deleteById(productId);
    }

}
