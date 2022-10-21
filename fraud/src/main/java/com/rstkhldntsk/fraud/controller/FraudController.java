package com.rstkhldntsk.fraud.controller;

import com.rstkhldntsk.client.fraud.FraudCheckResponse;
import com.rstkhldntsk.fraud.service.FraudCheckService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/fraud-check")
@Slf4j
public record FraudController(FraudCheckService fraudCheckService) {

    @PostMapping("/{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable Long customerId) {
        boolean isFraudulentCustomer = fraudCheckService.isFraudulentCustomer(customerId);
        log.info("Fraud check request for customer {}", customerId);
        return new FraudCheckResponse(isFraudulentCustomer);
    }

}
