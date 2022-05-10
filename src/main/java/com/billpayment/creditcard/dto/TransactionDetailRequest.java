package com.billpayment.creditcard.dto;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class TransactionDetailRequest {


    private int transactionId;
    private Date transactionDate;
    private int transactionAmount;
    List<HdfcTransactionResponse> hdfcTransactionList;
    List<SbiTransactionResponse> sbiTransactionList;
    List<CANARATransactionResponse>  canaraTransactionResponseList;

}
