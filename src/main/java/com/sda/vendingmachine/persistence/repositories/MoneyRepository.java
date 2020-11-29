package com.sda.vendingmachine.persistence.repositories;

import com.sda.vendingmachine.persistence.commons.HibernateUtils;
import com.sda.vendingmachine.persistence.entities.Money;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Set;

public class MoneyRepository {

    public Money findById(Integer coin_id) {
        Session session = HibernateUtils.getSessionFactory().openSession();

        Money type = session.find(Money.class, coin_id);

        session.close();

        return type;
    }

    public List<Money> findByType(Integer coin_type) {
        Session session = HibernateUtils.getSessionFactory().openSession();

        Query selectMoneyByType = session.createQuery("fMoney type = '" + coin_type + "'");

        List<Money> money = selectMoneyByType.list();

        session.close();

        return money;
    }

    public List<Money> findAll() {
        Session session = HibernateUtils.getSessionFactory().openSession();

        Query selectAllMoney = session.createQuery("from Money");

        List<Money> money = selectAllMoney.list();

        session.close();

        return money;
    }

    public void save(Money money) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.save(money);

        transaction.commit();
        session.close();
    }

    public void update(Money money) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.update(money);

        transaction.commit();
        session.close();
    }

    public void deleteById(Money money) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.delete(money);

        transaction.commit();
        session.close();
    }
}