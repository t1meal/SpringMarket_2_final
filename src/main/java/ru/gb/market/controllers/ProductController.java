package ru.gb.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.gb.market.dto.ProductDto;
import ru.gb.market.model.Product;
import ru.gb.market.services.ProductService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/products/{id}")
    public ProductDto findById(@PathVariable Long id) {
        return new ProductDto(productService.findById(id).get());
    }

    //    @GetMapping("/products")
//    public List<ProductDto> findAll() {
//        List<ProductDto> productDtoList = new ArrayList<>();
//        for (Product e : productService.findAll()) {
//            productDtoList.add(new ProductDto(e));
//        }
//        return productDtoList;
//    }
    @GetMapping("/products")
    public Page<ProductDto> findAll(@RequestParam (name = "p", defaultValue = "1") int pageIndex) {
        if (pageIndex<1){
            pageIndex = 1;
        }
        return productService.findAll(pageIndex-1,10).map(ProductDto::new);
    }

    @PostMapping("/products")
    public void saveProduct(@RequestBody ProductDto productDto) {
        productService.save(new Product(productDto.getTitle(), productDto.getPrice()));
    }

    @GetMapping("/products/delete/{id}")
    public ProductDto deleteById(@PathVariable Long id) {
        ProductDto item = new ProductDto(productService.findById(id).get());
        productService.deleteById(id);
        return item;
    }


    @GetMapping("/products/price")
    public List<Product> findByPriceBetween(@RequestParam(name = "min") int minPrice,
                                            @RequestParam(name = "max") int maxPrice) {
        return productService.findByPriceBetween(minPrice, maxPrice);
    }


}
