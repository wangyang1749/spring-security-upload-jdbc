package com.transaction.jdbc;

import com.wangyang.pojo.User;
import com.wangyang.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestJdbcConfig.class )
public class TestJdbc {

    @Autowired
    IUserService userService;
    @Test
    public void testAdd(){
            userService.add(new User());
    }
}
