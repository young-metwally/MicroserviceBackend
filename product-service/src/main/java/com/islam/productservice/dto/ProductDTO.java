package com.islam.productservice.dto;

import jakarta.validation.constraints.*;

import java.io.Serializable;

public class ProductDTO implements Serializable {

    private Integer id;
    @NotEmpty
    @Size(min = 1, max = 50)
    private String name;
    @NotEmpty
    @Size(min = 1, max = 100)
    private String description;
    @NotNull
    @DecimalMin(value = "0.25")
    private Double price;
    @NotEmpty
    @Size(min = 1, max = 255)
    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}