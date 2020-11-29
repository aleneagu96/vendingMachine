package com.sda.vendingmachine.service;

import com.sda.vendingmachine.persistence.entities.Item;
import com.sda.vendingmachine.persistence.entities.Money;
import com.sda.vendingmachine.persistence.repositories.ItemRepository;
import com.sda.vendingmachine.persistence.repositories.MoneyRepository;
import com.sda.vendingmachine.service.exceptions.NotFullPaidException;
import com.sda.vendingmachine.service.exceptions.NotSufficientChangeException;
import org.hibernate.query.criteria.internal.expression.function.AggregationFunction;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class VendingStorageImpl implements IVendingStorage {

    private double insertedAmount;
    List<Item> itemList;
    Item item;


    @Override
    public boolean checkInsertedAmount(ItemType itemType) throws NotFullPaidException {

        //compare inserted amount to product value
        // todo: check if item list contains the item type otherwise bring from db
        ItemRepository itemRepository = new ItemRepository();
        item = itemRepository.findByName(itemType.name());

        if (insertedAmount < item.getPrice()) {
            throw new NotFullPaidException();
        }

        return true;
    }



    @Override
    public boolean checkForChange(ItemType itemType) throws NotSufficientChangeException {
        //todo: diferenta intre (suma inserata - valoare item) si valoare din db
        MoneyRepository moneyRepository = new MoneyRepository();
        List<Money> moneys = moneyRepository.findAll();
        double moneyInTheBank = 0;
        Item item = null;

        for (Money money : moneys) {
            moneyInTheBank += money.getQuantity() * money.getCoin().getValue();
        }

        for (Item i : itemList) {
            if (itemType.name().equals(i.getName())) {
                item = i;
            }
        }
        // todo: if item is null here, bring it from db

        if (insertedAmount - item.getPrice() > moneyInTheBank ) {
            throw new NotSufficientChangeException();
        }

        return true;
    }



    @Override
    public void releaseItem(ItemType itemType) {
        ItemRepository itemRepository = new ItemRepository();
        Item item = itemRepository.findByName(itemType.name());
        item.setQuantity(item.getQuantity() - 1);
        itemRepository.update(item);
    }

    @Override
    public void addMoney(double value) {
        insertedAmount += value;
    }

    @Override
    public List<Item> getItems() {

        ItemRepository itemRepository = new ItemRepository();
        itemList = itemRepository.findAll();

        return itemList;
    }

}
