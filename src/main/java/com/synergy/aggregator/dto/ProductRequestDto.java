package com.synergy.aggregator.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data

//Consumer {request}--> Aggregator {request}--> Supplier {response}--> Aggregator {response}--> Consumer
//What consumer sends to the aggregator application
public class ProductRequestDto {

    @NotBlank(message = "Product data must be provided")
    private String name;
    @NotNull(message = "Product data cannot be null")
    private Map<String,Object> data;

}
