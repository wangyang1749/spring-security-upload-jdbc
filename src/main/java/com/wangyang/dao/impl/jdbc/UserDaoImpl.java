package com.wangyang.dao.impl.jdbc;

import com.wangyang.dao.IUserDao;
import com.wangyang.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDaoImpl implements IUserDao {
    @Autowired
    DataSource dataSource;
    @Override
    public void add(User user) {
        try {
            Connection conn = dataSource.getConnection();
            Statement statement = conn.createStatement();
            statement.execute("");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public User findUserById(int id) {
        return null;
    }
    public void test(DataSource dataSource){
        try {
            Connection connection = dataSource.getConnection();
            Statement state = connection.createStatement();
            state.executeUpdate("drop table if exists books");
//            state.executeUpdate("create table books(name VARCHAR(255),author VARCHAR(100))");

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
//            conn.close();
            state.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
