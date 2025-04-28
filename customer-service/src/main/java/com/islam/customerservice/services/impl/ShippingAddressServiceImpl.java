package com.islam.customerservice.services.impl;

import com.islam.customerservice.Repository.ShippingAddressRepository;
import com.islam.customerservice.converter.TempConverter;
import com.islam.customerservice.dto.ShippingAddressDTO;
import com.islam.customerservice.entity.ShippingAddressEntity;
import com.islam.customerservice.exceptions.InstanceUndefinedException;
import com.islam.customerservice.services.ShippingAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShippingAddressServiceImpl implements ShippingAddressService {

    @Autowired
    private ShippingAddressRepository shippingAddressRepository;
    @Autowired
    private TempConverter converter;

    @Override
    @Transactional(readOnly = true)
    public List<ShippingAddressDTO> getAllShippingAddresses() {
        List<ShippingAddressEntity> allAddresses = shippingAddressRepository.findAll();
        List<ShippingAddressDTO> shippingAddressDTOs = new ArrayList<ShippingAddressDTO>();
        for (ShippingAddressEntity shippingAddressEntity : allAddresses) {
            shippingAddressDTOs.add(converter.shippingAddressEntityToDto(shippingAddressEntity));
        }
        return shippingAddressDTOs;
    }

    @Override
    @Transactional(readOnly = true)
    public ShippingAddressDTO getShippingAddressById(Integer id) {
        Optional<ShippingAddressEntity> shippingAddressEntityOptional = shippingAddressRepository.findById(id);
        if (shippingAddressEntityOptional.isPresent()) {
            ShippingAddressEntity shippingAddressEntity = shippingAddressEntityOptional.get();
            return converter.shippingAddressEntityToDto(shippingAddressEntity);
        } else {
            throw new InstanceUndefinedException(new Error("Address not found!"));
        }
    }

    @Override
    @Transactional
    public ShippingAddressDTO updateAddress(ShippingAddressDTO address, Integer addressId) {
        ShippingAddressDTO currentAddress = getShippingAddressById(addressId);
        address.setId(currentAddress.getId());
        address.setCustomerId(currentAddress.getCustomerId());
        ShippingAddressEntity updatedAddress = shippingAddressRepository
                .saveAndFlush(converter.shippingAddressDtoToEntity(address));
        return converter.shippingAddressEntityToDto(updatedAddress);
    }

}
