package models;

import java.util.ArrayList;
import java.util.Date;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.List;

public class Exhibition extends Artwork{



    // Attributes
    private int ID_exhibition;
    private String exhibitionName;
    private int exhibitionDuration;
    private int maxCapacity;
    private Date startDate;
    private Date endDate;



    // Constructors
    public Exhibition(int idExhibition, String title, String description, String artist, int year, boolean exhibited,
                      int ID_exhibition, String exhibitionName, int exhibitionDuration, int maxCapacity,
                      Date startDate, Date endDate) {
        // Call the parent class (Artwork) constructor
        super();

        // Initialize Exhibition-specific attributes
        this.ID_exhibition = ID_exhibition;
        this.exhibitionName = exhibitionName;
        this.exhibitionDuration = exhibitionDuration;
        this.maxCapacity = maxCapacity;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Exhibition(){}

    public Exhibition(int idExhibition, String nameExhibition, int durationExhibition, int capacityExhibition, Date startDateExhibition, Date endDateExhibition) {
        this.ID_exhibition = idExhibition;
        this.exhibitionName = nameExhibition;
        this.exhibitionDuration = durationExhibition;
        this.maxCapacity = capacityExhibition;
        this.startDate = startDateExhibition;
        this.endDate= endDateExhibition;
    }



    // Setters and Getters
    public String getExhibitionName() {
        return exhibitionName;
    }

    public int getID_exhibition() {
        return ID_exhibition;
    }

    public void setID_exhibition(int ID_exhibition) {
        this.ID_exhibition = ID_exhibition;
    }

    public void setExhibitionName(String exhibitionName) {
        this.exhibitionName = exhibitionName;
    }

    public int getExhibitionDuration() {
        return exhibitionDuration;
    }

    public void setExhibitionDuration(int exhibitionDuration) {
        this.exhibitionDuration = exhibitionDuration;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }



    // Methods
    public static List<Exhibition> getAllExhibitions() {
        System.out.println("Fetching exhibitions...");

        List<Exhibition> exhibitions = new ArrayList<>();

        // Connect to the database
        Connection connection = DatabaseConnection.connect();
        if (connection != null) {
            try {
                // Query to fetch all exhibitions from the 'Exhibition' table
                String selectQuery = "SELECT * FROM Exhibition";
                Statement statement = connection.createStatement();

                // Execute the query
                ResultSet resultSet = statement.executeQuery(selectQuery);



                // Populate the list of exhibitions
                while (resultSet.next()) {
                    int idExhibition = resultSet.getInt("id_exhibition");
                    String nameExhibition = resultSet.getString("name_exhibition");
                    int durationExhibition = resultSet.getInt("duration_exhibition");
                    int capacityExhibition = resultSet.getInt("capacity_exhibition");
                    Date startDateExhibition = resultSet.getDate("startdate_exhibition");
                    Date endDateExhibition = resultSet.getDate("enddate_exhibition");

                    // Create an Exhibition object
                    Exhibition exhibition = new Exhibition(
                            idExhibition,
                            nameExhibition,
                            durationExhibition,
                            capacityExhibition,
                            startDateExhibition,
                            endDateExhibition
                    );

                    // Add to the list
                    exhibitions.add(exhibition);
                }
                System.out.println("Number of exhibitions fetched: " + exhibitions.size());
                // Close the result set and statement
                resultSet.close();
                statement.close();

            } catch (SQLException e) {
                System.out.println("Error fetching exhibitions: " + e.getMessage());
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

        return exhibitions;
    }



    public static boolean addExhibition(String name, int duration, int capacity, java.sql.Date startDate, java.sql.Date endDate) {
        Connection connection = DatabaseConnection.connect();
        if (connection != null) {
            try {
                String insertQuery = "INSERT INTO Exhibition (name_exhibition, duration_exhibition, capacity_exhibition, startdate_exhibition, enddate_exhibition) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                preparedStatement.setString(1, name);
                preparedStatement.setInt(2, duration);
                preparedStatement.setInt(3, capacity);
                preparedStatement.setDate(4, startDate);
                preparedStatement.setDate(5, endDate);

                int rowsAffected = preparedStatement.executeUpdate();
                preparedStatement.close();
                connection.close();

                return rowsAffected > 0; // Returnează true dacă inserarea a avut succes
            } catch (SQLException e) {
                System.out.println("Error adding exhibition: " + e.getMessage());
            }
        } else {
            System.out.println("Failed to connect to the database.");
        }
        return false; // Returnează false în caz de eroare
    }



    public static boolean removeExhibitionByName(String exhibitionName) {
        System.out.println("Attempting to remove exhibition: " + exhibitionName);

        Connection connection = DatabaseConnection.connect();
        if (connection != null) {
            try {
                // Query pentru ștergerea expoziției
                String deleteQuery = "DELETE FROM Exhibition WHERE name_exhibition = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
                preparedStatement.setString(1, exhibitionName);

                // Execuția query-ului
                int rowsAffected = preparedStatement.executeUpdate();
                preparedStatement.close();
                connection.close();

                return rowsAffected > 0; // Returnează true dacă s-a șters ceva
            } catch (SQLException e) {
                System.out.println("Error removing exhibition: " + e.getMessage());
            }
        } else {
            System.out.println("Failed to connect to the database.");
        }
        return false; // Returnează false dacă există erori
    }



    public static Exhibition getExhibitionByName(String exhibitionName) {
        System.out.println("Searching for exhibition: '" + exhibitionName + "'...");

        Connection connection = DatabaseConnection.connect();
        if (connection != null) {
            try {
                // SQL query to retrieve the exhibition
                String selectQuery = "SELECT * FROM Exhibition WHERE name_exhibition = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
                preparedStatement.setString(1, exhibitionName);

                // Execute the query
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    // Create and return the Exhibition object
                    int idExhibition = resultSet.getInt("id_exhibition");
                    String name = resultSet.getString("name_exhibition");
                    int duration = resultSet.getInt("duration_exhibition");
                    int capacity = resultSet.getInt("capacity_exhibition");
                    java.sql.Date startDate = resultSet.getDate("startdate_exhibition");
                    java.sql.Date endDate = resultSet.getDate("enddate_exhibition");

                    // Return the populated Exhibition object
                    return new Exhibition(
                            idExhibition,
                            name,
                            duration,
                            capacity,
                            startDate,
                            endDate
                    );
                } else {
                    System.out.println("No exhibition found with the name '" + exhibitionName + "'.");
                }

                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println("Error retrieving exhibition: " + e.getMessage());
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

        return null; // Return null if no exhibition is found or an error occurs
    }



    public void saveExhibitionChanges() {
        Connection connection = DatabaseConnection.connect();

        if (connection != null) {
            try {
                // Query pentru actualizarea expoziției în baza de date
                String updateQuery = "UPDATE Exhibition SET name_exhibition = ?, duration_exhibition = ?, capacity_exhibition = ?, startdate_exhibition = ?, enddate_exhibition = ? WHERE id_exhibition = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);

                // Setarea parametrilor
                preparedStatement.setString(1, this.getExhibitionName());
                preparedStatement.setInt(2, this.getExhibitionDuration());
                preparedStatement.setInt(3, this.getMaxCapacity());
                preparedStatement.setDate(4, new java.sql.Date(this.getStartDate().getTime()));
                preparedStatement.setDate(5, new java.sql.Date(this.getEndDate().getTime()));
                preparedStatement.setInt(6, this.getID_exhibition());

                // Executarea interogării
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Exhibition updated successfully.");
                } else {
                    System.out.println("No exhibition was updated. Please check the ID.");
                }

                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println("Error saving exhibition changes: " + e.getMessage());
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
