package com.synergy.aggregator.controller;

import com.synergy.aggregator.dto.ProductRequestDto;
import com.synergy.aggregator.dto.ProductResponseDto;
import com.synergy.aggregator.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<ProductResponseDto> getProductById(@RequestParam String id){
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(@Valid @RequestBody ProductRequestDto productRequestDto){

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productService.createProduct(productRequestDto));

    }
}
