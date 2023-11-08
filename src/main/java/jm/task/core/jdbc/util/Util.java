package jm.task.core.jdbc.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД

    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "2377";

    private static Connection connection;






    static {
        try {

            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);


        } catch (SQLException e) {
            System.out.println("Подключении к базе данных что то пошло не так");
        }

    }

    public static Connection getConnection() {
        return connection;
    }



}
