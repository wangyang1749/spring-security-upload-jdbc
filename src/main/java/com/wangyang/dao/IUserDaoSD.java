package com.wangyang.dao;

import com.wangyang.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserDaoSD extends JpaRepository<User,Integer> {

}
