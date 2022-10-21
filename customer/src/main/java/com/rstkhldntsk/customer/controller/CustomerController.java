package com.rstkhldntsk.customer.controller;

import com.rstkhldntsk.client.customer.CustomerRegistrationRequest;
import com.rstkhldntsk.customer.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/customer")
public record CustomerController(CustomerService customerService) {

    @PostMapping()
    public void registerCustomer(@RequestBody CustomerRegistrationRequest customerRegistrationRequest) {
        log.info("New customer registration {}", customerRegistrationRequest);
        var registeredCustomer = customerService.registerCustomer(customerRegistrationRequest);
        log.info("New customer registered {}", registeredCustomer);
    }

}
