package ru.gb.market.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.gb.market.mappers.ProductToDTOMapper;
import ru.gb.market.models.ProductDto;
import ru.gb.market.exceptions.DataValidationException;
import ru.gb.market.exceptions.ResourceNotFoundException;
import ru.gb.market.services.ProductService;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor

public class ProductController {
    private final ProductService productService;
    private final ProductToDTOMapper productToDTOMapper;


    @GetMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    public Page<ProductDto> findAll(@RequestParam(name = "p", defaultValue = "1") int pageIndex) {
        if (pageIndex < 1) {
            pageIndex = 1;
        }
        return productService.findAll(pageIndex - 1, 10).map(ProductDto::new);
    }

      @GetMapping("/products/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDto findById(@PathVariable Long id) {
        return new ProductDto(
                productService.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Product id: " + id + " not found")));
    }
    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveProduct(@RequestBody @Validated ProductDto productDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new DataValidationException(bindingResult
                    .getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.toList()));
        }
        productService.save(productToDTOMapper.mapToProduct(productDto));
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
