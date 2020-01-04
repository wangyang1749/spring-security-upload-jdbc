package com.saylovewall;

import com.wangyang.config.RootConfig;
import com.wangyang.dao.ILoveWallDao;
import com.wangyang.pojo.LoveWall;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
public class TestLoveWallDao {

    @Autowired
    ILoveWallDao loveWallDao;

    @Test
    public void add(){
        LoveWall loveWall = new LoveWall();
        loveWall.setContent("love ");
        loveWallDao.save(loveWall);
    }
    @Test
    public void  list(){
        List<LoveWall> loveWallDaoAll = loveWallDao.findAll();
        System.out.println(loveWallDaoAll.get(1).getContent());
    }
    @Test
    public void  page(){
        

        Page<LoveWall> loveWallDaoAll = loveWallDao.findAll(PageRequest.of(0, 5));

        System.out.println(loveWallDaoAll.getContent().size());
    }
}
