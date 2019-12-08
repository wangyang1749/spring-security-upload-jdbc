package com.transaction.hibernate;

import com.wangyang.pojo.User;
import com.wangyang.service.IUserService;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;


/**
 * 本类是用来测试Spring的声明式事务和编程式事务
 * Spring容器的配置文件为
 * @see TestUSerDaoConfig
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes =TestUSerDaoConfig.class )
public class TestUserDao {

    @Autowired
    IUserService userService;

    @Autowired
    TransactionTemplate transactionTemplate;

    /**
     * 本方法没有使用编程式事务
     * 如何在service层没有使用声明式事务, 将会出现
     * Could not obtain transaction-synchronized Session for current thread
     * 在{@link IUserService}中添加 @Transactional即可开启声明式事务
     * 同时应该注意到配置类中
     * @see TestUSerDaoConfig#hibernateTransactionManager(SessionFactory)
     * @see TestUSerDaoConfig 头部的@EnableTransactionManagement开启声明式事务
     */
    @Test
    public void testAdd(){
        User user = new User();
        user.setUsername("wangyang");
        user.setPassword("123456");
//        user.setId(78);
        userService.add(user);
    }

    /**
     * 本方法采用编程式事务
     * 经实验,即使在service添加了声明式事务, 本方法仍然可以正常使用
     */
    @Test
    public void testAddByTransactionTemplate(){
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                User user = new User();
                user.setUsername("wangyang");
                user.setPassword("123456");
                user.setId(88);
                userService.add(user);
            }
        });
    }
}
