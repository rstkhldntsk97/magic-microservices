package com.rstkhldntsk.customer.reposotory;

import com.rstkhldntsk.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
