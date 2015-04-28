package ro.teamnet.zth.api.database;

import com.mysql.jdbc.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by MN on 4/28/2015.
 */
public class DBManager {
    private DBManager() {
        throw new UnsupportedOperationException();
    }

    public static final String CONNECTION_STRING = "jdbc:mysql://" + DProperties.IP + "/" + DProperties.SCHEMA;

    private static void registerDriver() {
        try {
            Class.forName(DProperties.DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection con = null;
        registerDriver();
        try {
            con = (Connection) DriverManager.getConnection(CONNECTION_STRING, DProperties.USER, DProperties.PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;

    }

}
