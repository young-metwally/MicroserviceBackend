package com.islam.apigateway.services.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.islam.apigateway.services.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public ProductServiceImpl(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public JsonNode getProductById(Integer id) {
        String url = "http://product-service/products/" + id;
        String response = restTemplate.getForObject(url, String.class);
        try {
            return objectMapper.readTree(response);
        } catch (Exception e) {
            throw new RuntimeException("Error parsing response", e);
        }
    }

    @Override
    public List<JsonNode> getAllProducts() {
        String url = "http://product-service/products";
        String response = restTemplate.getForObject(url, String.class);
        try {
            return Arrays.asList(objectMapper.readValue(response, JsonNode[].class));
        } catch (Exception e) {
            throw new RuntimeException("Error parsing response", e);
        }
    }

    @Override
    public JsonNode createProduct(JsonNode productDTO) {
        String url = "http://product-service/products";
        String response = restTemplate.postForObject(url, productDTO, String.class);
        try {
            return objectMapper.readTree(response);
        } catch (Exception e) {
            throw new RuntimeException("Error parsing response", e);
        }
    }

    @Override
    public JsonNode updateProduct(Integer id, JsonNode productDTO) {
        String url = "http://product-service/products/" + id;
        restTemplate.put(url, productDTO);
        return getProductById(id);
    }

    @Override
    public void deleteProduct(Integer id) {
        String url = "http://product-service/products/" + id;
        restTemplate.delete(url);
    }
}