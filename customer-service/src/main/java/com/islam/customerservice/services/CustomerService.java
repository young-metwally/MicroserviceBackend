package com.islam.customerservice.services;

import com.islam.customerservice.dto.CustomerDTO;
import com.islam.customerservice.utils.RegistrationForm;

import java.util.List;

public interface CustomerService {
    List<CustomerDTO> getAllCustomers();

    CustomerDTO getCustomerById(int id);

    CustomerDTO addCustomer(RegistrationForm form);

    CustomerDTO updateCustomer(CustomerDTO customer);

    CustomerDTO getCustomerByUserId(Integer userId);

    CustomerDTO getCurrentCustomer();

    void deleteCustomerById(int id);

    void suspendCustomer(Integer customerId);

    void reactivateCustomer(Integer customerId);

}
