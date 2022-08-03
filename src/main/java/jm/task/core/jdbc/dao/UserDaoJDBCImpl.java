package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    @Override
    public void createUsersTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS Users (id SERIAL, name varchar(255) NOT NULL, lastName varchar(255) NOT NULL, age tinyint NOT NULL)";

        try (Connection connection = Util.getConnection();
            PreparedStatement PreparedStatement = connection.prepareStatement(sql);) {
            PreparedStatement.executeUpdate(sql);
            System.out.println("Database has been created!");
        } catch (Exception e) {
            System.out.println("Connection failed");
        }
    }

    @Override
    public void dropUsersTable() throws SQLException {
        String sql = "DROP TABLE IF EXISTS Users";

        try (Connection connection = Util.getConnection();
            PreparedStatement PreparedStatement = connection.prepareStatement(sql);) {
            PreparedStatement.executeUpdate(sql);
            System.out.println("Database has been dropped!");
        } catch (Exception e) {
            System.out.println("Connection failed");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) throws SQLException {
        String sql = "INSERT INTO Users (NAME, LASTNAME, AGE) VALUES (?, ?, ?)";

        try (Connection connection = Util.getConnection();
            PreparedStatement PreparedStatement = connection.prepareStatement(sql);) {
            PreparedStatement.setString(1, name);
            PreparedStatement.setString(2, lastName);
            PreparedStatement.setByte(3, age);

            if (PreparedStatement.executeUpdate() > 0) {
                System.out.println("User с именем " + name + " добавлен в базу данных");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeUserById(long id) throws SQLException {
        String sql = "DELETE FROM Users WHERE ID=?";

        try (Connection connection = Util.getConnection();
            PreparedStatement PreparedStatement = connection.prepareStatement(sql);) {
            PreparedStatement.setLong(1, id);
            PreparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<User> getAllUsers() throws SQLException {
        List<User> userList = new ArrayList<>();

        String sql = "SELECT ID, NAME, LASTNAME, AGE FROM Users";

        try (Connection connection = Util.getConnection();
             PreparedStatement PreparedStatement = connection.prepareStatement(sql);) {
            ResultSet resultSet = PreparedStatement.executeQuery(sql);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("ID"));
                user.setName(resultSet.getString("NAME"));
                user.setLastName(resultSet.getString("LASTNAME"));
                user.setAge(resultSet.getByte("AGE"));

                userList.add(user);
                System.out.println(user.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }



    @Override
    public void cleanUsersTable() throws SQLException {
        String sql = "DELETE FROM Users";

        try (Connection connection = Util.getConnection();
             PreparedStatement PreparedStatement = connection.prepareStatement(sql);) {
            PreparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
