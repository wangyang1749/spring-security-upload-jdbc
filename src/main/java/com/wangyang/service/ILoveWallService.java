package com.wangyang.service;

import com.wangyang.pojo.LoveWall;
import org.springframework.data.domain.Page;

public interface ILoveWallService {
    void add(LoveWall loveWall);
    LoveWall findById(int id);
    void likeOrDislike(int lId,int uId);
    Page<LoveWall> findPage(int page,int size);


}
