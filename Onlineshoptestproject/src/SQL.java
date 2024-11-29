/**
 * @author Simon Braun
 */

import java.nio.file.Paths;
import java.sql.*;

public class SQL {


//    public static String dbPath = "jdbc:sqlite:Database.db";
    public static String dbPath = "jdbc:sqlite:" + Paths.get("src", "Database.db").toString();
    public static Connection conn;

    public static boolean init() {
        try {
            conn = DriverManager.getConnection(dbPath);
        } catch (SQLException e) {
            System.out.println("Datenbankverbindungsfehler");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static ResultSet runQuery(String statement) throws SQLException {
        Statement stmt = conn.createStatement();
        return stmt.executeQuery(statement);
    }

    public static int runUpdate(String statement) throws SQLException {
        Statement stmt = conn.createStatement();
        return stmt.executeUpdate(statement);
    }


}
