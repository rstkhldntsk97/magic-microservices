package com.rstkhldntsk.customer.service;

import com.rstkhldntsk.client.fraud.FraudClient;
import com.rstkhldntsk.customer.dto.CustomerRegistrationRequest;
import com.rstkhldntsk.customer.model.Customer;
import com.rstkhldntsk.customer.reposotory.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public record CustomerService(CustomerRepository customerRepository, FraudClient fraudClient) {

    public Customer registerCustomer(CustomerRegistrationRequest customerRegistrationRequest) {
        var customer = Customer.builder()
                .firstName(customerRegistrationRequest.firstName())
                .lastName(customerRegistrationRequest.lastName())
                .email(customerRegistrationRequest.email())
                .build();
        Customer persistedCustomer = customerRepository.saveAndFlush(customer);
        var fraudCheckResponse = fraudClient.isFraudster(customer.getId());
        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }
        return persistedCustomer;
    }

}
