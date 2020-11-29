package com.sda.vendingmachine.service;

import com.sda.vendingmachine.persistence.entities.Item;
import com.sda.vendingmachine.persistence.repositories.ItemRepository;
import com.sda.vendingmachine.service.exceptions.NotFullPaidException;
import com.sda.vendingmachine.service.exceptions.NotSufficientChangeException;
import com.sda.vendingmachine.service.exceptions.SoldOutException;

import java.util.List;

public class VendingMachineImpl implements IVendingMachine {

    private IVendingStorage vendingStorage = new VendingStorageImpl();


    @Override
    public List<Item> getItems() {
        return vendingStorage.getItems();
    }

    @Override
    public void insertMoney(Coin coin, int quantity) {
        vendingStorage.addMoney(coin.getValue() * quantity);
    }

    @Override
    public boolean selectProduct(ItemType itemType) throws SoldOutException, NotFullPaidException,
            NotSufficientChangeException {
        ItemRepository itemRepository = new ItemRepository();
        Item itemPick = itemRepository.findByName(itemType.name());

        if (itemPick.getQuantity() == 0) {
            throw new SoldOutException();
        }
        vendingStorage.checkInsertedAmount(itemType);
        vendingStorage.checkForChange(itemType);

        return true;
    }

    @Override
    public void purchaseItem(ItemType itemType) {
        vendingStorage.releaseItem(itemType);
    }
}
