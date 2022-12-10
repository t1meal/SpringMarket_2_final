package ru.gb.market.products.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.market.api.dto.ProductDto;
import ru.gb.market.api.exceptions.ResourceNotFoundException;
import ru.gb.market.products.entities.ProductEntity;
import ru.gb.market.products.mappers.ProductConverter;
import ru.gb.market.products.repositories.ProductRepository;
import ru.gb.market.products.specifications.ProductsSpecifications;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final ProductConverter productConverter;

    public Specification<ProductEntity> createSpecByFilters(Integer minPrice, Integer maxPrice, String title) {
        Specification<ProductEntity> spec = Specification.where(null);
        if (minPrice != null) {
            spec = spec.and(ProductsSpecifications.priceGreaterOrEqualsThan(minPrice));
        }
        if (minPrice != null) {
            spec = spec.and(ProductsSpecifications.priceLessThanOrEqualsThan(maxPrice));
        }
        if (minPrice != null) {
            spec = spec.and(ProductsSpecifications.titleLike(title));
        }
        return spec;
    }

    public Page<ProductDto> findAll(Specification<ProductEntity> spec, int pageIndex, int pageSize) {
        if (pageIndex < 1) {
            pageIndex = 1;
        }
        return productRepository.findAll(spec ,PageRequest.of(pageIndex - 1, pageSize))
                .map(productConverter::entityToDto);
    }

//    public Page<ProductEntity> findAllProductsBySpec(Specification<ProductEntity> spec, int page) {
//        return productRepository.findAll(spec, PageRequest.of(page, 5));
//    }

    public ProductDto findProductDtoById(Long id) {
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product id: " + id + " not found"));
        return productConverter.entityToDto(product);
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
        Optional <ProductEntity> deletedProduct = productRepository.findById(productId);
        if (deletedProduct.isPresent()){
            productRepository.deleteById(productId);
        } else {
            throw new ResourceNotFoundException("Product with id " + productId + " is not exist!");
        }


    }

}
