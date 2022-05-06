package com.billpayment.creditcard.dto;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class HdfcCreditCardResponse {

    private int HDFCCreditCardId;
    private String HDFCCreditCardName;
    private int HDFCCreditCardBalance;
    private int HDFCCreditCardLimit;
    private int HDFCCreditCardExpense;



}
