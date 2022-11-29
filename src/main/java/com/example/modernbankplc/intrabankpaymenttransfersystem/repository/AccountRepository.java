package com.example.modernbankplc.intrabankpaymenttransfersystem.repository;

import com.example.modernbankplc.intrabankpaymenttransfersystem.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}