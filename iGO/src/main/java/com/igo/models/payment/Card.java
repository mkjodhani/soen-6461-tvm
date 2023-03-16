package com.igo.models.payment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author mkjodhani
 * @version 1.0
 * @project SDM TVM Project
 * @since 01/03/23
 */


public class Card {
    enum TYPES{
        CREDIT_CARD,
        DEBIT_CARD
    }
    private String cardNumber, expireDate;
    private int cvv;
    private TYPES cardType;
    public Card getCardByDetails(String cardNumber, String dueDate, int cvv,TYPES cardType){
        String cardNumberPattern = "^(\\d{4})(\\d{4})(\\d{4})(\\d{4})$";
        String expireDatePattern = "^(\\d{2})/(\\d{2})$";
        if(validate(cardNumberPattern,cardNumber) && validate(expireDatePattern,dueDate)){
            return new Card(cardNumber,dueDate,cvv,cardType);
        }
        else{
            throw new Error("Card details are not valid.");
        }
    }
    private Card(String cardNumber, String expireDate, int cvv, TYPES cardType) {
        this.cardNumber = cardNumber;
        this.expireDate = expireDate;
        this.cvv = cvv;
        this.cardType = cardType;
    }

    public boolean validate(String regex , String word){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(word);
        return matcher.find();
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public int getCvv() {
        return cvv;
    }

    public TYPES getCardType() {
        return cardType;
    }
}
