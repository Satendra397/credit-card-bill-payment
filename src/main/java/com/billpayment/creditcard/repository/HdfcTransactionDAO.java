package com.billpayment.creditcard.repository;

import com.billpayment.creditcard.dto.HdfcTransactionResponse;
import com.billpayment.creditcard.entity.HdfcTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface HdfcTransactionDAO extends JpaRepository<HdfcTransaction,Integer> {


}
