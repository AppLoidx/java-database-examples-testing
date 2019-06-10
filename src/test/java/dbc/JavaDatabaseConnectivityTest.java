package dbc;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Arthur Kupriyanov
 */
class JavaDatabaseConnectivityTest {

    @Test
    void getConnection() {
        JavaDatabaseConnectivity dbc = new JavaDatabaseConnectivity();

        assertNotNull(dbc);
    }

    @BeforeAll
    public static void prepareDB() throws SQLException {
        JavaDatabaseConnectivity dbc = new JavaDatabaseConnectivity();
        Connection conn = dbc.getConnection();
        try {
            conn.createStatement().execute("DROP TABLE testTable");
        } catch (SQLException ignored){}
        conn.createStatement().execute("CREATE TABLE testTable ( " +
                "id integer NOT NULL , nameOf varchar, PRIMARY KEY (id))");
    }

    @Test
    void sensitiveAndInsensitive() throws SQLException {

        JavaDatabaseConnectivity dbc = new JavaDatabaseConnectivity();
        Connection conn = dbc.getConnection();

        conn.createStatement().execute("INSERT INTO testTable (id, nameOf) VALUES (14, 'pasha')");

        ResultSet rsInsensitive = conn.createStatement().executeQuery("SELECT * FROM testTable");
        ResultSet rsSensitive = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * from testTable");

        // sensitive - реагирует на изменения в таблице баз данных

        assertTrue(rsSensitive.next());
        assertTrue(rsInsensitive.next());
        assertEquals("pasha", rsInsensitive.getString("nameOf"));
        assertEquals("pasha", rsInsensitive.getString(2));
        assertEquals(14, rsInsensitive.getInt(1));
        assertEquals(14, rsInsensitive.getInt("id"));

        conn.createStatement().execute("INSERT INTO testTable (id, nameOf) VALUES (11, 'champloo')");


        // Здесь Insensitive - старые данные, а Sensitive - с новыми
        assertFalse(rsInsensitive.next());

        // assertTrue(rsSensitive.next());  -- но это не сработает так как в PostgreSQL не поддерживается Sensitive
        assertFalse(rsSensitive.next());

        // Такое не сработает : Operation requires a scrollable ResultSet, but this ResultSet is FORWARD_ONLY
//        rs = conn.createStatement().executeQuery("SELECT * FROM testTable");
        rsInsensitive = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE ).executeQuery("SELECT * from testTable");

        assertTrue(rsInsensitive.next());

        assertEquals("pasha", rsInsensitive.getString("nameOf"));
        assertEquals("pasha", rsInsensitive.getString(2));
        assertEquals(14, rsInsensitive.getInt(1));
        assertEquals(14, rsInsensitive.getInt("id"));

        assertTrue(rsInsensitive.next());

        assertEquals("champloo", rsInsensitive.getString("nameOf"));
        assertEquals("champloo", rsInsensitive.getString(2));
        assertEquals(11, rsInsensitive.getInt(1));
        assertEquals(11, rsInsensitive.getInt("id"));

        assertTrue(rsInsensitive.previous());
        assertFalse(rsInsensitive.previous());
        assertFalse(rsInsensitive.previous());
        assertFalse(rsInsensitive.previous());
        assertFalse(rsInsensitive.previous());
        assertTrue(rsInsensitive.next());

        assertEquals("pasha", rsInsensitive.getString("nameOf"));
        assertEquals("pasha", rsInsensitive.getString(2));
        assertEquals(14, rsInsensitive.getInt(1));
        assertEquals(14, rsInsensitive.getInt("id"));

        conn.createStatement().execute("DELETE FROM testTable WHERE id=14");
        PreparedStatement ps = conn.prepareStatement("DELETE FROM testTable WHERE id=?");
        ps.setInt(1, 11);

        ResultSet rs = conn.createStatement().executeQuery("SELECT * from testTable");
        assertFalse(rs.next());
    }

    @AfterAll
    public static void deleteDB(){
        JavaDatabaseConnectivity dbc = new JavaDatabaseConnectivity();
        Connection conn = dbc.getConnection();
        try {
            conn.createStatement().execute("DROP TABLE testTable");
        } catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }
}