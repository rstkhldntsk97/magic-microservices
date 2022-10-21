package com.rstkhldntsk.customer.service;

import com.rstkhldntsk.client.fraud.FraudClient;
import com.rstkhldntsk.client.customer.CustomerRegistrationRequest;
import com.rstkhldntsk.client.notification.NotificationClient;
import com.rstkhldntsk.client.notification.NotificationRequest;
import com.rstkhldntsk.customer.model.Customer;
import com.rstkhldntsk.customer.reposotory.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public record CustomerService(CustomerRepository customerRepository, FraudClient fraudClient, NotificationClient notificationClient) {

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
        NotificationRequest notificationRequest = new NotificationRequest(
                customer.getId(),
                customer.getEmail(),
                String.format("Hi %s, welcome to magic service...", customer.getFirstName())
        );
        notificationClient.send(notificationRequest);
        return persistedCustomer;
    }

}
