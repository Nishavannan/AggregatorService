package com.synergy.aggregator.service.impl;

import com.synergy.aggregator.dto.ProductRequestDto;
import com.synergy.aggregator.dto.ProductResponseDto;
import com.synergy.aggregator.dto.SupplierProductDto;
import com.synergy.aggregator.exception.ProductNotFoundException;
import com.synergy.aggregator.mapper.ProductMapper;
import com.synergy.aggregator.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final RestClient restClient;
    private final ProductMapper productMapper;

    @Override
    public ProductResponseDto getProductById(String id) {
        try {
            SupplierProductDto supplierResponse = restClient.get()
                    .uri("/objects/{id}", id)
                    .retrieve()
                    .body(SupplierProductDto.class);
            return productMapper.toProductResponse(supplierResponse);
        } catch (HttpClientErrorException ex) {

            throw new ProductNotFoundException("No product found with ID:" + id);

        } catch (RestClientException ex) {

            log.error("Error fetching product with ID: {}", id, ex);
            throw new RuntimeException("Unable to fetch product from supplier service", ex);
        }

    }

    @Override
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
        try {
            SupplierProductDto supplierResponse = restClient.post()
                    .uri("/objects")
                    .body(productRequestDto)
                    .retrieve()
                    .body(SupplierProductDto.class);

            return productMapper.toProductResponse(supplierResponse);
        }catch (RestClientException ex){
            log.error("Error creating product with supplier", ex);
            throw new RuntimeException("Error creating product in supplier", ex);
        }
    }
}
