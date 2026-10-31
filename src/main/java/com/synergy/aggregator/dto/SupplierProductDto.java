package com.synergy.aggregator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

//Consumer {request}--> Aggregator {request}--> Supplier {response}--> Aggregator {response}--> Consumer
//Holds what the supplier sends to aggregator application
@Data
@AllArgsConstructor
public class SupplierProductDto {

    private String id;
    private String name;
    private Map<String,Object> data;
    private String createdAt;
}
