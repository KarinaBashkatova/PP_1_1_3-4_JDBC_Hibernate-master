package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        UserDao userDao = new UserDaoHibernateImpl();

        userDao.createUsersTable();


        userDao.saveUser("Тамара", "Володько", (byte) 13);
        userDao.saveUser("Валерия", "Иванова", (byte) 25);
        userDao.saveUser("Владимир", "Петров", (byte) 31);
        userDao.saveUser("Alex", "Pitt", (byte) 38);
        userDao.saveUser("Alexander", "Pitt", (byte) 38);


        userDao.getAllUsers();

        userDao.cleanUsersTable();

        userDao.dropUsersTable();


    }



}

