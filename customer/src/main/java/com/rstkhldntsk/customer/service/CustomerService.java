package com.rstkhldntsk.customer.service;

import com.rstkhldntsk.customer.dto.CustomerRegistrationRequest;
import com.rstkhldntsk.customer.dto.FraudCheckResponse;
import com.rstkhldntsk.customer.model.Customer;
import com.rstkhldntsk.customer.reposotory.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public record CustomerService(CustomerRepository customerRepository, RestTemplate restTemplate) {

    public Customer registerCustomer(CustomerRegistrationRequest customerRegistrationRequest) {
        var customer = Customer.builder()
                .firstName(customerRegistrationRequest.firstName())
                .lastName(customerRegistrationRequest.lastName())
                .email(customerRegistrationRequest.email())
                .build();
        Customer persistedCustomer = customerRepository.saveAndFlush(customer);
        var fraudCheckResponse = restTemplate.getForObject(
                "http://FRAUD/api/v1/fraud-check/{customerId}",
                FraudCheckResponse.class,
                customer.getId()
        );
        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }
        return persistedCustomer;
    }

}
