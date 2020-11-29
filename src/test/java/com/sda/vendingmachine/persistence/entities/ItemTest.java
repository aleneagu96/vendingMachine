package com.sda.vendingmachine.persistence.entities;

import com.sda.vendingmachine.persistence.entities.Item;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class ItemTest {

    @Test
    public void testGetterAndSetterForItemPrice() {

//        ----given----
        double ammount = 0.75;

//        -----when----
        Item item = new Item();

//        ----then----
        item.setPrice(0.75);
        assert item.getPrice() == ammount;
    }

    @Test
    public void testGetterAndSetterForItemName() {

//            ----given----
        Item item = new Item();

////        ----when----
        item.setName("Kooky-Cola");

////        ----then----

        assert item.getName().equalsIgnoreCase("Kooky-cola");

    }

    @Test
    public void testGetterAndSetterForItemQuantity() {

//        ----given---
        int ammount = 3;
        Item item = new Item();

//        ----when----
        item.setQuantity(3);

//        -----then----
        assert item.getQuantity() == ammount;

    }


}
