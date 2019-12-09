package com.transaction.jpa;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configurable
@ComponentScan({"com.wangyang.dao.impl.jpa","com.wangyang.service"})
@EnableTransactionManagement
public class TestJpaConfig {
    /**
     * mysql 的数据源配置
     * @return
     */
//    @Bean
//    public DataSource dataSource(){
//        BasicDataSource ds = new BasicDataSource();
//        ds.setUrl("jdbc:mysql://47.93.201.74:3306/my_test?useUnicode=true&characterEncoding=UTF-8");
//        ds.setUsername("root");
//        ds.setPassword("123456");
//
//        System.out.println("-------配置spring的数据源----mysql------");
//        return ds;
//    }

    /**
     * 配置H2 databases的数据源
     * @return
     */
    @Bean
    public DataSource dataSource(){
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("org.h2.Driver");
        ds.setUrl("jdbc:h2:~/spring_test");
        ds.setUsername("sa");
        System.out.println("-------配置spring的数据源----- H2 Databases-----");
        return ds;
    }

    /**
     * Spring JdbcTemplate
     * @param dataSource
     * @return
     */
    @Bean
    public JdbcTemplate getJdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    /**
     * 以hibernate作为jpa实现的配置
     * @param dataSource
     * @return
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        vendorAdapter.setGenerateDdl(true);
//        vendorAdapter.setShowSql(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);

        Properties properties = new Properties();
        properties.setProperty("hibernate.show_sql","true");
        properties.setProperty("hibernate.dialect","org.hibernate.dialect.H2Dialect");
        properties.setProperty("hibernate.hbm2ddl.auto","update");
        factory.setJpaProperties(properties);
        //扫描实体类的包
        factory.setPackagesToScan("com.wangyang.pojo");
        factory.setDataSource(dataSource);
        return factory;
    }

    /**
     * 配置Jap的事务管理器
     * @param entityManagerFactory
     * @return
     */
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);
        return txManager;
    }

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
