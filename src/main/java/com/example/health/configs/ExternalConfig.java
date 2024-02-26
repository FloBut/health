package com.example.health.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

public class ExternalConfig {
    //este o clasa din frame work spring care permite trimiterea cererilor
    //permite trimterea cererilor http pentru a primi si a da date in fomrmat json, xml
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {

        return restTemplateBuilder.build();
    }
    //este o clasa din biblioteca jakson utilizata pentru seraializare sau
    // desarailizarea dateleor primite in format json , xml sau yaml
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
