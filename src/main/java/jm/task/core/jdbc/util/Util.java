package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    // реализуйте настройку соеденения с БД
    private final static String url = "jdbc:mysql://localhost:3306/mysql";
    private final static String user = "root";
    private final static String password = "ingadfg842655@";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        return conn;
    }

//    public static void createDatabase() {
//        String sql = "CREATE DATABASE IF NOT EXISTS users";
//        try (Connection conn = DriverManager.getConnection(url, user, password);
//             Statement statement = conn.createStatement()) {
//            statement.execute(sql);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

}
