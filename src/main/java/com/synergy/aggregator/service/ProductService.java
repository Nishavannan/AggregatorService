package com.synergy.aggregator.service;

import com.synergy.aggregator.dto.ProductRequestDto;
import com.synergy.aggregator.dto.ProductResponseDto;

import java.util.List;

public interface ProductService {
    List<ProductResponseDto> getAllProducts();
    ProductResponseDto getProductById(String id);
    ProductResponseDto createProduct(ProductRequestDto productRequestDto);
}
