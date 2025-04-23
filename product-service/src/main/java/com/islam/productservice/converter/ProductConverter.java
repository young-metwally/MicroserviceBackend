package com.islam.productservice.converter;

import com.islam.productservice.dto.ProductDTO;
import com.islam.productservice.entity.ProductEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {

    @Autowired
    private ModelMapper modelMapper;

    public ProductDTO convertToDto(ProductEntity productEntity) {
        return modelMapper.map(productEntity, ProductDTO.class);
    }
    public ProductEntity convertToEntity(ProductDTO productDTO) {
        return modelMapper.map(productDTO, ProductEntity.class);
    }

}
