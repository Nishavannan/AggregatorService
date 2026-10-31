package com.synergy.aggregator.service;

import com.synergy.aggregator.dto.ProductRequestDto;
import com.synergy.aggregator.dto.ProductResponseDto;

public interface ProductService {
    ProductResponseDto getProductById(String id);
    ProductResponseDto createProduct(ProductRequestDto productRequestDto);
}
