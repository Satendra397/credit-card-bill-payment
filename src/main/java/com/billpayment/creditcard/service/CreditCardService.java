package com.billpayment.creditcard.service;


import com.billpayment.creditcard.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface CreditCardService {
    ResponseEntity<BaseResponse> userDetailRequest(UserDetailRequest userDetailRequest);

      ResponseEntity<BaseResponse> loginUser( UserDetailRequest userDetailRequest);//

    ResponseEntity<BaseResponse> fetchTransactionDetail(  int creditCardId);//etch transaction detail


    ResponseEntity<BaseResponse> fetchPaymentDetails(PaymentRequest paymentRequest);//

    ResponseEntity<BaseResponse> payFull(PayFullRequest payFullRequest);//

    ResponseEntity<BaseResponse> fetchCreditCard(int userId);//

    // boolean saveCreditCardPic(CreditCardPic creditCardPic);
}
