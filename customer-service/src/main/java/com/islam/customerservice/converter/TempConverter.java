package com.islam.customerservice.converter;

import com.islam.customerservice.Repository.CustomerRepository;
import com.islam.customerservice.Repository.ShippingAddressRepository;
import com.islam.customerservice.dto.CustomerDTO;
import com.islam.customerservice.dto.ShippingAddressDTO;
import com.islam.customerservice.entity.CustomerEntity;
import com.islam.customerservice.entity.ShippingAddressEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TempConverter {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ShippingAddressRepository shippingAddressRepository;


    public CustomerDTO customerEntityToDto(CustomerEntity customerEntity) {
        CustomerDTO customerDTO = modelMapper.map(customerEntity, CustomerDTO.class);
        Optional<ShippingAddressEntity> shippingAddressOptional = Optional
                .ofNullable(customerEntity.getAddress());
        if (shippingAddressOptional.isPresent()) {
            ShippingAddressEntity shippingAddress = shippingAddressOptional.get();
            customerDTO.setAddressId(shippingAddress.getId());
        }

        return customerDTO;
    }

    public CustomerEntity customerDtoToEntity(CustomerDTO customerDTO) {
        CustomerEntity customerEntity = modelMapper.map(customerDTO, CustomerEntity.class);
        Optional<Integer> addressIdOptional = Optional.ofNullable(customerDTO.getAddressId());
        if (addressIdOptional.isPresent()) {
            Integer addressId = addressIdOptional.get();
            ShippingAddressEntity shippingAddress = shippingAddressRepository.findById(addressId).orElse(null);
            if (shippingAddress != null) {
                customerEntity.setAddress(shippingAddress);
            }
        }
        return customerEntity;
    }

    public ShippingAddressDTO shippingAddressEntityToDto(ShippingAddressEntity addressEntity) {
        ShippingAddressDTO shippingAddressDTO = modelMapper.map(addressEntity, ShippingAddressDTO.class);
        Optional<CustomerEntity> customerEntityOptional = Optional.ofNullable(addressEntity.getCustomer());
        if (customerEntityOptional.isPresent()) {
            CustomerEntity customerEntity = customerEntityOptional.get();
            shippingAddressDTO.setCustomerId(customerEntity.getId());
        }
        return shippingAddressDTO;
    }

    public ShippingAddressEntity shippingAddressDtoToEntity(ShippingAddressDTO addressDTO) {
        ShippingAddressEntity shippingAddress = modelMapper.map(addressDTO, ShippingAddressEntity.class);
        Optional<Integer> customerIdOptional = Optional.ofNullable(addressDTO.getCustomerId());
        if (customerIdOptional.isPresent()) {
            CustomerEntity customerEntity = customerRepository.findById(customerIdOptional.get()).orElse(null);
            if (customerEntity != null) {
                shippingAddress.setCustomer(customerEntity);
            }
        }
        return shippingAddress;
    }

}
