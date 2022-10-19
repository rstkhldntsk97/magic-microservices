package com.rstkhldntsk.fraud.controller;

import com.rstkhldntsk.fraud.dto.FraudCheckResponse;
import com.rstkhldntsk.fraud.service.FraudCheckService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/fraud-check")
public record FraudController(FraudCheckService fraudCheckService) {

    @GetMapping("/{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable Long customerId) {
        boolean isFraudulentCustomer = fraudCheckService.isFraudulentCustomer(customerId);
        return new FraudCheckResponse(isFraudulentCustomer);
    }
}
