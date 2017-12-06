package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by p16005334 on 25/09/17.
 */
public class ConnexionUnique {
    private Connection connection;
    private static ConnexionUnique instance = null;
    private static final String CONNECT_URL = "jdbc:mysql://mysql-ptutequipe2g1.alwaysdata.net:3306/ptutequipe2g1_bd";
    private static final String LOGIN = "148356";
    private static final String PASSWORD = "oui";

    private ConnexionUnique () {
        try {
            connection = DriverManager.getConnection(CONNECT_URL,LOGIN,PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static ConnexionUnique getInstance() {
        if(instance == null)
            instance = new ConnexionUnique();
        return instance;
    }
}
