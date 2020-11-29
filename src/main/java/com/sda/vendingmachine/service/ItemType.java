package com.sda.vendingmachine.service;

public enum ItemType {

    CHOCOLATE_BAR(0.35),
    GRANOLA_BAR(0.25),
    LEMON_SODA(0.5),
    KOOKY_COLA(0.75),
    SALTY_CHIPS( 1),
    CHEESY_CHIPS( 1.25),
    CHIKEN_SANDIWCH(2.5),
    BLT_SANDWICH(1.75);

        private double price;

    ItemType(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "ItemType{" +
                "price=" + price +
                '}';
    }



}
