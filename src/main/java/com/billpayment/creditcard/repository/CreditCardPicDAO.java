package com.billpayment.creditcard.repository;

import com.billpayment.creditcard.entity.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardPicDAO extends JpaRepository<CreditCard,Integer> {

}
