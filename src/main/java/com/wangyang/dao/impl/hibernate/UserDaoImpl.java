package com.wangyang.dao.impl.hibernate;

import com.wangyang.dao.IUserDao;
import com.wangyang.pojo.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("hibernate_user_dao")
public class UserDaoImpl implements IUserDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void add(User user) {
        getSession().save(user);
    }

    @Override
    public User findUserById(int id) {
//        return getSession().f;
        return null;
    }
}
