package ws;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    public static final String url = "jdbc:mysql://127.0.0.1:3306/shop";
    public static final String user = "root";
    public static final String password = "1234";

    public static Connection getConnection() throws SQLException {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void close(AutoCloseable... closeables) {
        for(AutoCloseable closeble : closeables) {
            if(closeble != null) {
                try{
                    closeble.close();
                } catch(Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
