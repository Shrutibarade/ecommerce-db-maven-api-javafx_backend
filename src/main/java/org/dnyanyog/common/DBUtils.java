package org.dnyanyog.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils{
    
	private static final String DB_URL = "jdbc:mysql://localhost:3306/ecommerece_management";

    private static final String USERNAME = "root";
    private static final String PASSWORD = "shruti9160";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
    }
}
