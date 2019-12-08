package com.wangyang.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = HibernateJavaConfig.class)
@ContextConfiguration("/beans.xml")
@Transactional
public class HibernateDemo {
    @Autowired
    SessionFactory sessionFactory;

    @Test
    public void useCurrentSession(){
        try {
            Session session = sessionFactory.getCurrentSession();

        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void useOPenSession(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.getTransaction().commit();
    }
}
