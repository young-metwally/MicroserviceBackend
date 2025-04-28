package com.islam.customerservice.services.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.islam.customerservice.Repository.CustomerRepository;
import com.islam.customerservice.Repository.ShippingAddressRepository;
import com.islam.customerservice.converter.TempConverter;
import com.islam.customerservice.dto.CustomerDTO;
import com.islam.customerservice.dto.ShippingAddressDTO;
import com.islam.customerservice.entity.CustomerEntity;
import com.islam.customerservice.entity.ShippingAddressEntity;
import com.islam.customerservice.exceptions.DuplicateEntryException;
import com.islam.customerservice.exceptions.InstanceUndefinedException;
import com.islam.customerservice.services.CustomerService;
import com.islam.customerservice.utils.RegistrationForm;
import org.apache.http.HttpEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TempConverter converter;

    @Autowired
    private ShippingAddressRepository shippingAddressRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CustomerDTO> getAllCustomers() {
        List<CustomerEntity> entities = customerRepository.findAll();
        List<CustomerDTO> dtos = new ArrayList<>();
        for (CustomerEntity entity : entities) {
            dtos.add(converter.customerEntityToDto(entity));
        }
        return dtos;
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerDTO getCustomerById(int id) {
        Optional<CustomerEntity> entity = customerRepository.findById(id);
        if (entity.isPresent()) {
            return converter.customerEntityToDto(entity.get());
        } else {
            throw new InstanceUndefinedException(new Error("Customer not found!"));
        }
    }

    @Override
    public CustomerDTO addCustomer(RegistrationForm form) {
        ShippingAddressDTO shippingAddressDTO = form.getShippingAddress();
        CustomerDTO customerDTO = form.getCustomer();
        JsonNode user = form.getUser();
        String userServiceUrl = "http://user-service/api/users/saves";
        HttpEntity <JsonNode> requestEntity = new HttpEntity<>(user);
        JsonNode response = restTemplate.postForObject(userServiceUrl, requestEntity, JsonNode.class);
    }


    @Override
    @Transactional
    public CustomerDTO updateCustomer(CustomerDTO customer) {
        CustomerDTO currentCustomer = getCurrentCustomer();
        customer.setCartId(currentCustomer.getCartId());
        customer.setId(currentCustomer.getId());
        customer.setAddressId(currentCustomer.getAddressId());
        customer.setUserId(currentCustomer.getUserId());
        CustomerEntity updatedCustomer = customerRepository.saveAndFlush(converter.customerDtoToEntity(customer));
        return converter.customerEntityToDto(updatedCustomer);
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerDTO getCustomerByUserId(Integer userId) {
        CustomerEntity entity = customerRepository.findByUserId(userId)
                .orElseThrow(() -> new InstanceUndefinedException(new Error("Customer not found!")));
        return converter.customerEntityToDto(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerDTO getCurrentCustomer() {
        // Placeholder implementation for current customer retrieval
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    @Transactional
    public void deleteCustomerById(int id) {
        CustomerDTO customer = getCustomerById(id);
        customerRepository.deleteById(customer.getId());
        customerRepository.flush();
    }

    @Override
    @Transactional
    public void suspendCustomer(Integer customerId) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    @Transactional
    public void reactivateCustomer(Integer customerId) {
        throw new UnsupportedOperationException("Method not implemented");
    }
}