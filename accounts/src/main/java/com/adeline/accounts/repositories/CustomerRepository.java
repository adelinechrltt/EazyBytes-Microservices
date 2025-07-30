package com.adeline.accounts.repositories;

import com.adeline.accounts.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    ///  passing optional because there either can be a customer based on this mobile number or not
    Optional<Customer> findByMobileNumber(String mobileNumber); /// --> derived name methods
}
