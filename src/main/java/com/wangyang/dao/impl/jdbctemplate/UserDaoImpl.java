package com.wangyang.dao.impl.jdbctemplate;

import com.wangyang.dao.IUserDao;
import com.wangyang.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

@Repository("jdbc_template_dao")
public class UserDaoImpl implements IUserDao {
    @Autowired
    JdbcOperations jdbcOperations;
    @Override
    public void add(User user) {
        jdbcOperations.execute("insert into user (username,password) values ('zzzzzz','111111')");
    }

    @Override
    public User findUserById(int id) {
        return null;
    }
}
