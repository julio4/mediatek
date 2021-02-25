package persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static Connection getConnection() throws ClassNotFoundException, SQLException {

        // Info de connexion
        String host = "localhost";
        String sid = "XE";
        String user = "SYSTEM";
        String password = "ROOT";

        // Pour Oracle
        Class.forName("oracle.jdbc.driver.OracleDriver");
        String connectionURL = "jdbc:oracle:thin:@" + host + ":1521:" + sid;

        return DriverManager.getConnection(connectionURL, user, password);
    }

    public static void close(Connection conn) {
        try {
            conn.close();
        } catch (Exception ignored) {}
    }
}
