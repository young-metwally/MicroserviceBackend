package com.islam.apigateway.services;


import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public interface ProductService {
    JsonNode getProductById(Integer id);
    List<JsonNode> getAllProducts();
    JsonNode createProduct(JsonNode productDTO);
    JsonNode updateProduct(Integer id, JsonNode productDTO);
    void deleteProduct(Integer id);
}