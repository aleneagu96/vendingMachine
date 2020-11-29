package com.sda.vendingmachine.service;
import com.sda.vendingmachine.persistence.entities.Item;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class ItemTypeTest {

    @Test
    public void testingItemType() {

        assertTrue(ItemType.GRANOLA_BAR.getPrice() == Coin.QUARTER.getValue());

        assert ItemType.CHIKEN_SANDIWCH.getPrice() == (Coin.QUARTER.getValue() * 9) + Coin.DIME.getValue() +
                Coin.DIME.getValue() + Coin.NICKEL.getValue();

        assert Coin.DIME.getValue() * 5 == ItemType.LEMON_SODA.getPrice();

        assertNotSame(ItemType.CHOCOLATE_BAR, ItemType.KOOKY_COLA);

        assertNotNull(ItemType.BLT_SANDWICH);


    }
}
