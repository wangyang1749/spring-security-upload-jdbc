package com.wangyang.databases;

import java.io.File;
import java.sql.*;

public class SqliteTest {
    public static void main(String[] args) {
        try {
            File dbFolder = new File("./dbs");
            if (!dbFolder.exists()) {
                System.out.println("创建dbs文件夹：" + dbFolder.mkdir());
            }
//			Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:dbs/xwf.db");//需要先创建文件夹dbs，数据库文件才会自动创建
            Statement state = conn.createStatement();

            //建表
            state.executeUpdate("drop table if exists books");
            state.executeUpdate("create table books(id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR(255),author VARCHAR(100))");

            //插入数据
            state.executeUpdate("insert into books (name,author) values ('hello','world'),('abc','def'),('jdbc','sqlite')");
            state.close();

            //查询
            PreparedStatement pstate = conn.prepareStatement("select * from books");
            ResultSet resultSet = pstate.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String author = resultSet.getString("author");
                System.out.println("id=" + id + " name=" + name + " author=" + author);
            }
            pstate.close();

            //修改
            PreparedStatement pstate2 = conn.prepareStatement("update books set author = ? where id = ?");
            pstate2.setString(1, "new author");
            pstate2.setInt(2, 3);
            int updateResult = pstate2.executeUpdate();
            System.out.println("updateResult = " + updateResult);
            pstate2 = conn.prepareStatement("select * from books where id = 3");
            ResultSet check = pstate2.executeQuery();
            while (check.next()) {
                System.out.println("----id:" + check.getInt(1) + " name:" + check.getString(2) + " author:" + check.getString(3));
            }
            pstate2.close();

            //删除
            PreparedStatement pstate3 = conn.prepareStatement("delete from books where id = ?");
            pstate3.setInt(1, 3);
            System.out.println("delete result : " + pstate3.executeUpdate());
            pstate3.close();

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
