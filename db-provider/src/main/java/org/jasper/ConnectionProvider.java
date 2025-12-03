package org.jasper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
    private static final String dbName = "jdbc:postgresql://localhost:5432/holidays_db";
    private static final String dbUser = "user";
    private static final String dbPass = "pass";
    public static Connection connect(){
        try{
            Connection connection = DriverManager.getConnection(dbName,dbUser,dbPass);
            System.out.println("Successful");
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
