package com.wangyang.service.impl;

import com.wangyang.dao.IUserDao;
import com.wangyang.pojo.User;
import com.wangyang.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements IUserService {
//    @Resource(name = "hibernate_user_dao")
    @Resource
    IUserDao userDao;
    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Override
    public User findUserById(int id) {
        return userDao.findUserById(id);
    }
}
