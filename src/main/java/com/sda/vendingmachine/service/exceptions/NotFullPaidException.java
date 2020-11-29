package com.sda.vendingmachine.service.exceptions;

public class NotFullPaidException extends Exception{


    @Override
    public String getMessage() {
        return "Not enough money for this product";
    }
}
