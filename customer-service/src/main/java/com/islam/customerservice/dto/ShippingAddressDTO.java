package com.islam.customerservice.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public class ShippingAddressDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotEmpty(message = "Address is required")
    @Size(min = 5, max = 255, message = "Address should be between 5 and 255 characters")
    private String address;

    @NotEmpty(message = "City is required")
    @Size(min = 2, max = 50, message = "City should be between 2 and 50 characters")
    private String city;

    @NotEmpty(message = "Country is required")
    @Size(min = 2, max = 50, message = "Country should be between 2 and 50 characters")
    private String country;

    @NotEmpty(message = "Postal code is required")
    @Size(min = 4, max = 10, message = "Postal code should be between 4 and 10 characters")
    private String postalCode;

    private Integer CustomerId;

    public Integer getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(Integer customerId) {
        CustomerId = customerId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
