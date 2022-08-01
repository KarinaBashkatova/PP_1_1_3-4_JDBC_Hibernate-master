package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {
    private Connection connection = null;
    private PreparedStatement Statement = null;

    public UserDaoJDBCImpl() {

    }

    @Override
    public void createUsersTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS Users (id SERIAL, name varchar(255) NOT NULL, lastName varchar(255) NOT NULL, age tinyint NOT NULL)";
        try {connection = Util.getConnection();
            Statement  = connection.prepareStatement(sql);
            Statement.executeUpdate(sql);
            System.out.println("Database has been created!");
        } catch (Exception e) {
            System.out.println("Connection failed");
        } finally {
            if (connection !=null) {
                connection.close();
            }
        }
}

    @Override
    public void dropUsersTable() throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "DROP TABLE IF EXISTS Users";
        try {connection = Util.getConnection();
            Statement  = connection.prepareStatement(sql);
            Statement.executeUpdate(sql);
            System.out.println("Database has been dropped!");
        } catch (Exception e) {
            System.out.println("Connection failed");
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }


    @Override
    public void saveUser(String name, String lastName, byte age) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO Users (NAME, LASTNAME, AGE) VALUES (?, ?, ?)";

        try {
            connection = Util.getConnection();
            Statement  = connection.prepareStatement(sql);
            Statement.setString(1, name);
            Statement.setString(2, lastName);
            Statement.setByte(3, age);


            if (Statement.executeUpdate() > 0) {
                System.out.println("User с именем " + name + " добавлен в базу данных");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (Statement !=null) {
                Statement.close();
            }
            if (connection !=null) {
                connection.close();
            }
        }
    }

    @Override
    public void removeUserById(long id) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM Users WHERE ID=?";

        try {connection = Util.getConnection();
            Statement  = connection.prepareStatement(sql);
            Statement.setLong(1, id);
            Statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (Statement !=null) {
                Statement.close();
            }
            if (connection !=null) {
                connection.close();
            }
        }

    }
    @Override
    public List<User> getAllUsers() throws SQLException {
        List<User> userList = new ArrayList<>();

        String sql = "SELECT ID, NAME, LASTNAME, AGE FROM Users";
        Statement statement = null;
        try {
            connection = Util.getConnection();
            Statement = connection.prepareStatement(sql);
            ResultSet resultSet = Statement.executeQuery(sql);

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
        } finally {
            if (Statement != null) {
                Statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return userList;

    }



    @Override
    public void cleanUsersTable() throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM Users";

        try {connection = Util.getConnection();
            Statement  = connection.prepareStatement(sql);
            Statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (Statement !=null) {
                Statement.close();
            }
            if (connection !=null) {
                connection.close();
            }
        }

    }
}
