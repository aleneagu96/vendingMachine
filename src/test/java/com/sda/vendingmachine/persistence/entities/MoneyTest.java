package com.sda.vendingmachine.persistence.entities;

import com.sda.vendingmachine.persistence.entities.Money;

import com.sda.vendingmachine.service.Coin;
import org.junit.Test;

public class MoneyTest {

    @Test
    public void testGetterAndSetterForCoin() {

//        ----given---
        Money money = new Money();

//        ----when----
        money.setCoin(Coin.DIME);

//        ----then----
        assert money.getCoin().equals(Coin.DIME);
    }

    @Test
    public void testGetterAndSetterForCoinQuantity() {

//        ----given----
        Money money = new Money();
        int ammount = 21;

//        -----when----
        money.setQuantity(21);

//        ----then----
        assert money.getQuantity() == ammount;
    }
}
