package com.billpayment.creditcard.controller;

import com.billpayment.creditcard.dto.BaseResponse;
import com.billpayment.creditcard.dto.RegisterDetailRequest;
import com.billpayment.creditcard.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreditCardController {

    @Autowired
    private CreditCardService creditCardService;


    /*
    * post mapping for register user detail
    *
    * */


    @PostMapping("register-user-detail")
    public ResponseEntity<BaseResponse> registerUserDetail(@RequestBody RegisterDetailRequest registerDetailRequest) {
        if (registerDetailRequest == null || registerDetailRequest.getRegisterMobileNo().isEmpty()) {
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setMessage("registration detail can not be empty");
            baseResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
            baseResponse.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());

            return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
        }
        return creditCardService.registerUserDetail(registerDetailRequest);
    }

    /*
    * post mapping for login user
    *
    * */

    @PostMapping("login-user/{registerId}/{registerPassword}")
    public ResponseEntity<BaseResponse> loginUser(@PathVariable int registerId,@PathVariable String registerPassword,@RequestBody RegisterDetailRequest registerDetailRequest)
    {
        if (registerId==0&&registerPassword==null)
        {
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setMessage("please enter valid credentials");
            baseResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
            baseResponse.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());

            return new ResponseEntity<>(baseResponse,HttpStatus.BAD_REQUEST);
        }
        return creditCardService.loginUser(registerId,registerPassword,registerDetailRequest);
    }
}
