package com.sda.vendingmachine.persistence.repositories;

import com.sda.vendingmachine.persistence.commons.HibernateUtils;
import com.sda.vendingmachine.persistence.entities.Item;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ItemRepository {

    public Item findById(Integer item_id) {
        Session session = HibernateUtils.getSessionFactory().openSession();

        Item item = session.find(Item.class, item_id);

        session.close();
        return item;
    }

    public List<Item> findAll() {
        Session session = HibernateUtils.getSessionFactory().openSession();

        Query selectAllItemsQuery = session.createQuery("from Item");
        List<Item> items = selectAllItemsQuery.list();

        session.close();
        return items;
    }

    public Item findByName (String itemName) {
        Session session = HibernateUtils.getSessionFactory().openSession();

        Query selectItemByNameQuery = session.createQuery("from Item i WHERE i.name = '" + itemName + "'");
        List<Item> names = selectItemByNameQuery.list();

        session.close();
        return names.stream().findFirst().get(); // transforma names din lista in stream si returneaza primul element din stream
    }

    public void save(Item item) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.save(item);

        transaction.commit();
        session.close();
    }

    public void update(Item item) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.update(item);

        transaction.commit();
        session.close();
    }

    public void delete(Item item) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.delete(item);

        transaction.commit();
        session.close();
    }
}
