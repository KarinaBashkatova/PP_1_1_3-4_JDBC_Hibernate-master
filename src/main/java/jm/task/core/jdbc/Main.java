package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        Util util = new Util();
        util.getConnection();
        UserDao userDao = new UserDaoJDBCImpl();

        userDao.createUsersTable();


        userDao.saveUser("Тамара", "Володько", (byte) 13);
        userDao.saveUser("Валерия", "Иванова", (byte) 25);
        userDao.saveUser("Владимир", "Петров", (byte) 31);
        userDao.saveUser("Alex", "Pitt", (byte) 38);


        userDao.getAllUsers();

        userDao.cleanUsersTable();
        userDao.dropUsersTable();


    }



}

