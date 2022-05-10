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
public class CANARATransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cnrTransactionId;
    private Date cnrTransactionDate;
    private int cnrTransactionAmount;
}
