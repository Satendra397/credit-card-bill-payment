package com.billpayment.creditcard.serviceImpl;
import antlr.StringUtils;
import com.billpayment.creditcard.dto.*;
import com.billpayment.creditcard.entity.*;
import com.billpayment.creditcard.repository.*;
import com.billpayment.creditcard.service.CreditCardService;
import com.billpayment.creditcard.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CreditCardServiceImpl implements CreditCardService {


    @Autowired
    private UserDAO userDAO;

    @Autowired
    private CreditCardDAO creditCardDAO;

    @Autowired
    private TransactionDAO transactionDAO;

    @Autowired
    private CreditCardPicDAO creditCardPicDAO;
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
    public ResponseEntity<BaseResponse> fetchTransactionDetail( int transactionId) {

            BaseResponse baseResponse = new BaseResponse();


            List<Transaction> transactionList = Collections.singletonList(transactionDAO.findDetailsByTransactionId(transactionId));

            TransactionDetailRequest transactionDetailRequest = new TransactionDetailRequest();

            List<CreditCardDetail> creditCardDetails = new ArrayList<>();
            for(Transaction transactionHistory:transactionList){
                CreditCardDetail creditCardDetail = new CreditCardDetail();
                //creditCardDetail.setCreditCardId(transactionHistory.getTransactionId());
                //creditCardDetail.setCreditCardBalance(transactionHistory.getTransactionAmount());
                //*creditCardDetail.setCreditCardLimit(transactionHistory.getTransactionAmount());
                creditCardDetail.setCreditCardExpense(transactionHistory.getTransactionAmount());
                transactionDetailRequest.setTransactionId(transactionHistory.getTransactionId());
                transactionDetailRequest.setTransactionDate(transactionHistory.getTransactionDate());
                transactionDetailRequest.setTransactionAmount(transactionHistory.getTransactionAmount());

                creditCardDetails.add(creditCardDetail);

            }

            transactionDetailRequest.setCreditCardDetails(creditCardDetails);

            baseResponse.setMessage("Transaction data found successfully");
            baseResponse.setHttpStatusCode(HttpStatus.OK.value());
            baseResponse.setHttpStatus(HttpStatus.OK);
            baseResponse.setResponse(transactionDetailRequest);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);



    }

    @Override
    public ResponseEntity<BaseResponse> fetchPaymentDetails(PaymentRequest paymentRequest) {

        int amount = 0;
        BaseResponse baseResponse = new BaseResponse();

        Optional<CreditCard> creditCard = creditCardDAO.findById(paymentRequest.getCreditCardId());
        if (!creditCard.isPresent()){
            baseResponse.setMessage("Card not found");
            baseResponse.setHttpStatus(HttpStatus.NOT_FOUND);
            baseResponse.setHttpStatusCode(HttpStatus.NOT_FOUND.value());
        }

        Transaction transaction = new Transaction();


        transaction.setTransactionAmount(paymentRequest.getPaymentAmount());
        transaction.setTransactionDate(new Date());
        transaction.setCreditCardId(paymentRequest.getCreditCardId());
        transactionDAO.save(transaction);

        amount = creditCard.get().getCreditCardBalance() + paymentRequest.getPaymentAmount();
        int updatedExpenses = creditCard.get().getCreditCardExpense() - paymentRequest.getPaymentAmount();
        creditCard.get().setCreditCardBalance(amount);
        creditCard.get().setCreditCardExpense(updatedExpenses);
        creditCardDAO.save(creditCard.get());



        baseResponse.setMessage("Payment Successful");
        baseResponse.setHttpStatus(HttpStatus.OK);
        baseResponse.setHttpStatusCode(HttpStatus.OK.value());

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BaseResponse> payFull(PayFullRequest payFullRequest) {

        int amount = 0;
        BaseResponse baseResponse = new BaseResponse();

        Optional<CreditCard> creditCard = creditCardDAO.findById(payFullRequest.getCreditCardId());
        if (!creditCard.isPresent()){
            baseResponse.setMessage("Card not found");
            baseResponse.setHttpStatus(HttpStatus.NOT_FOUND);
            baseResponse.setHttpStatusCode(HttpStatus.NOT_FOUND.value());
        }

        Transaction transaction = new Transaction();

        transaction.setTransactionAmount(payFullRequest.getPaymentAmount());
        transaction.setTransactionDate(new Date());
        transaction.setCreditCardId(payFullRequest.getCreditCardId());
        transactionDAO.save(transaction);


        amount = creditCard.get().getCreditCardBalance() + payFullRequest.getPaymentAmount();

        int updatedExpenses = creditCard.get().getCreditCardExpense() - payFullRequest.getPaymentAmount();
        creditCard.get().setCreditCardBalance(amount);
        creditCard.get().setCreditCardExpense(updatedExpenses);
        creditCardDAO.save(creditCard.get());



        baseResponse.setMessage("Payment Successful");
        baseResponse.setHttpStatus(HttpStatus.OK);
        baseResponse.setHttpStatusCode(HttpStatus.OK.value());

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

   /* @Override
    public boolean saveCreditCardPic(CreditCardPic creditCardPic) {
        String fileName= StringUtils.cleanPath(creditCardPic.getCreditCardPic().getOriginalFilename());
        CreditCardPic cardPic=new CreditCardPic();
        cardPic.setCreditCardName(creditCardPic.getCreditCardName());
        cardPic.setCreditCardPic(fileName);

        CreditCardPic saveCreditCardPic=creditCardPicDAO.save(cardPic);

        String uploadDir = "card-photos";

        FileUtils.saveFile(uploadDir,fileName,creditCardPic.getCreditCardPic());
        return true;

    }*/


}