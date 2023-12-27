package org.example.storeservice.DAO;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    private static Connection instance = null;

    public static Connection getInstance(){
        if (instance == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("Driver not found");
            }
            try {
                instance = DriverManager.getConnection("jdbc:mysql://localhost:3306/store_service_schema","root","root");
            } catch (SQLException e) {
                throw new RuntimeException("Connection failed");
            }
        }
        return instance;
    }

}
