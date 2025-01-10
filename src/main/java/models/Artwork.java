package models;

import interfaces.ArtworkActions;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Artwork implements ArtworkActions {



    // Attributes
    private int idExhibition;
    private String title;
    private String description;
    private String artist;
    private int year;
    private boolean exhibited;



    // Constructors
    public Artwork(int idExhibition,String title, String description, String artist, int year, boolean exhibited) {
        this.title = title;
        this.description = description;
        this.artist = artist;
        this.year = year;
        this.exhibited = exhibited;
        this.idExhibition = idExhibition;

    }
    public Artwork() {}



    // Methods
    // Update an unexposed artwork in my database table Artwork as exposed
    @Override
    public void exhibit(String title) {
        System.out.println("Attempting to expose the artwork '" + title + "'.");

        Connection connection = DatabaseConnection.connect();
        if (connection != null) {
            try {
                String updateQuery = "UPDATE Artwork SET exhibited = TRUE WHERE title = ? AND exhibited = FALSE";
                PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
                preparedStatement.setString(1, title);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0)
                    System.out.println("The artwork '" + title + "' has been exposed and updated in the database.");
                 else
                    System.out.println("No matching unexposed artwork found for '" + title + "'.");

                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println("Error updating the database: " + e.getMessage());
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



    // Update an exposed artwork in my database table Artwork as unexposed
    @Override
    public void removeFromExhibit(String title) {
        System.out.println("Attempting to remove the artwork '" + title + "'.");

        Connection connection = DatabaseConnection.connect();
        if (connection != null) {
            try {
                String updateQuery = "UPDATE Artwork SET exhibited = FALSE WHERE title = ? AND exhibited = TRUE";
                PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
                preparedStatement.setString(1, title);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0)
                    System.out.println("The artwork '" + title + "' has been removed from the database.");
                else
                    System.out.println("No matching exposed artwork found for '" + title + "'.");

                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println("Error updating the database: " + e.getMessage());
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



    // Add a non-existential artwork to my database
    @Override
    public void addArtworkToDatabase(Connection connection) {
        // Check if the artwork already exists
        String checkQuery = "SELECT COUNT(*) FROM Artwork WHERE title = ? AND artist = ?";
        String insertQuery = "INSERT INTO Artwork (title, description, artist, year, exhibited, id_exhibition) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement checkStmt = connection.prepareStatement(checkQuery)) {
            checkStmt.setString(1, this.title);
            checkStmt.setString(2, this.artist);

            ResultSet resultSet = checkStmt.executeQuery();
            if (resultSet.next() && resultSet.getInt(1) > 0) {
                System.out.println("Artwork already exists in the database.");
                return; // Exit the method if the artwork already exists
            }
        } catch (SQLException e) {
            System.out.println("Error checking artwork existence: " + e.getMessage());
            return; // Exit the method on error
        }

        // Insert the artwork if it does not exist
        try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {
            insertStmt.setString(1, this.title);
            insertStmt.setString(2, this.description);
            insertStmt.setString(3, this.artist);
            insertStmt.setInt(4, this.year);
            insertStmt.setBoolean(5, this.exhibited);
            if (this.idExhibition != 0) {
                insertStmt.setInt(6, this.idExhibition);
            } else {
                insertStmt.setNull(6, java.sql.Types.INTEGER);
            }

            int rowsInserted = insertStmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Artwork added successfully to the database.");
            }
        } catch (SQLException e) {
            System.out.println("Error adding artwork to the database: " + e.getMessage());
        }
    }



    // Remove an existential artwork from my database
    @Override
    public void removeArtwork(String title) {
        System.out.println("Attempting to remove the artwork with the title: '" + title + "'.");

        Connection connection = DatabaseConnection.connect();
        if (connection != null) {
            try {
                // SQL query to delete artwork by title
                String deleteQuery = "DELETE FROM Artwork WHERE title = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
                preparedStatement.setString(1, title); // Set the title parameter

                // Execute the delete query
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("The artwork with title '" + title + "' has been removed from the database.");
                } else {
                    System.out.println("No artwork found with the title '" + title + "'.");
                }

                // Close the prepared statement
                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println("Error deleting the artwork from the database: " + e.getMessage());
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



    // Change the title of an art-piece in my database
    @Override
    public void changeTitle(String oldTitle, String newTitle) {
        System.out.println("Attempting to change the title of artwork from '" + oldTitle + "' to '" + newTitle + "'.");

        // Connecting to the database
        Connection connection = DatabaseConnection.connect();
        if (connection != null) {
            try {
                // SQL query to update the title of an artwork
                String updateQuery = "UPDATE Artwork SET title = ? WHERE title = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
                preparedStatement.setString(1, newTitle);  // Set the new title
                preparedStatement.setString(2, oldTitle);  // Set the old title

                // Execute the update query
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("The title of artwork '" + oldTitle + "' has been successfully changed to '" + newTitle + "'.");
                } else {
                    System.out.println("No artwork found with the title '" + oldTitle + "'.");
                }

                // Close the prepared statement
                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println("Error updating the artwork title in the database: " + e.getMessage());
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



    // Change the artist of an art-piece in my database
    @Override
    public void changeArtist(String title, String newArtist) {
        System.out.println("Attempting to change the artist of artwork '" + title+"' to '" + newArtist + "'.");

        // Connecting to the database
        Connection connection = DatabaseConnection.connect();
        if (connection != null) {
            try {
                // SQL query to update the artist of an artwork
                String updateQuery = "UPDATE Artwork SET artist = ? WHERE title = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
                preparedStatement.setString(1, newArtist);  // Set the new title
                preparedStatement.setString(2, title);  // Set the old title

                // Execute the update query
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("The artist of artwork '" + title + "' has been successfully changed to '" + newArtist + "'.");
                } else {
                    System.out.println("No artwork found with the title '" + title + "'.");
                }

                // Close the prepared statement
                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println("Error updating the artwork title in the database: " + e.getMessage());
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



    // Changing year of a title in my database
    @Override
    public void changeYear(String title, int newYear) {
        System.out.println("Attempting to change the year of artwork '" + title + "' to " + newYear + ".");

        Connection connection = DatabaseConnection.connect();
        if (connection != null) {
            try {
                String updateQuery = "UPDATE Artwork SET year = ? WHERE title = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
                preparedStatement.setInt(1, newYear);
                preparedStatement.setString(2, title);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("The year of artwork '" + title + "' has been successfully changed to " + newYear + ".");
                } else {
                    System.out.println("No artwork found with the title '" + title + "'.");
                }

                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println("Error updating the artwork year in the database: " + e.getMessage());
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



    // Changing the description
    @Override
    public void changeDescription(String title, String newDescription) {
        System.out.println("Attempting to change the description of artwork '" + title + "'.");

        Connection connection = DatabaseConnection.connect();
        if (connection != null) {
            try {
                String updateQuery = "UPDATE Artwork SET description = ? WHERE title = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
                preparedStatement.setString(1, newDescription);
                preparedStatement.setString(2, title);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("The description of artwork '" + title + "' has been successfully changed.");
                } else {
                    System.out.println("No artwork found with the title '" + title + "'.");
                }

                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println("Error updating the artwork description in the database: " + e.getMessage());
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



    // Print artworks that are not exhibited yet in my console
    @Override
    public void printNonExhibitedArtworks() {
        System.out.println("Fetching non-exhibited artworks...");

        // Connect to the database
        Connection connection = DatabaseConnection.connect();
        if (connection != null) {
            try {
                // Query to fetch all artworks that are not exhibited
                String selectQuery = "SELECT * FROM Artwork WHERE exhibited = FALSE";
                Statement statement = connection.createStatement();

                // Execute the query
                ResultSet resultSet = statement.executeQuery(selectQuery);

                // Check if there are any results and print them
                boolean hasResults = false;
                while (resultSet.next()) {
                    hasResults = true;
                    String title = resultSet.getString("title");
                    String description = resultSet.getString("description");
                    String artist = resultSet.getString("artist");
                    int year = resultSet.getInt("year");

                    // Print the details of each artwork that is not exhibited
                    System.out.println("Artwork: " + title);
                    System.out.println("Description: " + description);
                    System.out.println("Artist: " + artist);
                    System.out.println("Year: " + year);
                    System.out.println("Exhibited: No");
                    System.out.println("-------------------------------");
                }

                if (!hasResults) {
                    System.out.println("No non-exhibited artworks found.");
                }

                // Close the result set and statement
                resultSet.close();
                statement.close();

            } catch (SQLException e) {
                System.out.println("Error fetching non-exhibited artworks: " + e.getMessage());
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



    //Print details of an art-piece searched by title
    @Override
    public void printArtworkDetailsByTitle(String titleToSearch) {
        System.out.println("Searching for artwork with title: " + titleToSearch);

        // Connect to the database
        Connection connection = DatabaseConnection.connect();
        if (connection != null) {
            try {
                // Query to fetch artwork by title from the Artwork table
                String selectQuery = "SELECT * FROM Artwork WHERE title = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
                preparedStatement.setString(1, titleToSearch);

                // Execute the query
                ResultSet resultSet = preparedStatement.executeQuery();

                // If an artwork is found, print its details
                if (resultSet.next()) {
                    String title = resultSet.getString("title");
                    String description = resultSet.getString("description");
                    String artist = resultSet.getString("artist");
                    int year = resultSet.getInt("year");
                    boolean exhibited = resultSet.getBoolean("exhibited");

                    // Print the details of the artwork
                    System.out.println("Artwork found:");
                    System.out.println("Title: " + title);
                    System.out.println("Description: " + description);
                    System.out.println("Artist: " + artist);
                    System.out.println("Year: " + year);
                    System.out.println("Exhibited: " + (exhibited ? "Yes" : "No"));
                    System.out.println("-------------------------------");
                } else {
                    System.out.println("No artwork found with the title: " + titleToSearch);
                }

                // Close resources
                resultSet.close();
                preparedStatement.close();

            } catch (SQLException e) {
                System.out.println("Error fetching artwork details: " + e.getMessage());
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



    @Override
    public void ListArtworks() {
        Connection connection = DatabaseConnection.connect();
        if (connection != null) {
            // Define the file path where you want to save the data
            String filePath = "ArtworkList.txt";  // Path to the file

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                // Create a statement
                Statement statement = connection.createStatement();
                // Select from table
                ResultSet resultSet = statement.executeQuery("SELECT * FROM Artwork");

                // Writing to file
                while (resultSet.next()) {
                    int artwork_id = resultSet.getInt("artwork_id");
                    String title = resultSet.getString("title");
                    String description = resultSet.getString("description");
                    String artist = resultSet.getString("artist");
                    int year = resultSet.getInt("year");
                    boolean exhibited = resultSet.getBoolean("exhibited");

                    // Write the artwork details to the file
                    writer.write("Artwork ID: " + artwork_id);
                    writer.newLine();
                    writer.write("Title: " + title);
                    writer.newLine();
                    writer.write("Description: " + description);
                    writer.newLine();
                    writer.write("Artist: " + artist);
                    writer.newLine();
                    writer.write("Year: " + year);
                    writer.newLine();
                    writer.write("Exhibited: " + exhibited);
                    writer.newLine();
                    writer.write("-----------------------------");
                    writer.newLine(); // Add a new line to separate entries
                }
                System.out.println("Artworks have been written to " + filePath);

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



        // Print artwork from database table Artwork in my console
        @Override
        public void displayArtworks() {
            // Obtain the connection to database
            Connection connection = DatabaseConnection.connect();
            if (connection != null) {
                try {
                    // Create a statement
                    Statement statement = connection.createStatement();
                    // Select from table
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM Artwork");

                    // Printing
                    while (resultSet.next()) {
                        int artwork_id = resultSet.getInt("artwork_id");
                        String title = resultSet.getString("title");
                        String description = resultSet.getString("description");
                        String artist = resultSet.getString("artist");
                        int year = resultSet.getInt("year");
                        boolean exhibited = resultSet.getBoolean("exhibited");

                        System.out.println("Artwork ID: " + artwork_id);
                        System.out.println("Title: " + title);
                        System.out.println("Description: " + description);
                        System.out.println("Artist: " + artist);
                        System.out.println("Year: " + year);
                        System.out.println("Exhibited: " + exhibited);
                        System.out.println("-----------------------------");
                    }

                } catch (SQLException e) {
                    System.out.println("Error SQL: " + e.getMessage());
                } finally {
                    try {
                        connection.close();  //Closing connection
                    } catch (SQLException e) {
                        System.out.println("Error while closing the connection: " + e.getMessage());
                    }
                }
            }

        }
    }

