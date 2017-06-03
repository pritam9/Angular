/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shopaholic.model;

import java.util.Date;

/**
 *
 * @author Pritam
 */
public class CreditCard {
    private String creditCardNumber,cardHolderName,expiryDate,validFromDate;
    private int cvv;

    public CreditCard(String creditCardNumber, String cardHolderName, String expiryDate, String validFromDate, int cvv) {
        this.creditCardNumber = creditCardNumber;
        this.cardHolderName = cardHolderName;
        this.expiryDate = expiryDate;
        this.validFromDate = validFromDate;
        this.cvv = cvv;
    }

    public CreditCard() {
    }

    

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getValidFromDate() {
        return validFromDate;
    }

    public void setValidFromDate(String validFromDate) {
        this.validFromDate = validFromDate;
    }

    

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }
    
    
}
