package com.wangyang.service;

import com.wangyang.pojo.User;

import javax.transaction.Transactional;

@Transactional
public interface IUserService {
    void add(User user);
    User findUserById(int id);
}
