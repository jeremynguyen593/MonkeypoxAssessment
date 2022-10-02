package quiz;

import java.sql.*;

/**
 * Sets up the main class at which the user should run the program.
 * 
 * @author Jeremy Nguyen
 * @version 9-13-2022
 */
public class MainClass {
    /**
     * The URL to use the JDBC API to connect to the local host.
     */
    static final String URL = "jdbc:mysql://localhost:3306/";

    /**
     * The URL to connect to the database of the assessment.
     */
    static final String URL_2 = "jdbc:mysql://localhost:3306/MONKEYPOXASSESSMENT";

    /**
     * The username for the mySQL login.
     */
    static final String USER = "root";
    /**
     * The password for the mySQL login.
     */
    static final String PASS = "";
     /**
     * The main method where the connection to a database is created to store information from the assessment.
     * 
     * @param args an array of command-line arguments for the application
     */
    public static void main (String[]args) throws Exception {
        Connection conn = null;
        Statement stmt = null;

        conn = DriverManager.getConnection(URL, USER, PASS);
        stmt = conn.createStatement();

        String sql = "CREATE DATABASE IF NOT EXISTS MONKEYPOXASSESSMENT";
        stmt.executeUpdate(sql);

        conn = DriverManager.getConnection(URL_2, USER, PASS);
        stmt = conn.createStatement();
        
        String table = "CREATE TABLE IF NOT EXISTS DATA" +
                       "(ID int NOT NULL auto_increment," +
                        "NAME VARCHAR(255)," +
                       "RISK VARCHAR(255)," +
                       "TIME TIMESTAMP," +
                       "PRIMARY KEY (ID))";
        
        stmt.executeUpdate(table);

        stmt.close();
        conn.close();

        new Login();
    }
}
