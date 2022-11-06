package ru.gb.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.gb.market.dto.ProductDto;
import ru.gb.market.services.ProductService;
import ru.gb.market.utils.BindingResultService;


@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor

public class ProductController {
    private final ProductService productService;

    @GetMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    public Page<ProductDto> findAll(@RequestParam(name = "p", defaultValue = "1") int pageIndex) {
        return productService.findAll(pageIndex, 10);
    }

    @GetMapping("/products/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDto findById(@PathVariable Long id) {
        return productService.findProductDtoById(id);
    }

    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveProduct(@RequestBody @Validated ProductDto productDto, BindingResult bindingResult) {
        BindingResultService.checkError(bindingResult);
        productService.save(productDto);
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
