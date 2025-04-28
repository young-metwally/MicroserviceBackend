package com.islam.customerservice.services;

import com.islam.customerservice.dto.ShippingAddressDTO;

import java.util.List;

public interface ShippingAddressService {
    List<ShippingAddressDTO> getAllShippingAddresses();

    ShippingAddressDTO getShippingAddressById(Integer id);

    ShippingAddressDTO updateAddress(ShippingAddressDTO address, Integer addressId);
}
