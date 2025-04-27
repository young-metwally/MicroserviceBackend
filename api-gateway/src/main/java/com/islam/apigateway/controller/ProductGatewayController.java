package com.islam.apigateway.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.islam.apigateway.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductGatewayController {

    private final ProductService productService;

    public ProductGatewayController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<JsonNode> getProductById(@PathVariable Integer id) {
        JsonNode product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping
    public ResponseEntity<List<JsonNode>> getAllProducts() {
        List<JsonNode> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity<JsonNode> createProduct(@RequestBody JsonNode productDTO) {
        JsonNode createdProduct = productService.createProduct(productDTO);
        return ResponseEntity.ok(createdProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JsonNode> updateProduct(@PathVariable Integer id, @RequestBody JsonNode productDTO) {
        JsonNode updatedProduct = productService.updateProduct(id, productDTO);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}