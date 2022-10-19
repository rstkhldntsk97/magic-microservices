package com.rstkhldntsk.fraud.repository;

import com.rstkhldntsk.fraud.model.FraudCheckHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FraudRepository extends JpaRepository<FraudCheckHistory, Long> {

}
