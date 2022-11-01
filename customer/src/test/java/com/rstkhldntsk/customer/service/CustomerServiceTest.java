package com.rstkhldntsk.customer.service;

import com.rstkhldntsk.amqp.RabbitMQMessageProducer;
import com.rstkhldntsk.client.customer.CustomerRegistrationRequest;
import com.rstkhldntsk.client.fraud.FraudCheckResponse;
import com.rstkhldntsk.client.fraud.FraudClient;
import com.rstkhldntsk.customer.model.Customer;
import com.rstkhldntsk.customer.reposotory.CustomerRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    private CustomerService customerService;

    private FraudClient fraudClient;

    private Customer testCustomer;

    @BeforeAll
    public void init() {
        testCustomer = new Customer(1L, "firstName", "lastName", "email@gmail.com");
        fraudClient = Mockito.mock(FraudClient.class);
        CustomerRepository customerRepository = Mockito.mock(CustomerRepository.class);
        RabbitMQMessageProducer producer = Mockito.mock(RabbitMQMessageProducer.class);
        customerService = new CustomerService(customerRepository, fraudClient, producer);

        when(customerRepository.saveAndFlush(any(Customer.class))).thenReturn(testCustomer);
    }

    @Test
    public void shouldRegisterCustomer() {
        CustomerRegistrationRequest customerRegistrationRequest = new CustomerRegistrationRequest("firstName", "lastName", "email@gmail.com");
        when(fraudClient.isFraudster(anyLong())).thenReturn(new FraudCheckResponse(false));
        assertEquals(testCustomer, customerService.registerCustomer(customerRegistrationRequest));
    }

    @Test
    public void shouldThrowExceptionWhenCustomerIsFraudulent() {
        CustomerRegistrationRequest customerRegistrationRequest = new CustomerRegistrationRequest("firstName", "lastName", "email@gmail.com");
        when(fraudClient.isFraudster(anyLong())).thenReturn(new FraudCheckResponse(true));
        assertThrows(IllegalStateException.class, () -> customerService.registerCustomer(customerRegistrationRequest));
    }

}