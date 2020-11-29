package com.sda.vendingmachine.service;

import com.sda.vendingmachine.persistence.entities.Item;
import com.sda.vendingmachine.service.exceptions.NotFullPaidException;
import com.sda.vendingmachine.service.exceptions.NotSufficientChangeException;

import java.util.List;

public interface IVendingStorage {



    public boolean checkInsertedAmount(ItemType itemType) throws NotFullPaidException;

    public boolean checkForChange(ItemType itemType) throws NotSufficientChangeException;

    public void releaseItem(ItemType itemType);

    public void addMoney(double value);

    public List<Item> getItems();

}
