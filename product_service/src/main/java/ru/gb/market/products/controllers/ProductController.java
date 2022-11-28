package ru.gb.market.products.controllers;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.gb.market.api.dto.ProductDto;

import ru.gb.market.products.entities.ProductEntity;
import ru.gb.market.products.mappers.ProductConverter;
import ru.gb.market.products.services.ProductService;

import java.util.List;


@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor

public class ProductController {
    private final ProductService productService;
    private final ProductConverter productConverter;

    @GetMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    public Page<ProductDto> findAll(@RequestParam(name = "p", defaultValue = "1") int pageIndex) {
        return productService.findAll(pageIndex, 10);
    }

    @GetMapping("/products_")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDto> findAll(
            @RequestParam(required = false, name = "min_price") Integer minPrice,
            @RequestParam(required = false, name = "max_price") Integer maxPrice,
            @RequestParam(required = false, name = "title") String title,
            @RequestParam(defaultValue = "1", name = "p") Integer page
    ) {
        if (page < 1){
            page = 1;
        }
        Specification<ProductEntity> specification = productService.createSpecByFilters(minPrice, maxPrice, title);

        return productService.findAllProductsBySpec(specification, page - 1).map(productConverter::entityToDto).getContent();
    }

    @GetMapping("/products/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDto findById(@PathVariable Long id) {
        return productService.findProductDtoById(id);
    }

    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveProduct(@RequestBody @Validated ProductDto productDto) {
        productService.createNewProduct(productDto);
    }

    @PutMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateProduct(@RequestBody ProductDto productDto) {
        productService.updateProduct(productDto);
    }

    @DeleteMapping("/products/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }


}
