package com.wangyang.databases;

import java.sql.*;

public class H2Test {
    //数据库连接URL，通过使用TCP/IP的服务器模式（远程连接）
    private static final String JDBC_URL = "jdbc:h2:~/test";
    //连接数据库时使用的用户名
    private static final String USER = "sa";
    //连接数据库时使用的密码
    private static final String PASSWORD = "";
    //连接H2数据库时使用的驱动类，org.h2.Driver这个类是由H2数据库自己提供的，在H2数据库的jar包中可以找到
    private static final String DRIVER_CLASS = "org.h2.Driver";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 加载H2数据库驱动
//        Class.forName(DRIVER_CLASS);
        // 根据连接URL，用户名，密码获取数据库连接
        Connection conn = DriverManager.getConnection("jdbc:h2:~/test.db");
        //如果存在USER_INFO表就先删除USER_INFO表
        Statement state = conn.createStatement();
        //建表
        state.executeUpdate("drop table if exists books");
        state.executeUpdate("create table books(name VARCHAR(255),author VARCHAR(100))");

        //插入数据
        state.executeUpdate("insert into books (name,author) values ('hello','world'),('abc','def'),('jdbc','sqlite')");
//        state.close();
//        //查询
        ResultSet rs = state.executeQuery("select * from books");
//        //遍历结果集
        while (rs.next()) {
//            int id = rs.getInt("id");
            String name = rs.getString("name");
            String author = rs.getString("author");
            System.out.println(" name=" + name + " author=" + author);
        }
        //释放资源
        state.close();
        //关闭连接
        conn.close();
    }
}
