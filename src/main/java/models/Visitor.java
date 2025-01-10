package models;

import interfaces.VisitorActions;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Visitor extends Person implements VisitorActions {



    // Attributes
    private int id_visitor;
    private int id_person;
    private int ticketid;
    private Date visit_date;



    //Constructor
    public Visitor(int id_visitor, int id_person, int ticketid, Date visit_date,
                   String firstName, String lastName, int age, String gender, String email, String phoneNumber) {
        super(id_person, firstName, lastName, age, gender, email, phoneNumber);
        this.id_visitor = id_visitor;
        this.id_person = id_person;
        this.ticketid = ticketid;
        this.visit_date = visit_date;
    }

    public Visitor( int id_visitor, int id_person, int ticketid, Date visit_date) {
        super();
        this.id_visitor = id_visitor;
        this.id_person = id_person;
        this.ticketid = ticketid;
        this.visit_date = visit_date;
    }

    public Visitor() {

    }


    // Getters and Setters
    public int getId_visitor() {
        return id_visitor;
    }

    public int getId_person() {
        return id_person;
    }

    public int getTicketid() {
        return ticketid;
    }

    public Date getVisit_date() {
        return visit_date;
    }

    public void setId_visitor(int id_visitor) {
        this.id_visitor = id_visitor;
    }

    public void setId_person(int id_person) {
        this.id_person = id_person;
    }

    public void setTicketid(int ticketid) {
        this.ticketid = ticketid;
    }

    public void setVisit_date(Date visit_date) {
        this.visit_date = visit_date;
    }



    // Methods
    public static List<Visitor> getAllVisitors() {
        System.out.println("Fetching visitors...");

        List<Visitor> visitors = new ArrayList<>();

        // Connect to the database
        Connection connection = DatabaseConnection.connect();
        if (connection != null) {
            try {
                String selectQuery = "SELECT * FROM Visitor";
                Statement statement = connection.createStatement();

                // Execute the query
                ResultSet resultSet = statement.executeQuery(selectQuery);



                // Populate the list of exhibitions
                while (resultSet.next()) {
                    int id_visitor = resultSet.getInt("id_visitor");
                    int id_person = resultSet.getInt("id_person");
                    int ticketid = resultSet.getInt("ticketid");
                    Date visit_date = resultSet.getDate("visit_date");

                    // Create a Visitor object
                    Visitor visitor = new Visitor(
                           id_visitor,
                            id_person,
                            ticketid,
                            visit_date
                    );

                    // Add to the list
                    visitors.add(visitor);
                }
                System.out.println("Number of visitors fetched: " + visitors.size());
                // Close the result set and statement
                resultSet.close();
                statement.close();

            } catch (SQLException e) {
                System.out.println("Error fetching visitors: " + e.getMessage());
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Error closing the connection: " + e.getMessage());
                }
            }
        } else {
            System.out.println("Failed to connect to the database.");
        }

        return visitors;
    }



    @Override
    public void listVisitors() {
        // Obtain the connection to database
        Connection connection = DatabaseConnection.connect();
        if (connection != null) {
            // Define the file path where you want to save the data
            String filePath = "VisitorsList.txt";  // Path to the file

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                String query = "SELECT * FROM Visitor";
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    int visitorId = resultSet.getInt("id_visitor");
                    int personId = resultSet.getInt("id_person");
                    int ticketId = resultSet.getInt("ticketid");
                    Date visitDate = resultSet.getDate("visit_date");

                    // Writing data to the file
                    writer.write("Visitor ID: " + visitorId);
                    writer.newLine();
                    writer.write("Person ID: " + personId);
                    writer.newLine();
                    writer.write("Ticket ID: " + ticketId);
                    writer.newLine();
                    writer.write("Visit Date: " + visitDate);
                    writer.newLine();
                    writer.write("----------------------------------");
                    writer.newLine();  // New line between each visitor
                }
                System.out.println("Visitors have been written to " + filePath);

            } catch (SQLException e) {
                System.out.println("Error SQL: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("Error writing to file: " + e.getMessage());
            } finally {
                try {
                    connection.close();  // Closing connection
                } catch (SQLException e) {
                    System.out.println("Error while closing the connection: " + e.getMessage());
                }
            }
        }
    }


    public static Visitor getVisitorById(int visitor_id) {
        System.out.println("Searching for visitor with ID: '" + visitor_id + "'...");

        Connection connection = DatabaseConnection.connect();
        if (connection != null) {
            try {
                // Query pentru a găsi vizitatorul după ID
                String selectQuery = "SELECT * FROM Visitor WHERE id_visitor = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
                preparedStatement.setInt(1, visitor_id); // Folosim setInt pentru câmp numeric

                // Executăm query-ul
                ResultSet resultSet = preparedStatement.executeQuery();

                // Verificăm dacă există un rezultat
                if (resultSet.next()) {
                    int id_visitor = resultSet.getInt("id_visitor");
                    int id_person = resultSet.getInt("id_person");
                    int ticketid = resultSet.getInt("ticketid");
                    Date visit_date = resultSet.getDate("visit_date");

                    // Creăm obiectul Visitor
                    Visitor visitor = new Visitor(
                            id_visitor,
                            id_person,
                            ticketid,
                            visit_date
                    );

                    // Returnăm obiectul Visitor
                    resultSet.close();
                    preparedStatement.close();
                    connection.close();
                    return visitor;
                } else {
                    System.out.println("No visitor found with ID: '" + visitor_id + "'.");
                }

                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println("Error retrieving visitor: " + e.getMessage());
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Error closing the connection: " + e.getMessage());
                }
            }
        } else {
            System.out.println("Failed to connect to the database.");
        }

        return null; // Return null dacă vizitatorul nu este găsit sau apare o eroare
    }



    @Override
    public void displayVisitors() {
        Connection connection = DatabaseConnection.connect();
        if (connection != null) {
            try {
                String query = "SELECT * FROM Visitor";
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    System.out.println("Visitor ID: " + resultSet.getInt("id_visitor"));
                    System.out.println("Person ID: " + resultSet.getInt("id_person"));
                    System.out.println("Ticket ID: " + resultSet.getInt("ticketid"));
                    System.out.println("Visit Date: " + resultSet.getDate("visit_date"));
                    System.out.println("----------------------------------");
                }
                resultSet.close();
                statement.close();
            } catch (SQLException e) {
                System.out.println("Error retrieving visitors: " + e.getMessage());
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Error closing connection: " + e.getMessage());
                }
            }
        } else {
            System.out.println("Failed to connect to the database.");
        }
    }



    // Method to add a visitor
    @Override
    public void addVisitor(int idVisitor, int idPerson, int ticketId, Date visitDate) {
        Connection connection = DatabaseConnection.connect();
        if (connection != null) {
            try {
                String insertQuery = "INSERT INTO Visitor (id_visitor, id_person, ticketid, visit_date) VALUES (?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                preparedStatement.setInt(1, idVisitor);
                preparedStatement.setInt(2, idPerson);
                preparedStatement.setInt(3, ticketId);
                preparedStatement.setDate(4, (java.sql.Date) visitDate);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Visitor added successfully.");
                } else {
                    System.out.println("Failed to add visitor.");
                }

                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println("Error adding visitor: " + e.getMessage());
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Error closing connection: " + e.getMessage());
                }
            }
        } else {
            System.out.println("Failed to connect to the database.");
        }
    }

    // Method to delete a visitor by ID
    @Override
    public void deleteVisitor(int idVisitor) {
        Connection connection = DatabaseConnection.connect();
        if (connection != null) {
            try {
                String deleteQuery = "DELETE FROM Visitor WHERE id_visitor = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
                preparedStatement.setInt(1, idVisitor);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Visitor deleted successfully.");
                } else {
                    System.out.println("No visitor found with the given ID.");
                }

                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println("Error deleting visitor: " + e.getMessage());
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Error closing connection: " + e.getMessage());
                }
            }
        } else {
            System.out.println("Failed to connect to the database.");
        }
    }



    // Method to search for a visitor by id
    @Override
    public void printVisitorById(int idVisitor) {
        Connection connection = DatabaseConnection.connect();
        if (connection != null) {
            try {
                String query = "SELECT * FROM Visitor WHERE id_visitor = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, idVisitor);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    System.out.println("Visitor Details:");
                    System.out.println("Visitor ID: " + resultSet.getInt("id_visitor"));
                    System.out.println("Person ID: " + resultSet.getInt("id_person"));
                    System.out.println("Ticket ID: " + resultSet.getInt("ticketid"));
                    System.out.println("Visit Date: " + resultSet.getDate("visit_date"));
                } else {
                    System.out.println("No visitor found with ID: " + idVisitor);
                }

                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println("Error retrieving visitor: " + e.getMessage());
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Error closing connection: " + e.getMessage());
                }
            }
        } else {
            System.out.println("Failed to connect to the database.");
        }
    }



}
