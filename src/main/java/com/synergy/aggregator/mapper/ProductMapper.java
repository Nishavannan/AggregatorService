package com.synergy.aggregator.mapper;

import com.synergy.aggregator.dto.ProductResponseDto;
import com.synergy.aggregator.dto.SupplierProductDto;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class ProductMapper {

    public ProductResponseDto toProductResponse(SupplierProductDto source){
        ProductResponseDto target = new ProductResponseDto();

        if(source.getCreatedAt() != null){
            String formattedDate = String.valueOf(Instant.ofEpochMilli(Long.parseLong(source.getCreatedAt())));
            target.setCreatedAt(formattedDate);
        }

        target.setId(source.getId());
        target.setName(source.getName());
        target.setData(source.getData());

        return target;
    }
}
