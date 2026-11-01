package com.synergy.aggregator.service.impl;

import com.synergy.aggregator.dto.ProductRequestDto;
import com.synergy.aggregator.dto.ProductResponseDto;
import com.synergy.aggregator.dto.SupplierProductDto;
import com.synergy.aggregator.exception.MethodArgumentNotValidException;
import com.synergy.aggregator.exception.ProductNotFoundException;
import com.synergy.aggregator.exception.SupplierServiceException;
import com.synergy.aggregator.mapper.ProductMapper;
import com.synergy.aggregator.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private final RestClient restClient;
    @Autowired
    private final ProductMapper productMapper;

    @Override
    public List<ProductResponseDto> getAllProducts() {
        try{
            SupplierProductDto[] supplierResponse = restClient.get()
                    .uri("/objects")
                    .retrieve()
                    .body(SupplierProductDto[].class);

            if(supplierResponse == null || supplierResponse.length == 0){
                throw new ProductNotFoundException("No products found");
            }
            return Arrays.stream(supplierResponse)
                    .map(productMapper::toProductResponse)
                    .toList();
        } catch (HttpClientErrorException.NotFound ex) {
            log.error("Error No Products found");
            throw new ProductNotFoundException("No products found");

        } catch (HttpClientErrorException.BadRequest ex){
            log.error("Invalid request while fetching products", ex);
            throw new MethodArgumentNotValidException("Invalid request. " + ex.getResponseBodyAsString());
        }catch (HttpServerErrorException ex){
            log.error("Error accessing supplier server while fetching products", ex);
            throw  new SupplierServiceException("Error processing request. Please try after some time.");
        }
        catch (RestClientException ex) {

            log.error("Error connecting to supplier service ", ex);
            throw new SupplierServiceException("Error processing request. Please try after some time.");
        }
    }

    @Override
    public ProductResponseDto getProductById(String id) {
        try {
            SupplierProductDto supplierResponse = restClient.get()
                    .uri("/objects/{id}", id)
                    .retrieve()
                    .body(SupplierProductDto.class);
            return productMapper.toProductResponse(supplierResponse);
        } catch (HttpClientErrorException.NotFound ex) {
            log.error("Error Product Not Found With ID: {}", id, ex);
            throw new ProductNotFoundException("No product found with ID:" + id);

        } catch (HttpClientErrorException.BadRequest ex){
            log.error("Invalid request With ID: {}", id, ex);
            throw new MethodArgumentNotValidException("Invalid request" + ex.getResponseBodyAsString());
        }catch (HttpServerErrorException ex){
            log.error("Error accessing supplier server");
            throw  new SupplierServiceException("Error processing request. Please try after some time.");
        }
        catch (RestClientException ex) {

            log.error("Error connecting to supplier service for ID {}", id, ex);
            throw new SupplierServiceException("Unable to fetch product from supplier service");
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

            if(supplierResponse == null){
                throw new SupplierServiceException("Unable to create product. Empty response received.");
            }

            return productMapper.toProductResponse(supplierResponse);
        }catch (HttpClientErrorException ex){
            log.error("Invalid request while creating product", ex);

            throw new MethodArgumentNotValidException("Invalid request. "+ ex.getResponseBodyAsString());
        }
        catch (HttpServerErrorException ex){
            log.error("Supplier service error while creating product", ex);
            throw  new SupplierServiceException("Error processing request. Please try after some time.");
        }

        catch (RestClientException ex){
            log.error("Error connecting to supplier service", ex);
            throw new SupplierServiceException("Error processing request. Please try after some time.");
        }
    }
}
