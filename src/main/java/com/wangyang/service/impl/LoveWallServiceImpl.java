package com.wangyang.service.impl;

import com.wangyang.dao.ILoveWallDao;
import com.wangyang.dao.IUserDaoSD;
import com.wangyang.pojo.LoveWall;
import com.wangyang.service.ILoveWallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class LoveWallServiceImpl implements ILoveWallService {

    @Autowired
    IUserDaoSD userDaoSD;
    @Autowired
    ILoveWallDao loveWallDao;

    @Override
    public void add(LoveWall loveWall) {
        loveWallDao.save(loveWall);
    }

    @Override
    public LoveWall findById(int id) {
        Optional<LoveWall> byId = loveWallDao.findById(id);
        return byId.get();
    }

    @Override
    public void likeOrDislike(int lId, int uId) {

        userDaoSD.existsById(uId);
    }

    @Override
    public Page<LoveWall> findPage(int page,int size) {
        Page<LoveWall> loveWalls = loveWallDao.findAll(PageRequest.of(page,size,Sort.by(Sort.Order.desc("id"))));
        return loveWalls;
    }
}
