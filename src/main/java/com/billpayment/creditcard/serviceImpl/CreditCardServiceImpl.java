package com.billpayment.creditcard.serviceImpl;

import com.billpayment.creditcard.dto.*;
import com.billpayment.creditcard.entity.*;
import com.billpayment.creditcard.repository.*;
import com.billpayment.creditcard.service.CreditCardService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CreditCardServiceImpl implements CreditCardService {


    @Autowired
    private UserDAO userDAO;

    @Autowired
    private CreditCardDAO creditCardDAO;

    @Autowired
    private TransactionDAO transactionDAO;

    @Autowired
    private HdfcTransactionDAO hdfcTransactionDAO;
    @Autowired
    private SbiTransactionDAO sbiTransactionDAO;
    @Autowired
    private CANARATransactionDAO canaraTransactionDAO;

    /*
     * post mapping for register user detail
     *
     * */

    @Override
    public ResponseEntity<BaseResponse> userDetailRequest(UserDetailRequest userDetailRequest) {

        User user = new User();

        user.setUserId(userDetailRequest.getUserId());
        user.setUserName(userDetailRequest.getUserName());
        user.setMobileNo(userDetailRequest.getMobileNo());
        user.setUserEmail(userDetailRequest.getUserEmail());
        user.setUserPassword(userDetailRequest.getUserPassword());
        userDAO.save(user);

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessage("detail save successfully!!!");
        baseResponse.setHttpStatus(HttpStatus.OK);
        baseResponse.setHttpStatusCode(HttpStatus.OK.value());
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);

    }

    /*
     * post mapping for login
     * */
    @Override
    public ResponseEntity<BaseResponse> loginUser(UserDetailRequest userDetailRequest) {


        Optional<Object> user = Optional.ofNullable(userDAO.login(userDetailRequest.getUserEmail(), userDetailRequest.getUserPassword()));

        BaseResponse baseResponse = new BaseResponse();
        if (!user.isPresent()) {
            baseResponse.setMessage("user not found ");
            baseResponse.setHttpStatus(HttpStatus.NOT_FOUND);
            baseResponse.setHttpStatusCode(HttpStatus.NOT_FOUND.value());

            return new ResponseEntity<>(baseResponse, HttpStatus.NOT_FOUND);
        }
        baseResponse.setMessage("user login successfully");
        baseResponse.setHttpStatus(HttpStatus.OK);
        baseResponse.setHttpStatusCode(HttpStatus.OK.value());

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);

    }

        @Override
    public ResponseEntity<BaseResponse> fetchCreditCardDetail( int creditCardId) {

        BaseResponse baseResponse = new BaseResponse();


        switch(creditCardId) {
            case 1:

                Optional<HdfcTransaction> hdfcTransaction=hdfcTransactionDAO.findById(creditCardId);
                HdfcTransaction hdfcTransaction1=hdfcTransaction.get();
                List<HdfcTransactionResponse> hdfcTransactionResponses=new ArrayList<>();

                    HdfcTransactionResponse hdfcTransactionResponse1=new HdfcTransactionResponse();
                    hdfcTransactionResponse1.setHdfcTransactionId(hdfcTransaction1.getHdfcTransactionId());
                    hdfcTransactionResponse1.setHdfcTransactionAmount(hdfcTransaction1.getHdfcTransactionAmount());
                    hdfcTransactionResponse1.setHdfcTransactionDate(hdfcTransaction1.getHdfcTransactionDate());
                    hdfcTransactionResponses.add(hdfcTransactionResponse1);

                HdfcTransactionResponse hdfcTransactionResponse2=new HdfcTransactionResponse();
                hdfcTransactionResponse2.setHdfcTransactionId(hdfcTransaction1.getHdfcTransactionId());
                hdfcTransactionResponse2.setHdfcTransactionAmount(hdfcTransaction1.getHdfcTransactionAmount());
                hdfcTransactionResponse2.setHdfcTransactionDate(hdfcTransaction1.getHdfcTransactionDate());
                hdfcTransactionResponses.add(hdfcTransactionResponse2);

                HdfcTransactionResponse hdfcTransactionResponse3=new HdfcTransactionResponse();
                hdfcTransactionResponse3.setHdfcTransactionId(hdfcTransaction1.getHdfcTransactionId());
                hdfcTransactionResponse3.setHdfcTransactionAmount(hdfcTransaction1.getHdfcTransactionAmount());
                hdfcTransactionResponse3.setHdfcTransactionDate(hdfcTransaction1.getHdfcTransactionDate());
                hdfcTransactionResponses.add(hdfcTransactionResponse3);

                HdfcTransactionResponse hdfcTransactionResponse4=new HdfcTransactionResponse();
                hdfcTransactionResponse4.setHdfcTransactionId(hdfcTransaction1.getHdfcTransactionId());
                hdfcTransactionResponse4.setHdfcTransactionAmount(hdfcTransaction1.getHdfcTransactionAmount());
                hdfcTransactionResponse4.setHdfcTransactionDate(hdfcTransaction1.getHdfcTransactionDate());
                hdfcTransactionResponses.add(hdfcTransactionResponse4);

                HdfcTransactionResponse hdfcTransactionResponse5=new HdfcTransactionResponse();
                hdfcTransactionResponse5.setHdfcTransactionId(hdfcTransaction1.getHdfcTransactionId());
                hdfcTransactionResponse5.setHdfcTransactionAmount(hdfcTransaction1.getHdfcTransactionAmount());
                hdfcTransactionResponse5.setHdfcTransactionDate(hdfcTransaction1.getHdfcTransactionDate());
                hdfcTransactionResponses.add(hdfcTransactionResponse5);
                baseResponse.setResponse(hdfcTransactionResponses);


                baseResponse.setMessage("HDFC credit card Transaction record found");
                baseResponse.setHttpStatus(HttpStatus.OK);
                baseResponse.setHttpStatusCode(HttpStatus.OK.value());
                return new ResponseEntity<BaseResponse>(baseResponse,HttpStatus.OK);


            case 2:

                Optional<SbiTransaction> sbiTransaction=sbiTransactionDAO.findById(creditCardId);
                SbiTransaction sbiTransaction1=sbiTransaction.get();

                List<SbiTransactionResponse> sbiTransactionResponseList=new ArrayList<>();

                SbiTransactionResponse sbiTransactionResponse1=new SbiTransactionResponse();
                sbiTransactionResponse1.setSbiTransactionId(sbiTransaction1.getSbiTransactionId());
               // sbiTransactionResponse1.setSbiTransactionDate(sbiTransaction1.getSbiTransactionDate());
                sbiTransactionResponse1.setSbiTransactionAmount(sbiTransaction1.getSbiTransactionAmount());
                sbiTransactionResponseList.add(sbiTransactionResponse1);

                SbiTransactionResponse sbiTransactionResponse2=new SbiTransactionResponse();
                sbiTransactionResponse2.setSbiTransactionId(sbiTransaction1.getSbiTransactionId());
                sbiTransactionResponse2.setSbiTransactionAmount(sbiTransaction1.getSbiTransactionAmount());
               // sbiTransactionResponse2.setSbiTransactionDate((Data) sbiTransaction1.getSbiTransactionDate());
                sbiTransactionResponseList.add(sbiTransactionResponse2);

                SbiTransactionResponse sbiTransactionResponse3=new SbiTransactionResponse();
                sbiTransactionResponse3.setSbiTransactionId(sbiTransaction1.getSbiTransactionId());
                sbiTransactionResponse3.setSbiTransactionAmount(sbiTransaction1.getSbiTransactionAmount());
                //sbiTransactionResponse3.setSbiTransactionDate((Data) sbiTransaction1.getSbiTransactionDate());
                sbiTransactionResponseList.add(sbiTransactionResponse3);

                SbiTransactionResponse sbiTransactionResponse4=new SbiTransactionResponse();
                sbiTransactionResponse4.setSbiTransactionId(sbiTransaction1.getSbiTransactionId());
                sbiTransactionResponse4.setSbiTransactionAmount(sbiTransaction1.getSbiTransactionAmount());
                //sbiTransactionResponse4.setSbiTransactionDate((Data) sbiTransaction1.getSbiTransactionDate());
                sbiTransactionResponseList.add(sbiTransactionResponse4);

                SbiTransactionResponse sbiTransactionResponse5=new SbiTransactionResponse();
                sbiTransactionResponse5.setSbiTransactionId(sbiTransaction1.getSbiTransactionId());
                sbiTransactionResponse5.setSbiTransactionAmount(sbiTransaction1.getSbiTransactionAmount());
               // sbiTransactionResponse5.setSbiTransactionDate((Data) sbiTransaction1.getSbiTransactionDate());
                sbiTransactionResponseList.add(sbiTransactionResponse5);
                baseResponse.setResponse(sbiTransactionResponseList);

                baseResponse.setMessage(" SBI credit card transaction record found successfully");
                baseResponse.setHttpStatus(HttpStatus.OK);
                baseResponse.setHttpStatusCode(HttpStatus.OK.value());
                return new ResponseEntity<>(baseResponse,HttpStatus.OK);

            case 3:

                Optional<CANARATransaction> canaraTransaction=canaraTransactionDAO.findById(creditCardId);
                CANARATransaction canaraTransaction1=canaraTransaction.get();

                List<CANARATransactionResponse> canaraTransactionResponseList=new ArrayList<>();

                CANARATransactionResponse canaraTransactionResponse1=new CANARATransactionResponse();
                canaraTransactionResponse1.setCnrTransactionId(canaraTransaction1.getCnrTransactionId());
               canaraTransactionResponse1.setCnrTransactionDate(canaraTransaction1.getCnrTransactionDate());
               canaraTransactionResponse1.setCnrTransactionAmount(canaraTransaction1.getCnrTransactionAmount());
               canaraTransactionResponseList.add(canaraTransactionResponse1);

                CANARATransactionResponse canaraTransactionResponse2=new CANARATransactionResponse();
                canaraTransactionResponse2.setCnrTransactionId(canaraTransaction1.getCnrTransactionId());
                canaraTransactionResponse2.setCnrTransactionDate(canaraTransaction1.getCnrTransactionDate());
                canaraTransactionResponse2.setCnrTransactionAmount(canaraTransaction1.getCnrTransactionAmount());
                canaraTransactionResponseList.add(canaraTransactionResponse2);

                CANARATransactionResponse canaraTransactionResponse3=new CANARATransactionResponse();
                canaraTransactionResponse3.setCnrTransactionId(canaraTransaction1.getCnrTransactionId());
                canaraTransactionResponse3.setCnrTransactionDate(canaraTransaction1.getCnrTransactionDate());
                canaraTransactionResponse3.setCnrTransactionAmount(canaraTransaction1.getCnrTransactionAmount());
                canaraTransactionResponseList.add(canaraTransactionResponse3);

                CANARATransactionResponse canaraTransactionResponse4=new CANARATransactionResponse();
                canaraTransactionResponse4.setCnrTransactionId(canaraTransaction1.getCnrTransactionId());
                canaraTransactionResponse4.setCnrTransactionDate(canaraTransaction1.getCnrTransactionDate());
                canaraTransactionResponse4.setCnrTransactionAmount(canaraTransaction1.getCnrTransactionAmount());
                canaraTransactionResponseList.add(canaraTransactionResponse4);

                CANARATransactionResponse canaraTransactionResponse5=new CANARATransactionResponse();
                canaraTransactionResponse5.setCnrTransactionId(canaraTransaction1.getCnrTransactionId());
                canaraTransactionResponse5.setCnrTransactionDate(canaraTransaction1.getCnrTransactionDate());
                canaraTransactionResponse5.setCnrTransactionAmount(canaraTransaction1.getCnrTransactionAmount());
                canaraTransactionResponseList.add(canaraTransactionResponse5);
                baseResponse.setResponse(canaraTransactionResponseList);


                baseResponse.setMessage(" Canara CREDIT CARD transaction record found successfully");


                break;
            default:
                baseResponse.setMessage("  no record found ");
        }
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }



}