package com.khaja.tacocloud.tacoorder.model;

import org.hibernate.validator.constraints.CreditCardNumber;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Pattern;

public class CreditCard {
    @CreditCardNumber(message = "Not a valid credit-card number")
    private String ccNumber;
    @Digits(integer = 3, fraction = 0, message = "invalid cvv")
    private String ccCVV;
    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$", message = "Must be formatted MM/YY")
    private String ccExpiration;

    public CreditCard() {
    }

    public CreditCard(String ccNumber, String ccCVV, String ccExpiration) {
        this.ccNumber = ccNumber;
        this.ccCVV = ccCVV;
        this.ccExpiration = ccExpiration;
    }

    public String getCcNumber() {
        return ccNumber;
    }

    public void setCcNumber(String ccNumber) {
        this.ccNumber = ccNumber;
    }

    public String getCcCVV() {
        return ccCVV;
    }

    public void setCcCVV(String ccCVV) {
        this.ccCVV = ccCVV;
    }

    public String getCcExpiration() {
        return ccExpiration;
    }

    public void setCcExpiration(String ccExpiration) {
        this.ccExpiration = ccExpiration;
    }

    @Override
    public String toString() {
        return "CreditCard [ccNumber=" + ccNumber + ", ccCVV=" + ccCVV + ", ccExpiration=" + ccExpiration + "]";
    }

}
