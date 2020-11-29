package com.sda.vendingmachine.service.exceptions;

public class SoldOutException extends Exception {

    @Override
    public String getMessage() {
        return "Item not available in stock";
    }
}
