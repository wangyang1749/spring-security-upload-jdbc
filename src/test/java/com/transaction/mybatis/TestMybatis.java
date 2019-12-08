package com.transaction.mybatis;


import com.wangyang.pojo.User;
import com.wangyang.service.IUserService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 配置文件:
    * @see com.transaction.mybatis.TestMybatisConfig
 * 手动注册映射器见:
    *  @see com.transaction.mybatis.TestMybatisConfig#userMapper(SqlSessionFactory)
 * 自动发现映射器:
     * 这里使用@MapperScan扫描{@link com.wangyang.dao.IUserDao}这个接口,
     * 由spring帮助我们自动配置mybatis的映射.
     * 我们只提供一个接口或者(接口+mapper文件), 接口的具体实现由spring容器完成
 * 传统的mybatis使用见:
    * @see com.wangyang.dao.impl.mybatis.UserDaoImpl
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestMybatisConfig.class )
public class TestMybatis {

    @Autowired
    IUserService userService;

    @Test
    public void testAdd(){
        userService.add(new User());
    }

    @Test
    public void find(){
        System.out.println(userService.findUserById(1).getUsername());
    }
}
