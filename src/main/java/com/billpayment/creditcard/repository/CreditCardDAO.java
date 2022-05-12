package com.billpayment.creditcard.repository;

import com.billpayment.creditcard.entity.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditCardDAO extends JpaRepository<CreditCard,Integer> {


    @Query(value = "select * from credit_card where user_user_id=?1", nativeQuery = true)
    List<CreditCard> findDetailsByUserID(int userId);
}
