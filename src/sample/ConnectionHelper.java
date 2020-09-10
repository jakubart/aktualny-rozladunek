package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelper {

//    private static String url = "jdbc:mysql://localhost:3306/urlop?serverTimezone = ECT";
//    private static String user = "root";
//    private static String passwd = "admin";
    private static String url = "jdbc:mysql://url";
    private static String user = "user";
    private static String passwd = "pass";
    private static String dbDriver = "com.mysql.cj.jdbc.Driver";
    private static Connection conn;

    public static Connection getConnection() {
        try {
            Class.forName(dbDriver);
        } catch (ClassNotFoundException e ) {
            System.out.println("Problem ze sterownikiem" + e.getMessage());
            Plik.zapiszWyjatek(e);
        }
        try {
            conn = DriverManager.getConnection(url, user, passwd);
        } catch (SQLException e) {
            Plik.zapiszWyjatek(e);
            System.out.println("Nie udało się nawiązać połącznia" + e.getMessage());
            e.printStackTrace();
        }
        return conn;
    }
}
