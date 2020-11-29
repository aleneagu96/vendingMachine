package com.sda.vendingmachine.service;

import com.sda.vendingmachine.persistence.entities.Item;
import com.sda.vendingmachine.service.exceptions.NotFullPaidException;
import com.sda.vendingmachine.service.exceptions.NotSufficientChangeException;
import com.sda.vendingmachine.service.exceptions.SoldOutException;

import java.util.List;

public interface IVendingMachine {

    public List<Item> getItems();

    public void insertMoney(Coin money, int quantity);

    public boolean selectProduct(ItemType itemType) throws SoldOutException, NotFullPaidException, NotSufficientChangeException;

    public void purchaseItem(ItemType itemType);


    












}
