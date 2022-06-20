package ru.gb.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.gb.market.dto.ProductDto;
import ru.gb.market.exceptions.ResourceNotFoundException;
import ru.gb.market.model.Product;
import ru.gb.market.services.ProductService;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor

public class ProductController {
    private final ProductService productService;

    @GetMapping("/products")
    public Page<ProductDto> findAll(@RequestParam(name = "p", defaultValue = "1") int pageIndex) {
        if (pageIndex < 1) {
            pageIndex = 1;
        }
        return productService.findAll(pageIndex - 1, 10).map(ProductDto::new);
    }

    @GetMapping("/products/{id}")
    public ProductDto findById(@PathVariable Long id) {
        return new ProductDto(productService
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product id: " + id + " not found")));
    }

    //    @GetMapping("/products/{id}")
//    public ResponseEntity<?> findById(@PathVariable Long id) {
//        Optional<Product> product = productService.findById(id);
//        if (product.isEmpty()){
//            return new ResponseEntity<>(new MarketError("Product id: " + id + " not found"), HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(new ProductDto(product.get()), HttpStatus.OK);
//    }
    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveProduct(@RequestBody ProductDto productDto) {
        productService.save(new Product(productDto.getTitle(), productDto.getPrice()));
    }

    @PutMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateProduct(@RequestBody ProductDto productDto) {
        productService.update(new Product(productDto.getTitle(), productDto.getPrice()));
    }

    @DeleteMapping("/products/{id}")
    public ProductDto deleteById(@PathVariable Long id) {
        ProductDto item = new ProductDto(productService.findById(id).get());
        productService.deleteById(id);
        return item;
    }
}
