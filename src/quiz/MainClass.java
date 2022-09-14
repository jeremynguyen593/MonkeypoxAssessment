package quiz;

import java.sql.*;

/*
 * Sets up the main class at which the user should run the program.
 * 
 * @author Jeremy Nguyen
 * @version 9-13-2022
 */
public class MainClass {
    /*
     * The driver for the mySQL connection.
     */
    static final String DRIVER = "com.mysql.jdbc.Driver";
    /*
     * The URL to use the JDBC API to connect to the local host.
     */
    static final String URL = "jdbc:mysql://localhost:3306/";

    /*
     * The username for the mySQL login.
     */
    static final String USER = "root";
    /*
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

        Class.forName(DRIVER);

        conn = DriverManager.getConnection(URL, USER, PASS);
        stmt = conn.createStatement();

        String sql = "CREATE DATABASE IF NOT EXISTS MONKEYPOXASSESSMENT";
        stmt.executeUpdate(sql);
        System.out.println("Sucess");

        stmt.close();
        conn.close();

        new Login();
    }
}
