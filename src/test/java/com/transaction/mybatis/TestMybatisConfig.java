package com.transaction.mybatis;

import com.wangyang.dao.IUserDao;
import com.wangyang.service.IUserService;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

@Configuration
@ComponentScan({"com.wangyang.dao.impl.mybatis","com.wangyang.service"})
@MapperScan("com.wangyang.dao")
public class TestMybatisConfig {
    /**
     * H2 databases的数据源
     * 使用H2 Databases应注意的问题:
     * 每一个本地文件只能使用一个程序连接
     * @return
     */
    @Bean
    public DataSource dataSource(){
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("org.h2.Driver");
        ds.setUrl("jdbc:h2:~/wangyang1749");
        ds.setUsername("sa");
        System.out.println("-------配置spring的数据源----------");
        return ds;
    }

    /**
     * Spring 管理Mybatis的sessionFactory
     * @return
     * @throws Exception
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource());
//        org.apache.ibatis.session.Configuration cfg = new org.apache.ibatis.session.Configuration();
//        cfg.addMapper(IUserDao.class);
//        factoryBean.setConfiguration(cfg);
        return factoryBean.getObject();
    }

//    @Bean
    public IUserDao userMapper(SqlSessionFactory sqlSessionFactory) throws Exception {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
        return sqlSessionTemplate.getMapper(IUserDao.class);
    }

//    @Bean
//    public MapperFactoryBean userMapper(SqlSessionFactory sqlSessionFactory) throws Exception {
//        MapperFactoryBean factoryBean = new MapperFactoryBean();
//        factoryBean.setMapperInterface(IUserDao.class);
//        factoryBean.setSqlSessionFactory(sqlSessionFactory);
//        return factoryBean;
//    }


    /**
     * 配置mybatis的事务管理器
     * @param dataSource
     * @return
//     */
    @Bean
    public PlatformTransactionManager dataSourceTransactionManager(DataSource dataSource){
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }

    /**
     * 配置编程式事务
     * @param platformTransactionManager
     * @return
     */
//    @Bean
//    public TransactionTemplate transactionTemplate(PlatformTransactionManager platformTransactionManager){
//        TransactionTemplate transactionTemplate = new TransactionTemplate();
//        transactionTemplate.setTransactionManager(platformTransactionManager);
//        return transactionTemplate;
//    }

}
