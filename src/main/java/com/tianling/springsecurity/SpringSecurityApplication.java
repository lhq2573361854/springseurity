package com.tianling.springsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author TianLing
 */
@EnableSwagger2
@SpringBootApplication
public class SpringSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class, args);

    }

    public void test() {
        Connection connection =null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://47.107.249.42:3306/demo?serverTimezone=GMT%2B8&useSSL=false");
            System.out.println("connection = " + connection);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            System.out.println("connection = " + connection);
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

}
