package com.synergy.aggregator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data

//Consumer {request}--> Aggregator {request}--> Supplier {response}--> Aggregator {response}--> Consumer
//Holds what aggregator application sends to the consumer
public class ProductResponseDto {

    private String id;
    private String name;
    private Map<String,Object> data;
    private String createdAt;

}
