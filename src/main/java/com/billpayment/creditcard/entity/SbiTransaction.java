package com.billpayment.creditcard.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
public class SbiTransaction {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int sbiTransactionId;
    private Date sbiTransactionDate;
    private int sbiTransactionAmount;

}
