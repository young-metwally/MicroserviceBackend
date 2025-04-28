package com.islam.customerservice.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.islam.customerservice.dto.CustomerDTO;
import com.islam.customerservice.dto.ShippingAddressDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public class RegistrationForm implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Valid
    private JsonNode user;
    @NotNull
    @Valid
    private ShippingAddressDTO shippingAddress;
    @NotNull
    @Valid
    private CustomerDTO customer;

    public JsonNode getUser() {
        return user;
    }

    public void setUser(JsonNode user) {
        this.user = user;
    }

    public ShippingAddressDTO getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(ShippingAddressDTO shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }
}
