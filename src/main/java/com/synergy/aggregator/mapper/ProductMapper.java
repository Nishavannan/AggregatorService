package com.synergy.aggregator.mapper;

import com.synergy.aggregator.dto.ProductResponseDto;
import com.synergy.aggregator.dto.SupplierProductDto;

public class ProductMapper {

    public ProductResponseDto toProductResponse(SupplierProductDto source){
        ProductResponseDto target = new ProductResponseDto();
        target.setId(source.getId());
        target.setName(source.getName());
        target.setData(source.getData());
        target.setCreatedAt(source.getCreatedAt());
        return target;
    }
}
