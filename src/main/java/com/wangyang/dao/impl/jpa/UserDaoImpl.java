package com.wangyang.dao.impl.jpa;

import com.wangyang.dao.IUserDao;
import com.wangyang.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

@Repository("jpa_user_dao")
public class UserDaoImpl implements IUserDao {
    /**
     * EntityManger 不是线程安全的,不适合输入到像Repository这样共享的单例bean中
     *
     */
//    @PersistenceUnit
//    private EntityManagerFactory entityManagerFactory;
//    public EntityManager getEntityManager(){
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        return entityManager;
//    }
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public User findUserById(int id) {
        return null;
    }


    public void addUser(User user){
//        entityManagerFactory.createEntityManager().getTransaction().begin();
//        entityManagerFactory.createEntityManager().merge(user);
//        entityManagerFactory.createEntityManager().getTransaction().commit();

    }
    public User getUserById(int id){
//        return  entityManagerFactory.createEntityManager().find(User.class,id);
        return null;
    }
}
