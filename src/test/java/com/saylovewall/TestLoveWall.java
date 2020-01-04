package com.saylovewall;

import com.wangyang.config.RootConfig;
import com.wangyang.pojo.LoveWall;
import com.wangyang.service.ILoveWallService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
public class TestLoveWall {

    @Autowired
    ILoveWallService loveWallService;

    @Test
    public void addLoveWall(){
        LoveWall loveWall = new LoveWall();
        loveWall.setContent("love ch");
        loveWallService.add(loveWall);
    }
    @Test
    public void findById(){
        Assert.assertNotNull(loveWallService.findById(1));
    }

    @Test
    public void page(){
        Page<LoveWall> page = loveWallService.findPage(0, 10);
        System.out.println(page.getContent());
    }
}
