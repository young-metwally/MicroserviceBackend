package com.islam.productservice.services.impl;

import com.islam.productservice.converter.ProductConverter;
import com.islam.productservice.dto.ProductDTO;
import com.islam.productservice.entity.ProductEntity;
import com.islam.productservice.exceptions.InstanceUndefinedException;
import com.islam.productservice.repository.ProductRepository;
import com.islam.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductConverter productConverter;

    @Override
    public ProductDTO getProductById(Integer id) {
        return productRepository.findById(id)
                .map(productConverter::convertToDto)
                .orElseThrow(() -> new InstanceUndefinedException(new Error("Product with ID " + id + " not found")));
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(productConverter::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        ProductEntity productEntity = productConverter.convertToEntity(productDTO);
        ProductEntity savedEntity = productRepository.save(productEntity);
        return productConverter.convertToDto(savedEntity);
    }

    @Override
    public ProductDTO updateProduct(Integer id, ProductDTO productDTO) {
        ProductEntity productEntity = productRepository.findById(id)
                .orElseThrow(() -> new InstanceUndefinedException(new Error("Product with ID " + id + " not found")));
        productEntity.setName(productDTO.getName());
        productEntity.setDescription(productDTO.getDescription());
        productEntity.setPrice(productDTO.getPrice());
        ProductEntity updatedEntity = productRepository.save(productEntity);
        return productConverter.convertToDto(updatedEntity);
    }

    @Override
    public void deleteProduct(Integer id) {
        getProductById(id);
        productRepository.deleteById(id);
        productRepository.flush();
    }
}