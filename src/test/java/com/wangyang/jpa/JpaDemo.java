package com.wangyang.jpa;
import com.wangyang.pojo.User;
import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.Properties;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = JpaJavaConfig.class)
public class JpaDemo {
//
//    @PersistenceUnit
//    EntityManagerFactory entityManagerFactory;

    @Test
    @Transactional
    public void Test1(){
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
////        entityManager.getTransaction().begin();
//        H_User user=new H_User();
//        user.setId(3);
//        user.setUsername("zhangsan");
//        entityManager.persist(user);

//        entityManager.getTransaction().commit();
//        getSession().persist(user);
    }


    @Test
    public void Test01(){
        /**
         * 定义数据源
         */
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl("jdbc:mysql://47.93.201.74:3306/my_test?useUnicode=true&characterEncoding=UTF-8");
        ds.setUsername("root");
        ds.setPassword("123456");
        /**
         * 使用Spring管理SessionFactory
         * LocalSessionFactoryBean实现FactoryBean<SessionFactory>
         * 因此这里创建LocalSessionFactoryBean将创建的是getObject的返回结果SessionFactory
         */
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(ds);
        sessionFactoryBean.setPackagesToScan("com.wangyang.pojo");
        Properties properties = new Properties();
        properties.setProperty("dialect","org.hibernate.dialect.H2Dialect");
        sessionFactoryBean.setHibernateProperties(properties);
        SessionFactory sessionFactory = sessionFactoryBean.getObject();
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        User user = new User();
        user.setUsername("zhangzan");
        user.setPassword("123456");
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }
}
