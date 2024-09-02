package sports;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3307/SportsManagementDB";
    private static final String USER = "root";
    private static final String PASSWORD = "Dharsh@21";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3307/SportsManagementDB","root","Dharsh@21" );
    }
}

