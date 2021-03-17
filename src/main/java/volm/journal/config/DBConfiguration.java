package volm.journal.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConfiguration {
    private static final String LOGIN = "postgres";
    private static final String PASS = "1";
    private static final String URL = "jdbc:postgresql://localhost:5432/journal";

    public static Connection getConnection() throws SQLException {

            return DriverManager.getConnection(URL, LOGIN, PASS);
    }
}
