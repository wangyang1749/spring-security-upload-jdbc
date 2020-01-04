package com.saylovewall;

import com.wangyang.config.RootConfig;
import com.wangyang.pojo.Resource;
import com.wangyang.service.IResourceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
public class TestResource {

    @Autowired
    IResourceService resourceService;

    @Test
    public void testAdd(){
        Resource resource = new Resource();
        resource.setPath("01.mp3");
        resource.setuId(123);
        resourceService.add(resource);
    }

    @Test
    public void delete(){
        resourceService.delete(1);
    }
    @Test
    public void page(){
        Page<Resource> page = resourceService.page(0, 20);
        System.out.println(page.getContent().get(0).getPath());
        ;

    }
}
