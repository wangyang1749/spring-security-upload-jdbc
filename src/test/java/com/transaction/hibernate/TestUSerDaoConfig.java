package com.transaction.hibernate;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan({"com.wangyang.dao.impl.hibernate","com.wangyang.service"})
@EnableTransactionManagement
public class TestUSerDaoConfig {

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
     * Mysql的数据源
     * @param dataSource
     * @return
     */
//    @Bean
//    public DataSource dataSource(){
//        BasicDataSource ds = new BasicDataSource();
//        ds.setUrl("jdbc:mysql://47.93.201.74:3306/my_test?useUnicode=true&characterEncoding=UTF-8");
//        ds.setUsername("root");
//        ds.setPassword("123456");
//        System.out.println("-------配置spring的数据源----------");
//        return ds;
//    }

    /**
     * spring管理Hibernate的SessionFactory
     * @param dataSource
     * @return
     */
    @Bean
    public LocalSessionFactoryBean sessionFactoryBean(DataSource dataSource){
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setPackagesToScan("com.wangyang.pojo");
        Properties properties = new Properties();
        properties.setProperty("hibernate.show_sql","true");
        properties.setProperty("hibernate.dialect","org.hibernate.dialect.H2Dialect");
        properties.setProperty("hibernate.hbm2ddl.auto","update");
        sessionFactoryBean.setHibernateProperties(properties);
        return sessionFactoryBean;
    }

    /**
     * 配置Hibernate的事务管理器
     * 注意:使用Hibernate必须配置HIbernate的事务管理器HibernateTransactionManager
     * 否则会出现如下错误:
     * no transaction is in progress
     * @param sessionFactory
     * @return
     */
    @Bean
    public PlatformTransactionManager hibernateTransactionManager(SessionFactory sessionFactory){
        HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
        hibernateTransactionManager.setSessionFactory(sessionFactory);
        return hibernateTransactionManager;
    }


//    @Bean
//    public PlatformTransactionManager dataSourceTransactionManager(DataSource dataSource){
//        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
//        dataSourceTransactionManager.setDataSource(dataSource);
//        return dataSourceTransactionManager;
//    }

    /**
     * 配置编程式事务
     * @param platformTransactionManager
     * @return
     */
    @Bean
    public TransactionTemplate transactionTemplate(PlatformTransactionManager platformTransactionManager){
        TransactionTemplate transactionTemplate = new TransactionTemplate();
        transactionTemplate.setTransactionManager(platformTransactionManager);
        return transactionTemplate;
    }

}
