package models;
import interfaces.PersonActions;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Person implements PersonActions {



    // Attributes
    private int ID_person;
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private String email;
    private String phoneNumber;


    // Constructor
    public Person(int ID_person, String firstName, String lastName, int age, String gender, String email, String phoneNumber) {
        this.ID_person = ID_person;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Person() {

    }



    // Getters and Setters
    public int getID_person() {
        return ID_person;
    }

    public void setID_person(int idPerson) {
        this.ID_person = idPerson;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }



    // Methods
    public static Person getPersonById(int personId) {
        System.out.println("Searching for person with ID: '" + personId + "'...");

        Connection connection = DatabaseConnection.connect();
        if (connection != null) {
            try {
                String selectQuery = "SELECT * FROM Person WHERE id_person = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
                preparedStatement.setInt(1, personId);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    int id_person = resultSet.getInt("id_person");
                    String firstName = resultSet.getString("first_name");
                    String lastName = resultSet.getString("last_name");
                    int age = resultSet.getInt("age");
                    String gender = resultSet.getString("gender");
                    String email = resultSet.getString("email");
                    String phoneNumber = resultSet.getString("phone_number");

                    Person person = new Person(
                            id_person,
                            firstName,
                            lastName,
                            age,
                            gender,
                            email,
                            phoneNumber
                    );

                    resultSet.close();
                    preparedStatement.close();
                    connection.close();
                    return person;
                } else {
                    System.out.println("No person found with ID: '" + personId + "'.");
                }

                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println("Error retrieving person: " + e.getMessage());
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

        return null;
    }



    // Function to display all persons from the database
    @Override
    public void printAllPersons() {
        System.out.println("Fetching all persons from the database...");

        // Connect to the database
        Connection connection = DatabaseConnection.connect();
        if (connection != null) {
            try {
                // SQL query to fetch all records from the Person table
                String query = "SELECT * FROM Person";
                PreparedStatement preparedStatement = connection.prepareStatement(query);

                // Execute the query
                ResultSet resultSet = preparedStatement.executeQuery();

                // Check if the result set has any records
                if (!resultSet.isBeforeFirst()) {
                    System.out.println("No persons found in the database.");
                } else {
                    System.out.println("Persons in the database:");
                    System.out.println("-----------------------------------------");

                    // Loop through and display each record
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id_person");
                        String firstName = resultSet.getString("first_name");
                        String lastName = resultSet.getString("last_name");
                        int age = resultSet.getInt("age");
                        String gender = resultSet.getString("gender");
                        String email = resultSet.getString("email");
                        String phoneNumber = resultSet.getString("phone_number");

                        System.out.println("ID: " + id);
                        System.out.println("First Name: " + firstName);
                        System.out.println("Last Name: " + lastName);
                        System.out.println("Age: " + age);
                        System.out.println("Gender: " + gender);
                        System.out.println("Email: " + email);
                        System.out.println("Phone Number: " + phoneNumber);
                        System.out.println("-----------------------------------------");
                    }
                }

                // Close resources
                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println("Error retrieving persons from the database: " + e.getMessage());
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



    // Function to fetch and display details of a person by their first name
    @Override
    public void printPersonByFirstName(String firstName, String secondName) {
        System.out.println("Searching for person with first name: " + firstName+ " "+ secondName);

        // Connect to the database
        Connection connection = DatabaseConnection.connect();
        if (connection != null) {
            try {
                // SQL query to search for a person by first name
                String query = "SELECT * FROM Person WHERE first_name = ? AND last_name = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, firstName);
                preparedStatement.setString(2, secondName);

                // Execute the query
                ResultSet resultSet = preparedStatement.executeQuery();

                // Check if the person exists
                if (!resultSet.isBeforeFirst()) {
                    System.out.println("No person found with the first name '" + firstName +" "+ secondName + "'.");
                } else {
                    System.out.println("Person details:");
                    System.out.println("-----------------------------------------");

                    // Display the person's details
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id_person");
                        String lastName = resultSet.getString("last_name");
                        int age = resultSet.getInt("age");
                        String gender = resultSet.getString("gender");
                        String email = resultSet.getString("email");
                        String phoneNumber = resultSet.getString("phone_number");

                        System.out.println("ID: " + id);
                        System.out.println("First Name: " + firstName);
                        System.out.println("Last Name: " + lastName);
                        System.out.println("Age: " + age);
                        System.out.println("Gender: " + gender);
                        System.out.println("Email: " + email);
                        System.out.println("Phone Number: " + phoneNumber);
                        System.out.println("-----------------------------------------");
                    }
                }

                // Close resources
                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println("Error retrieving person from the database: " + e.getMessage());
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



    // Add a person to my database
    @Override
    public void addPerson(String firstName, String secondName, int age, String gender, String email, String phoneNumber) {
        System.out.println("Attempting to add a new person: " + firstName + " "+secondName);

        // Connect to the database
        Connection connection = DatabaseConnection.connect();
        if (connection != null) {
            try {
                // SQL query to insert a new exhibition
                String insertQuery = "INSERT INTO Person (first_name, last_name, age, gender, email, phone_number) VALUES (?, ?, ?, ?, ?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

                // Setting parameters
                preparedStatement.setString(1, firstName);
                preparedStatement.setString(2, secondName);
                preparedStatement.setInt(3, age);
                preparedStatement.setString(4, gender);
                preparedStatement.setString(5, email);
                preparedStatement.setString(6, phoneNumber);

                // Execute the insert operation
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Person '" + firstName + " " + secondName + "' was successfully added to the database.");
                } else {
                    System.out.println("Failed to add the person.");
                }

                preparedStatement.close();

            } catch (SQLException e) {
                System.out.println("Error adding person: " + e.getMessage());
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



    // Remove an existential person from my database
    @Override
    public void removePerson(String firstName, String secondName) {
        System.out.println("Attempting to remove the person with the name: '" + firstName+ " "+ secondName + "'.");

        Connection connection = DatabaseConnection.connect();
        if (connection != null) {
            try {
                String deleteQuery = "DELETE FROM Person WHERE first_name = ? AND last_name = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
                preparedStatement.setString(1, firstName);
                preparedStatement.setString(2, secondName);

                // Execute the delete query
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("The person with the name '" + firstName+ " " + secondName + "' has been removed from the database.");
                } else {
                    System.out.println("No person found with the name '" + firstName+ " " + secondName + "'.");
                }

                // Close the prepared statement
                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println("Error deleting person from the database: " + e.getMessage());
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



    // Change a persons firstName in my database
    @Override
    public void changeFirstName(String oldFirstName, String newFirstName) {
        System.out.println("Attempting to change the first name of a person from '" + oldFirstName + "' to '" + newFirstName + "'.");

        // Connecting to the database
        Connection connection = DatabaseConnection.connect();
        if (connection != null) {
            try {
                // SQL query to update the title of an artwork
                String updateQuery = "UPDATE Person SET first_name = ? WHERE first_name = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
                preparedStatement.setString(1, newFirstName);
                preparedStatement.setString(2, oldFirstName);

                // Execute the update query
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("The name of the person  '" + oldFirstName+ "' has been successfully changed to '" + newFirstName + "'.");
                } else {
                    System.out.println("No person found with the name '" + oldFirstName + "'.");
                }

                // Close the prepared statement
                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println("Error updating the persons first name in the database: " + e.getMessage());
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



    // Change a persons lastName in my database
    @Override
    public void changeLastName(String oldLastName, String newLastName) {
        System.out.println("Attempting to change the first name of a person from '" + oldLastName + "' to '" + newLastName + "'.");

        // Connecting to the database
        Connection connection = DatabaseConnection.connect();
        if (connection != null) {
            try {
                String updateQuery = "UPDATE Person SET last_name = ? WHERE last_name = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
                preparedStatement.setString(1, newLastName);
                preparedStatement.setString(2, oldLastName);

                // Execute the update query
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("The name of the person  '" + oldLastName+ "' has been successfully changed to '" + newLastName + "'.");
                } else {
                    System.out.println("No person found with the name '" + oldLastName + "'.");
                }

                // Close the prepared statement
                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println("Error updating the persons last name in the database: " + e.getMessage());
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



    // Change a persons age in my database
    @Override
    public void changeAge(String firstName, String lastName, int newAge) {
        System.out.println("Attempting to change the age of '" + firstName + " "+ lastName +  "'.");

        // Connecting to the database
        Connection connection = DatabaseConnection.connect();
        if (connection != null) {
            try {
                String updateQuery = "UPDATE Person SET age = ? WHERE first_name=? AND last_name = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
                preparedStatement.setInt(1, newAge);
                preparedStatement.setString(2,firstName);
                preparedStatement.setString(3, lastName);

                // Execute the update query
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("The age of the person  '" + firstName+ " "+lastName+ "' has been successfully changed to '" + newAge + "'.");
                } else {
                    System.out.println("No person found with the name '" + firstName + " "+ lastName+ "'.");
                }

                // Close the prepared statement
                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println("Error updating the persons age in the database: " + e.getMessage());
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



    // Changing email
    @Override
    public void changeEmail(String firstName, String lastName, String newEmail) {
        System.out.println("Attempting to change the email of '" + firstName + " " + lastName + "'.");

        // Connecting to the database
        Connection connection = DatabaseConnection.connect();
        if (connection != null) {
            try {
                // SQL query to update the email
                String updateQuery = "UPDATE Person SET email = ? WHERE firstName = ? AND lastName = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);

                // Set the new values in the query
                preparedStatement.setString(1, newEmail);
                preparedStatement.setString(2, firstName);
                preparedStatement.setString(3, lastName);

                // Execute the update query
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("The email of the person '" + firstName + " " + lastName + "' has been successfully changed to '" + newEmail + "'.");
                } else {
                    System.out.println("No person found with the name '" + firstName + " " + lastName + "'.");
                }

                // Close the prepared statement
                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println("Error updating the person's email in the database: " + e.getMessage());
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



    // Change gender of person
    @Override
    public void changeGender(String firstName, String lastName, String newGender) {
        System.out.println("Attempting to change the gender of '" + firstName + " " + lastName + "' to '" + newGender + "'.");

        // Connecting to the database
        Connection connection = DatabaseConnection.connect();
        if (connection != null) {
            try {
                // SQL query to update the gender
                String updateQuery = "UPDATE Person SET gender = ? WHERE firstName = ? AND lastName = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);

                // Set the new values in the query
                preparedStatement.setString(1, newGender);
                preparedStatement.setString(2, firstName);
                preparedStatement.setString(3, lastName);

                // Execute the update query
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("The gender of the person '" + firstName + " " + lastName + "' has been successfully changed to '" + newGender + "'.");
                } else {
                    System.out.println("No person found with the name '" + firstName + " " + lastName + "'.");
                }

                // Close the prepared statement
                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println("Error updating the person's gender in the database: " + e.getMessage());
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



    // Change phone number
    @Override
    public void changePhoneNumber(String firstName, String lastName, String phoneNumber) {
        System.out.println("Attempting to change the phone number of '" + firstName + " " + lastName + "'.");

        // Connecting to the database
        Connection connection = DatabaseConnection.connect();
        if (connection != null) {
            try {
                // SQL query to update the email
                String updateQuery = "UPDATE Person SET email = ? WHERE firstName = ? AND lastName = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);

                // Set the new values in the query
                preparedStatement.setString(1, phoneNumber);
                preparedStatement.setString(2, firstName);
                preparedStatement.setString(3, lastName);

                // Execute the update query
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("The phone number of the person '" + firstName + " " + lastName + "' has been successfully changed to '" + phoneNumber + "'.");
                } else {
                    System.out.println("No person found with the name '" + firstName + " " + lastName + "'.");
                }

                // Close the prepared statement
                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println("Error updating the person's phone number in the database: " + e.getMessage());
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
