package com.billpayment.creditcard.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Table(name="creditCardPic")
public class CreditCardPicture {

    @Id
    @GeneratedValue
    @Column(name = "credit_card_id")
    private int creditCardId;

    @Column(name="credit_card_name")
    private String creditCardName;

    @Column(name = "credit_card_pic_path")
    private String creditCardPicPath;
}
