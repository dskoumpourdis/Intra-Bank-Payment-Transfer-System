package com.example.modernbankplc.intrabankpaymenttransfersystem.repository;

import com.example.modernbankplc.intrabankpaymenttransfersystem.domain.Statement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatementRepository extends JpaRepository<Statement, Long> {
}
