package ru.gb.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.market.mappers.ProductConverter;
import ru.gb.market.dto.ProductDto;
import ru.gb.market.exceptions.ResourceNotFoundException;
import ru.gb.market.entities.ProductEntity;

import ru.gb.market.repositories.ProductRepository;

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
                .map(product -> productConverter.entityToDto(product));
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

    public void save(ProductDto productDto) {
        ProductEntity product = productConverter.dtoToEntity(productDto);
        productRepository.save(product);
    }

    // завязать с логикой двух методов
    @Transactional
    public void updateProduct(ProductDto productDto) {
        ProductEntity product = findProductById(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setCategory(categoryService.findCategoryByTitle(productDto.getCategoryTitle()));
        productRepository.save(product);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

}
