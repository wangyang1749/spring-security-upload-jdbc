package com.wangyang.dao.impl.jpa;

import com.wangyang.dao.IUserDao;
import com.wangyang.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManagerFactory;

public class UserDaoImpl implements IUserDao {
    @Autowired
    private EntityManagerFactory entityManagerFactory;
    @Override
    public void add(User user) {

    }

    @Override
    public User findUserById(int id) {
        return null;
    }


    public void addUser(User user){
//        entityManagerFactory.createEntityManager().getTransaction().begin();
        entityManagerFactory.createEntityManager().merge(user);
//        entityManagerFactory.createEntityManager().getTransaction().commit();

    }
    public User getUserById(int id){
        return  entityManagerFactory.createEntityManager().find(User.class,id);
    }
}
