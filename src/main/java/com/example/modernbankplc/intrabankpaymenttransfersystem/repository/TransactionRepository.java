package com.example.modernbankplc.intrabankpaymenttransfersystem.repository;

import com.example.modernbankplc.intrabankpaymenttransfersystem.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
