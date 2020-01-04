package com.wangyang.dao;

import com.wangyang.pojo.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IResourceDao extends JpaRepository<Resource,Integer> {
}
