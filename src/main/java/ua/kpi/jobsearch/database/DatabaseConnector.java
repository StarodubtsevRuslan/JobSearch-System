package ua.kpi.jobsearch.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    
   
    private static final String URL = "jdbc:mysql://localhost:3306/kpi_jobsearch?useUnicode=true&characterEncoding=utf8";
    private static final String USER = "root"; 
    private static final String PASSWORD = ""; 

    public static Connection getConnection() throws SQLException {
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Помилка: Драйвер MySQL не знайдено!", e);
        }
        
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}