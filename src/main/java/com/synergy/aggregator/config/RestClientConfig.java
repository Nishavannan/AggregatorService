package com.synergy.aggregator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
@Configuration
public class RestClientConfig {

    //The following method builds restclient object and register it as spring bean
    @Bean
    public RestClient restClient(){

        return RestClient.builder()
                .baseUrl("https://api.restful-api.dev")
                .build();
    }
}

 /*  SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(100);
        factory.setReadTimeout(100);*/

/*    .requestFactory(factory)*/

// Test-NetConnection api.restful-api.dev -Port 443
// curl.exe https://api.restful-api.dev/objects/1
