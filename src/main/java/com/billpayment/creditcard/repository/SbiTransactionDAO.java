package com.billpayment.creditcard.repository;

import com.billpayment.creditcard.entity.SbiTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SbiTransactionDAO extends JpaRepository<SbiTransaction,Integer> {
}
