package com.wangyang.dao.impl.mybatis;

import com.wangyang.dao.IUserDao;
import com.wangyang.pojo.User;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

//@Repository
public class UserDaoImpl  extends SqlSessionDaoSupport implements IUserDao {

    @Autowired
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    /**
     * 采用注解的sql{@link com.wangyang.dao.IUserDao}
        * getSqlSession().getMapper(IUserDao.class).add(user);
     * mapper文件的如下:
        * sqlSession.insert("org.mybatis.spring.sample.mapper.UserMapper.insertUser", user);
     * 使用时注意事项:
        *出现Type interface com.wangyang.dao.IUserDao is not known to the MapperRegistry.
        * 加入如下:
        * org.apache.ibatis.session.Configuration cfg = new org.apache.ibatis.session.Configuration();
        * cfg.addMapper(IUserDao.class);
        * factoryBean.setConfiguration(cfg);
     * @param user
     */
    @Override
    public void add(User user){
        getSqlSession().getMapper(IUserDao.class).add(user);
    }

    @Override
    public User findUserById(int id) {
        return null;
    }
}
