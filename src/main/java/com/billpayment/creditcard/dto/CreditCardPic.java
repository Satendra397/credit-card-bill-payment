package com.billpayment.creditcard.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CreditCardPic {

    private int creditCardId;
    private String creditCardName;
    private MultipartFile creditCardPic;
}
