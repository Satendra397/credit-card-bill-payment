package com.billpayment.creditcard.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int transactionId;
    private Date transactionDate;
    private int transactionAmount;


    @Column(name="credit_card_id")
    private int creditCardId;

}
