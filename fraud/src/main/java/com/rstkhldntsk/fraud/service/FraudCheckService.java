package com.rstkhldntsk.fraud.service;

import com.rstkhldntsk.fraud.model.FraudCheckHistory;
import com.rstkhldntsk.fraud.repository.FraudRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public record FraudCheckService(FraudRepository fraudRepository) {

    public boolean isFraudulentCustomer(Long customerId) {
        fraudRepository.save(
                FraudCheckHistory.builder()
                        .isFraudster(false)
                        .customerId(customerId)
                        .createdAt(LocalDateTime.now())
                        .build()
        );
        return false;
    }

}
