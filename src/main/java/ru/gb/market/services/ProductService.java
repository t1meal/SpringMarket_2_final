package ru.gb.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.market.mappers.ProductMapper;
import ru.gb.market.dto.ProductDto;
import ru.gb.market.exceptions.ResourceNotFoundException;
import ru.gb.market.entities.Product;

import ru.gb.market.repositories.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public Page<ProductDto> findAll(int pageIndex, int pageSize) {
        if (pageIndex < 1) {
            pageIndex = 1;
        }
        return productRepository.findAll(PageRequest.of(pageIndex - 1, pageSize)).map(productMapper::mapToDto);
    }

    // подредачить. чтобы из базы возвращался Product и далее с ним работаем. Отдельным метод для возврата в контроллер.
    public ProductDto findById(Long id) {
        return productMapper.mapToDto(productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product id: " + id + " not found")));
    }
    public Product findProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product id: " + id + " not found"));
    }

    public Product findByIdUtil(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product id: " + id + " not found"));
    }

    public void save(ProductDto productDto) {
        productRepository.save(productMapper.mapToProduct(productDto));
    }

    // завязать с логикой двух методов
    @Transactional
    public void updateProduct(ProductDto productDto) {
        Product product = findByIdUtil(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

}
