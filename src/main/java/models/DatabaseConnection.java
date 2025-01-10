package models;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConnection {
    public static void main(String[] args) {
        // Connect to PostgreSQL
        String url = "jdbc:postgresql://localhost:5433/Museum"; // Name of database
        String user = "postgres";  // My PostgreSQL user
        String password = "1q2w3e";  // PostgreSQL password

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected successfully to database!");

        } catch (SQLException e) {
            System.out.println("Error connecting to database : " + e.getMessage());
        }
    }
    public static Connection connect() {
        // Detaliile conexiunii la baza de date
        String url = "jdbc:postgresql://localhost:5433/Museum";
        String username = "postgres";
        String password = "1q2w3e";

        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error connecting: " + e.getMessage());
            return null;
        }
    }
}

