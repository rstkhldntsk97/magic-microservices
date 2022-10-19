package com.rstkhldntsk.customer.service;

import com.rstkhldntsk.customer.dto.CustomerRegistrationRequest;
import com.rstkhldntsk.customer.model.Customer;
import com.rstkhldntsk.customer.reposotory.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public record CustomerService(CustomerRepository customerRepository) {

    public Customer registerCustomer(CustomerRegistrationRequest customerRegistrationRequest) {
        var customer = Customer.builder()
                .firstName(customerRegistrationRequest.firstName())
                .lastName(customerRegistrationRequest.lastName())
                .email(customerRegistrationRequest.email())
                .build();
        Customer persistedCustomer = customerRepository.save(customer);
        return persistedCustomer;
    }

}
