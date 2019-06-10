package dbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Arthur Kupriyanov
 */
public class JavaDatabaseConnectivity {

    public Connection getConnection(){
        try {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            Properties prop = new Properties();
            prop.put("user", "postgre");
            prop.put("passwd", "postgre");
            return DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "postgres");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void sample(){

    }

}
