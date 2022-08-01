package jm.task.core.jdbc.util;


import java.security.PublicKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
        final static String URL = "jdbc:mysql://localhost:3306/mydbtest?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true" +  "&useLegacyDatetimeCode=false&serverTimezone=UTC";
        final static String USERNAME = "root";
        final static String PASSWORD = "Karina1995";


       public static Connection getConnection() {
           Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            if (!connection.isClosed()) {
                System.out.println("Соединение с БД установлено");
            }
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
       }


}
