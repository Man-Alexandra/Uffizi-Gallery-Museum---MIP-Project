package models;

import interfaces.TicketActions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Ticket implements TicketActions {


    //Attributes
    private int ticketId;
    private TicketType ticketType;
    private boolean isValid;



    //Setters and Getters
    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public int getTicketId() {
        return ticketId;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public boolean isValid() {
        return isValid;
    }



    //Enum
    public enum TicketType {
        ADULT, CHILD, STUDENT, SENIOR
    }



    //Constructor
    public Ticket(int ticketId, TicketType ticketType, boolean isValid) {
        this.ticketId = ticketId;
        this.ticketType = ticketType;
        this.isValid = true;
    }

    public Ticket() {

    }

    // Methods
    public static List<Ticket> getAllTickets() {
        System.out.println("Fetching tickets...");

        List<Ticket> tickets = new ArrayList<>();

        // Connect to the database
        Connection connection = DatabaseConnection.connect();
        if (connection != null) {
            try {
                String selectQuery = "SELECT * FROM Ticket";
                Statement statement = connection.createStatement();

                // Execute the query
                ResultSet resultSet = statement.executeQuery(selectQuery);


                while (resultSet.next()) {
                    int id_ticket = resultSet.getInt("ticketid");
                    String type = resultSet.getString("tickettype");
                    boolean valid = resultSet.getBoolean("isvalid");

                    TicketType ticketType;
                    try {
                        ticketType = TicketType.valueOf(type.toUpperCase(Locale.ROOT));
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid ticket type: " + type);
                        continue; // Skip this ticket if the type is invalid
                    }

                    // Create a Visitor object
                    Ticket ticket = new Ticket(
                            id_ticket,
                            ticketType,
                            valid
                    );

                    // Add to the list
                    tickets.add(ticket);
                }
                System.out.println("Number of tickets fetched: " + tickets.size());
                // Close the result set and statement
                resultSet.close();
                statement.close();

            } catch (SQLException e) {
                System.out.println("Error fetching tickets: " + e.getMessage());
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

        return tickets;
    }



    // Display all tickets from database
    @Override
    public void displayTickets() {
        System.out.println("Displaying all tickets from the database...");

        // Connecting to the database
        Connection connection = DatabaseConnection.connect();
        if (connection != null) {
            try {
                // SQL query to fetch all tickets
                String selectQuery = "SELECT * FROM Tickets";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(selectQuery);

                while (resultSet.next()) {
                    String ticketId = resultSet.getString("ticketid");
                    String ticketType = resultSet.getString("tickettype");
                    boolean isValid = resultSet.getBoolean("isvalid");

                    System.out.println("Ticket ID: " + ticketId + ", Type: " + ticketType + ", Valid: " + isValid);
                }

                // Close the statement and resultSet
                resultSet.close();
                statement.close();
            } catch (SQLException e) {
                System.out.println("Error fetching tickets from the database: " + e.getMessage());
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
    }



    // Add a ticket to database
    @Override
    public void addTicket(String ticketId, String ticketType, boolean isValid) {
        System.out.println("Attempting to add a new ticket with ID: " + ticketId);

        // Connecting to the database
        Connection connection = DatabaseConnection.connect();
        if (connection != null) {
            try {
                // SQL query to insert a new ticket
                String insertQuery = "INSERT INTO Ticket (ticketid, tickettype, isvalid) VALUES (?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                preparedStatement.setString(1, ticketId);
                preparedStatement.setString(2, ticketType);
                preparedStatement.setBoolean(3, isValid);

                // Execute the insert query
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Ticket with ID '" + ticketId + "' has been successfully added.");
                } else {
                    System.out.println("Failed to add ticket.");
                }

                // Close the prepared statement
                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println("Error adding ticket to the database: " + e.getMessage());
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
    }



    // Remove a ticket
    @Override
    public void removeTicket(String ticketId) {
        System.out.println("Attempting to remove ticket with ID: " + ticketId);

        // Connecting to the database
        Connection connection = DatabaseConnection.connect();
        if (connection != null) {
            try {
                // SQL query to delete a ticket by ticketId
                String deleteQuery = "DELETE FROM Ticket WHERE ticketid = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
                preparedStatement.setString(1, ticketId);

                // Execute the delete query
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Ticket with ID '" + ticketId + "' has been successfully removed.");
                } else {
                    System.out.println("No ticket found with ID '" + ticketId + "'.");
                }

                // Close the prepared statement
                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println("Error removing ticket from the database: " + e.getMessage());
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
    }



    // Change ticket type
    @Override
    public void changeTicketType(String ticketId, String newTicketType) {
        System.out.println("Attempting to change the ticket type for ticket with ID: " + ticketId);

        // Connecting to the database
        Connection connection = DatabaseConnection.connect();
        if (connection != null) {
            try {
                // SQL query to update the ticket type
                String updateQuery = "UPDATE Ticket SET tickettype = ? WHERE ticketid = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
                preparedStatement.setString(1, newTicketType);
                preparedStatement.setString(2, ticketId);

                // Execute the update query
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Ticket type for ticket with ID '" + ticketId + "' has been successfully changed to '" + newTicketType + "'.");
                } else {
                    System.out.println("No ticket found with ID '" + ticketId + "'.");
                }

                // Close the prepared statement
                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println("Error changing ticket type in the database: " + e.getMessage());
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
    }



    // Validate ticket
    @Override
    public void validateTicket(String ticketId) {
        System.out.println("Attempting to validate ticket with ID: " + ticketId);

        // Connecting to the database
        Connection connection = DatabaseConnection.connect();
        if (connection != null) {
            try {
                // SQL query to set the ticket as valid
                String updateQuery = "UPDATE Ticket SET isvalid = TRUE WHERE ticketid = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
                preparedStatement.setString(1, ticketId);

                // Execute the update query
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Ticket with ID '" + ticketId + "' has been successfully validated.");
                } else {
                    System.out.println("No ticket found with ID '" + ticketId + "'.");
                }

                // Close the prepared statement
                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println("Error validating the ticket in the database: " + e.getMessage());
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
    }



    // Invalidate ticket
    @Override
    public void invalidateTicket(String ticketId) {
        System.out.println("Attempting to invalidate ticket with ID: " + ticketId);

        // Connecting to the database
        Connection connection = DatabaseConnection.connect();
        if (connection != null) {
            try {
                // SQL query to set the ticket as invalid
                String updateQuery = "UPDATE Ticket SET isvalid = FALSE WHERE ticketid = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
                preparedStatement.setString(1, ticketId);

                // Execute the update query
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Ticket with ID '" + ticketId + "' has been successfully invalidated.");
                } else {
                    System.out.println("No ticket found with ID '" + ticketId + "'.");
                }

                // Close the prepared statement
                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println("Error invalidating the ticket in the database: " + e.getMessage());
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
    }



}
