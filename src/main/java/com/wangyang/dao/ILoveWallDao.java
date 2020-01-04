package com.wangyang.dao;


import com.wangyang.pojo.LoveWall;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILoveWallDao extends JpaRepository<LoveWall,Integer> {

//    LoveWall findById(int id);
//    Page<LoveWall> findAll(Pageable pageable);
}
