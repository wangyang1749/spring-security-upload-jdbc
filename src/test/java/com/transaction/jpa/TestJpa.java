package com.transaction.jpa;

import com.transaction.jdbc.TestJdbcConfig;
import com.wangyang.pojo.User;
import com.wangyang.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestJpaConfig.class )
public class TestJpa {

    @Autowired
    IUserService userService;

    @Autowired
    TransactionTemplate transactionTemplate;

    @Test
    public void add(){
        User user  = new User();
        user.setUsername("wangyang");
        user.setPassword("1749");
        userService.add(user);
    }
    @Test
    public void testAddByTransactionTemplate(){
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                User user = new User();
                user.setUsername("wangyang");
                user.setPassword("123456");
                userService.add(user);
            }
        });
    }
}
