package com.billpayment.creditcard.controller;

import com.billpayment.creditcard.dto.*;
import com.billpayment.creditcard.service.CreditCardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@CrossOrigin(origins = "*")
public class CreditCardController {

    @Autowired
    private CreditCardService creditCardService;


    /*
    * post mapping for new user registration
    * user register here all the detail(name ,email,mobile,password)
    *
    * */


    @PostMapping("register-user-detail")
    public ResponseEntity<BaseResponse> registerUserDetail(@RequestBody UserDetailRequest userDetailRequest) {
        if(userDetailRequest == null || userDetailRequest.getMobileNo().isEmpty()) {
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setMessage("registration detail can not be empty");
            baseResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
            baseResponse.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());

            return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
        }
        return creditCardService.userDetailRequest(userDetailRequest);
    }

    /*
    post mapping for log-in
    * register user login after enter valid emailId and password
    *
    * */

    @PostMapping("user-login")
    public ResponseEntity<BaseResponse> loginUser(  @RequestBody UserDetailRequest userDetailRequest)
    {
        if (userDetailRequest==null)
        {
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setMessage("please enter valid credentials");
            baseResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
            baseResponse.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());

            return new ResponseEntity<>(baseResponse,HttpStatus.BAD_REQUEST);
        }
        return creditCardService.loginUser(userDetailRequest);
    }

    /*
    * Get mapping for transaction-history of user
    *
    *  user see all the transaction history and payment detail
    *
    *
    * */

    @GetMapping("fetch-transaction-detail/{creditCardId}")
    public ResponseEntity<BaseResponse> fetchTransactionDetail( @PathVariable int creditCardId)
    {
        if (creditCardId==0 || String.valueOf(creditCardId) == null)
        {
            BaseResponse baseResponse=new BaseResponse();
            baseResponse.setMessage("credit card id can not empty or  null");
            baseResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
            baseResponse.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());

            return new ResponseEntity<>(baseResponse,HttpStatus.BAD_REQUEST);
        }
        return creditCardService.fetchTransactionDetail(creditCardId);
    }

    /*
    * post mapping
    * for pay the credit card bill
    * all payment thing like balance are updating in the user account
    * */

    @PostMapping("payment-details")
    public ResponseEntity<BaseResponse> fetchPaymentDetail(@RequestBody PaymentRequest paymentRequest)
    {
        if (paymentRequest.getPaymentAmount() ==0 || paymentRequest.getPaymentType() == null)
        {
            BaseResponse baseResponse=new BaseResponse();
            baseResponse.setMessage("Payment detail can not be empty");
            baseResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
            baseResponse.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());

            return new ResponseEntity<>(baseResponse,HttpStatus.BAD_REQUEST);
        }
        return creditCardService.fetchPaymentDetails(paymentRequest);
    }


    @PostMapping("full-payment")
    public ResponseEntity<BaseResponse> fullPayment(@RequestBody PayFullRequest payFullRequest)
    {
        if (payFullRequest.getPaymentAmount()==0||payFullRequest.getPaymentType()==null)
        {
            BaseResponse baseResponse=new BaseResponse();
            baseResponse.setMessage("payment detail can not be empty");
            baseResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
            baseResponse.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(baseResponse,HttpStatus.BAD_REQUEST);
        }
        return creditCardService.payFull(payFullRequest);
    }

  /*  @PostMapping("save-creditCard-details")
    public boolean saveCreditCardPic(@ModelAttribute CreditCardPic creditCardPic)
    {
        return creditCardService.saveCreditCardPic(creditCardPic);
    }
*/

    /*
    * get mapping for fetch credit card using user id
    * here user see list of credit card user have

    * */

    @GetMapping("fetch-creditCards/{userId}")
    public ResponseEntity<BaseResponse> fetchCreditCards(@PathVariable int userId)
    {
        if (userId==0 || String.valueOf(userId) == null)
        {
            BaseResponse baseResponse=new BaseResponse();
            baseResponse.setMessage("Transaction id cannot be null");
            baseResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
            baseResponse.setHttpStatusCode(HttpStatus.OK.value());

            return new ResponseEntity<>(baseResponse,HttpStatus.BAD_REQUEST);
        }
        return creditCardService.fetchCreditCard(userId);
    }

}
