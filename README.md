# Introduction
本项目的目的是用来学习`Spring Security` `Spring data` `jpa` `Hibernate` `Mybatis` 
`jwt` 

## web层介绍
### Spring Security
> 
### 实现SpringSecurity+Jwt+token验证

## 持久层的介绍
> 在包 `com.wangyang.dao` 是接口定义, `com.wangyang.dao.impl` 是各种框架对接口的实现 .  
> 在全局的配置文件中, 确保 `com.wangyang.service` 只扫描到一种dao的实现,  
> 否则会出现多个bean重复定义. 
> 每一种持久化框架的实现代码,可以通过 `com.transaction` 中的测试代码进行测试,  
> 测试代码提供了配套的javaConfig配置文件
### Hibernate 

## 配置介绍
+ H2 databases的访问地址<http://localhost:8080/console>
+ 

# 备份
    @Bean
    public DataSource dataSource_(){
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:schema.sql")
                .build();
    }
    
    @Bean
    public  DataSource dataSource(){
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("org.sqlite.JDBC");
        ds.setUrl("jdbc:sqlite:dbs/xwfa.db");
        System.out.println("-------配置spring的数据源----------");
        return ds;
    }
    