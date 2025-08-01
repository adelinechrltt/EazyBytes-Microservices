package com.adeline.accounts.repositories;

import com.adeline.accounts.entities.Account;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByCustomerId(Long customerId);

    ///  two neccessary annotatinos for updating data
    @Transactional
    @Modifying
    void deleteByCustomerId(Long customerId); /// --> method query derivation
}
