package init;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static Connection getConnection()
            throws ClassNotFoundException, SQLException {

        // Note: Change the connection parameters accordingly.
        String hostName = "localhost";
        String sid = "db12c";
        String userName = "mytest";
        String password = "12345";

        Class.forName("oracle.jdbc.driver.OracleDriver");
        // URL Connection for Oracle
        // Example:
        // jdbc:oracle:thin:@localhost:1521:db11g
        // jdbc:oracle:thin:@//HOSTNAME:PORT/SERVICENAME
        String connectionURL = "jdbc:oracle:thin:@" + hostName + ":1521:" + sid;

        return DriverManager.getConnection(connectionURL, userName,
                password);
    }

    public static void close(Connection conn) {
        try {
            conn.close();
        } catch (Exception ignored) {
        }
    }
}
