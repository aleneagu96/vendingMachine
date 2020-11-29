package com.sda.vendingmachine.persistence.commons;

import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class HibernateUtilsTest {
    @Test
    public  void getSessionFactory() {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        assertNotNull(sessionFactory);
        assertEquals("jdbc:mysql://localhost:3306/vending_machine_container",sessionFactory.getProperties().get(Environment.URL));
       assertEquals("****",sessionFactory.getProperties().get(Environment.USER));
       assertEquals("****",sessionFactory.getProperties().get(Environment.PASS));
       assertEquals("org.hibernate.dialect.MySQL5Dialect",sessionFactory.getProperties().get(Environment.DIALECT));
       assertEquals("true",sessionFactory.getProperties().get(Environment.SHOW_SQL));

        Session session = sessionFactory.openSession();
        assertNotNull(session);



    }
}
