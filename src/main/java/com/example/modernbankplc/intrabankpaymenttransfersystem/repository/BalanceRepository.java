package com.example.modernbankplc.intrabankpaymenttransfersystem.repository;

import com.example.modernbankplc.intrabankpaymenttransfersystem.domain.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, Long> {
}
