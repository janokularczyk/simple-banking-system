package com.jano;

public class Account {
    public String cardNumber;
    public String pin;

    public String getCardNumber() {
        return cardNumber;
    }

    public String getPIN() {
        return pin;
    }

    public Account(String cardNumber, String pin) {
        this.cardNumber = cardNumber;
        this.pin = pin;
    }
}
