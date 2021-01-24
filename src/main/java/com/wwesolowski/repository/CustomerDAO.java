package com.wwesolowski.repository;

import com.wwesolowski.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDAO extends CrudRepository<Customer, Long> {
    Customer findByCustomerDigitId(String customerDigitId);
}
