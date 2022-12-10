package ru.gb.market.products.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.gb.market.api.dto.ProductDto;

import ru.gb.market.api.exceptions.MarketError;
import ru.gb.market.products.entities.ProductEntity;
import ru.gb.market.products.mappers.ProductConverter;
import ru.gb.market.products.services.ProductService;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Tag(name = "Товары", description = "Методы работы с товарами")
public class ProductController {
    private final ProductService productService;
    private final ProductConverter productConverter;

    @Operation(
            summary = "Запрос на получение списка (в том числе и отфильтрованного) товаров",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content (schema = @Schema(implementation = Page.class))
                    ),
                    @ApiResponse(
                            description = "не удалось получить список товаров", responseCode = "404",
                            content = @Content (schema = @Schema(implementation = MarketError.class))
                    )
            }
    )
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<ProductDto> findAll(
            @RequestParam(name = "p", defaultValue = "1") @Parameter(description = "Номер страницы", required = true) Integer pageIndex,
            @RequestParam(name = "page_size", defaultValue = "10") @Parameter(description = "Количество товаров на странице") Integer pageSize,
            @RequestParam(required = false, name = "min_price") @Parameter(description = "Фильтр по мин цене товара") Integer minPrice,
            @RequestParam(required = false, name = "max_price") @Parameter(description = "Фильтр по макс цене товара") Integer maxPrice,
            @RequestParam(required = false, name = "title") @Parameter(description = "Фильтр по мин цене товара") String title) {
        Specification<ProductEntity> specification = productService.createSpecByFilters(minPrice, maxPrice, title);
        return productService.findAll(specification, pageIndex, pageSize);
    }


    @Operation(
            summary = "Запрос на получение продукта по id",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ProductDto.class))
                    ),
                    @ApiResponse(
                            description = "Продукт не найден", responseCode = "404",
                            content = @Content(schema = @Schema(implementation = MarketError.class))
                    )
            }
    )
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDto findById(@PathVariable @Parameter (description = "Идентификатор продукта", required = true) Long id) {
        return productService.findProductDtoById(id);
    }


    @Operation(
            summary = "Запрос на создание нового товара",
            responses = {
                    @ApiResponse(
                            description = "Товар успешно создан", responseCode = "201"
                    ),
                    @ApiResponse(
                            description = "Ошибка создания нового товара", responseCode = "400"
                    )
            }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductDto productDto) {
        productService.createNewProduct(productDto);
    }

    @Operation(
            summary = "Запрос на изменение параметров продукта",
            responses = {
                    @ApiResponse(
                            description = "Товар успешно изменен", responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Ошибка изменения товара", responseCode = "400"
                    )
            }
    )
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateProduct(@RequestBody ProductDto productDto) {
        productService.updateProduct(productDto);
    }

    @Operation(
            summary = "Запрос на удаление товара из списка",
            responses = {
                    @ApiResponse (
                            description = "Товар успешно удален", responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Ошибка удаления товара", responseCode = "404"
                    )
            }
    )
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable @Parameter (description = "Идентификатор продукта", required = true) Long id) {
        productService.deleteById(id);
    }

}
