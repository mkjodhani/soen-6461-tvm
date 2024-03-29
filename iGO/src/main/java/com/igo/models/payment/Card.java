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
    public enum TYPES{
        CREDIT_CARD,
        DEBIT_CARD
    }
    private String cardNumber, expireDate;
    private int cvv;
    private TYPES cardType;
    public static Card getCardByDetails(String cardNumber, String dueDate, int cvv,TYPES cardType){
        return new Card(cardNumber,dueDate,cvv,cardType);
//        String cardNumberPattern = "^(?:4[0-9]{12}(?:[0-9]{3})?|[25][1-7][0-9]{14}|6(?:011|5[0-9][0-9])[0-9]{12}|3[47][0-9]{13}|3(?:0[0-5]|[68][0-9])[0-9]{11}|(?:2131|1800|35\\d{3})\\d{11})$";
//        String expireDatePattern = "^(\\d{2})/(\\d{2})$";
//        if(validate(cardNumberPattern,cardNumber) && validate(expireDatePattern,dueDate)){
//            return new Card(cardNumber,dueDate,cvv,cardType);
//        }
//        else{
//            return null;
//        }
    }
    private Card(String cardNumber, String expireDate, int cvv, TYPES cardType) {
        this.cardNumber = cardNumber;
        this.expireDate = expireDate;
        this.cvv = cvv;
        this.cardType = cardType;
    }

    public static boolean validate(String regex , String word){
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
