package com.sda.vendingmachine.service.exceptions;

public class NotSufficientChangeException extends Exception {

    @Override
    public String getMessage() {
        return "Not sufficient change";
    }
}