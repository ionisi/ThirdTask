package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
//    private Connection connection;


    public UserDaoJDBCImpl() {
//        Util.createDatabase();  // Создаём базу данных, если её нет
//        this.connection = Util.getConnection();
//        try {
//            this.connection.setCatalog("users"); // Переключаемся на базу данных
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

//    private void init() {
//        Util.createDatabase();  // Создаём базу данных, если её нет
//        this.connection = Util.getConnection();
//        try {
//            this.connection.setCatalog("users"); // Переключаемся на базу данных
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }


    @Override
    public void createUsersTable() {
//        init();
        String sql = "CREATE TABLE IF NOT EXISTS users("
                + "id BIGINT PRIMARY KEY AUTO_INCREMENT, "
                + "name VARCHAR(50) NOT NULL, "
                + "lastName VARCHAR(50) NOT NULL, "
                + "age TINYINT NOT NULL)";
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Ошибка содания таблицы");
        }
    }

    @Override
    public void dropUsersTable() {
//        init();
        String sql = "DROP TABLE IF EXISTS users";
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
//        init();
        String sql = "INSERT INTO users(name, lastName, age) VALUES (?, ?, ?)";
        try (Connection connection = Util.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
//        init();
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection connection = Util.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
//        init();
        String sql = "SELECT * FROM users";
        List<User> users = new ArrayList<>();
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
//        init();
        String sql = "DELETE FROM users";
        try (Connection connection = Util.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
