import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import models.*;



public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Artwork artwork = new Artwork();
        Person person = new Person();
        Ticket ticket = new Ticket();
        Visitor visitor = new Visitor();

        System.out.println("                      Welcome to Gallery Museum");
        System.out.println("__________________________________________________________________________");
        System.out.println("              What would you like to see? Type your option:");
        System.out.println("1. Artwork");
        System.out.println("2. Person");
        System.out.println("3. Ticket");
        System.out.println("4. Visitor");

        String option;
        option = scanner.nextLine();

        if (option.equals("1")) {
            do {
                System.out.println("                MENU for Artworks");
                System.out.println("________________________________________________________");
                System.out.println("1. Display All Artworks in the database");
                System.out.println("2. Display All Non Exhibited artworks in the database");
                System.out.println("3. Display details of an artwork in the database");
                System.out.println("4. Add a new artwork");
                System.out.println("5. Delete an artwork");
                System.out.println("6. Change the title of an artwork");
                System.out.println("7. Change the description of an artwork");
                System.out.println("8. Change the year of an artwork");
                System.out.println("9. Change the artist of an artwork");
                System.out.println("10. Exhibit an artwork");
                System.out.println("11. Remove an artwork from exhibition");
                System.out.println("12. Display All Artworks in ArtworkList.txt");
                System.out.println("E. Exit");
                System.out.println("Please enter the option: ");

                option = scanner.nextLine();

                switch (option) {
                    case "1":
                        artwork.displayArtworks();
                        break;
                    case "2":
                        artwork.printNonExhibitedArtworks();
                        break;
                    case "3":
                        System.out.println("Enter the title of an artwork");
                        String title = scanner.nextLine();
                        artwork.printArtworkDetailsByTitle(title);
                        break;
                    case "4":
                        Connection connection = DatabaseConnection.connect();
                        System.out.println("Enter the details of an artwork");
                        System.out.println("Enter the id exhibition:");
                        int id = Integer.parseInt(scanner.nextLine());
                        System.out.println("Enter the title of an artwork");
                        title = scanner.nextLine();
                        System.out.println("Enter the description of an artwork");
                        String description = scanner.nextLine();
                        System.out.println("Enter the year of an artwork");
                        int year = Integer.parseInt(scanner.nextLine());
                        System.out.println("Enter the artist of an artwork");
                        String artist = scanner.nextLine();
                        System.out.println("Enter the status of an artwork");
                        boolean status = Boolean.parseBoolean(scanner.nextLine());
                        Artwork new_artwork = new Artwork(id, title, description, artist, year, status);
                        new_artwork.addArtworkToDatabase(connection);
                        break;
                    case "5":
                        System.out.println("Enter the title of an artwork you want to delete");
                        title = scanner.nextLine();
                        artwork.removeArtwork(title);
                        break;
                    case "6":
                        System.out.println("Enter the title of an artwork you want to change and the new title");
                        title = scanner.nextLine();
                        String newTitle = scanner.nextLine();
                        artwork.changeTitle(title, newTitle);
                        break;
                    case "7":
                        System.out.println("Enter the title of an artwork you want to change its description and the new description");
                        title = scanner.nextLine();
                        String newDescription = scanner.nextLine();
                        artwork.changeDescription(title, newDescription);
                        break;
                    case "8":
                        System.out.println("Enter the title of an artwork you want to change its year and the new year");
                        title = scanner.nextLine();
                        int newyear = Integer.parseInt(scanner.nextLine());
                        artwork.changeYear(title, newyear);
                        break;
                    case "9":
                        System.out.println("Enter the title of an artwork you want to change its artist and the new artist");
                        title = scanner.nextLine();
                        String newArtist = scanner.nextLine();
                        artwork.changeArtist(title, newArtist);
                        break;
                    case "10":
                        System.out.println("Enter the title of an artwork you want to exhibit");
                        title = scanner.nextLine();
                        artwork.exhibit(title);
                        break;
                    case "11":
                        System.out.println("Enter the title of an artwork you want to remove from exhibit");
                        title = scanner.nextLine();
                        artwork.removeFromExhibit(title);
                        break;
                    case "12":
                        artwork.ListArtworks();
                        break;
                    case "E":
                        break;
                }


            } while (!option.equals("E"));

        }

        if(option.equals("4"))
        {
            do{
                System.out.println("                MENU for Visitors");
                System.out.println("________________________________________________________");
                System.out.println("1. Display All Visitors in the database");
                System.out.println("2. Add visitor in the database");
                System.out.println("3. Delete visitor in the database");
                System.out.println("4. Display details of a visitor searched by ID");
                System.out.println("5. Display All Visitors in VisitorsList.txt");
                System.out.println("E. Exit");
                System.out.println("Please enter the option: ");

                option = scanner.nextLine();

                switch (option) {
                    case "1":
                        visitor.displayVisitors();
                        break;
                    case "2":
                        Connection connection = DatabaseConnection.connect();
                        System.out.println("Enter the details of a visitor");
                        System.out.println("Enter the id visitor:");
                        int id_v = Integer.parseInt(scanner.nextLine());
                        System.out.println("Enter the id person:");
                        int id_p = Integer.parseInt(scanner.nextLine());
                        System.out.println("Enter the id ticket:");
                        int id_t = Integer.parseInt(scanner.nextLine());
                        System.out.println("Enter the date of a visitor (yyyy-MM-dd):");
                        String dateInput = scanner.nextLine();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                        java.util.Date date = null;
                        try {
                            date = dateFormat.parse(dateInput); // Conversie din String în java.util.Date
                        } catch (ParseException e) {
                            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
                            return;
                        }
                       visitor.addVisitor(id_v, id_p, id_t, date);
                        break;
                    case "3":
                        System.out.println("Enter the id of a visitor you want to remove from the database");
                        int id= Integer.parseInt(scanner.nextLine());
                        visitor.deleteVisitor(id);
                        break;
                    case "4":
                        System.out.println("Enter the id of a visitor you want to see details of");
                        id= Integer.parseInt(scanner.nextLine());
                        visitor.printVisitorById(id);
                        break;
                    case "5":
                        visitor.listVisitors();
                        break;
                    case "E":
                        break;
                }
            }while (!option.equals("E"));

        }

        if(option.equals("2"))
        {
            do {
                System.out.println("                MENU for Persons");
                System.out.println("________________________________________________________");
                System.out.println("1. Display All Persons in the database");
                System.out.println("2. Add a new person in the database");
                System.out.println("3. Delete a person from the database");
                System.out.println("4. Display details of a person by full name");
                System.out.println("5. Change a person's first name");
                System.out.println("6. Change a person's last name");
                System.out.println("7. Change a person's age");
                System.out.println("8. Change a person's email");
                System.out.println("9. Change a person's gender");
                System.out.println("10. Change a person's phone number");
                System.out.println("11. Display All Persons in PersonsList.txt");
                System.out.println("E. Exit");
                System.out.println("Please enter the option: ");

                option = scanner.nextLine();

                switch (option) {
                    case "1":
                        person.printAllPersons();
                        break;
                    case "2":
                        System.out.println("Enter the details of the new person:");
                        System.out.println("First name:");
                        String firstName = scanner.nextLine();
                        System.out.println("Last name:");
                        String lastName = scanner.nextLine();
                        System.out.println("Age:");
                        int age = Integer.parseInt(scanner.nextLine());
                        System.out.println("Gender:");
                        String gender = scanner.nextLine();
                        System.out.println("Email:");
                        String email = scanner.nextLine();
                        System.out.println("Phone number:");
                        String phoneNumber = scanner.nextLine();
                        person.addPerson(firstName, lastName, age, gender, email, phoneNumber);
                        break;
                    case "3":
                        System.out.println("Enter the first and last name of the person to delete:");
                        System.out.println("First name:");
                        firstName = scanner.nextLine();
                        System.out.println("Last name:");
                        lastName = scanner.nextLine();
                        person.removePerson(firstName, lastName);
                        break;
                    case "4":
                        System.out.println("Enter the first and last name of the person to view:");
                        System.out.println("First name:");
                        firstName = scanner.nextLine();
                        System.out.println("Last name:");
                        lastName = scanner.nextLine();
                        person.printPersonByFirstName(firstName, lastName);
                        break;
                    case "5":
                        System.out.println("Enter the current first name and the new first name:");
                        System.out.println("Current first name:");
                        String oldFirstName = scanner.nextLine();
                        System.out.println("New first name:");
                        String newFirstName = scanner.nextLine();
                        person.changeFirstName(oldFirstName, newFirstName);
                        break;
                    case "6":
                        System.out.println("Enter the current last name and the new last name:");
                        System.out.println("Current last name:");
                        String oldLastName = scanner.nextLine();
                        System.out.println("New last name:");
                        String newLastName = scanner.nextLine();
                        person.changeLastName(oldLastName, newLastName);
                        break;
                    case "7":
                        System.out.println("Enter the first and last name of the person and the new age:");
                        System.out.println("First name:");
                        firstName = scanner.nextLine();
                        System.out.println("Last name:");
                        lastName = scanner.nextLine();
                        System.out.println("New age:");
                        int newAge = Integer.parseInt(scanner.nextLine());
                        person.changeAge(firstName, lastName, newAge);
                        break;
                    case "8":
                        System.out.println("Enter the first and last name of the person and the new email:");
                        System.out.println("First name:");
                        firstName = scanner.nextLine();
                        System.out.println("Last name:");
                        lastName = scanner.nextLine();
                        System.out.println("New email:");
                        String newEmail = scanner.nextLine();
                        person.changeEmail(firstName, lastName, newEmail);
                        break;
                    case "9":
                        System.out.println("Enter the first and last name of the person and the new gender:");
                        System.out.println("First name:");
                        firstName = scanner.nextLine();
                        System.out.println("Last name:");
                        lastName = scanner.nextLine();
                        System.out.println("New gender:");
                        String newGender = scanner.nextLine();
                        person.changeGender(firstName, lastName, newGender);
                        break;
                    case "10":
                        System.out.println("Enter the first and last name of the person and the new phone number:");
                        System.out.println("First name:");
                        firstName = scanner.nextLine();
                        System.out.println("Last name:");
                        lastName = scanner.nextLine();
                        System.out.println("New phone number:");
                        phoneNumber = scanner.nextLine();
                        person.changePhoneNumber(firstName, lastName, phoneNumber);
                        break;
                    case "E":
                        System.out.println("Exiting the program...");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            } while (!option.equals("E"));

        }

        if(option.equals("3")){
            do {
                System.out.println("                MENU for Tickets");
                System.out.println("________________________________________________________");
                System.out.println("1. Display All Tickets in the database");
                System.out.println("2. Add a new ticket");
                System.out.println("3. Remove a ticket");
                System.out.println("4. Change ticket type");
                System.out.println("5. Validate a ticket");
                System.out.println("6. Invalidate a ticket");
                System.out.println("E. Exit");
                System.out.println("Please enter the option: ");

                option = scanner.nextLine();

                switch (option) {
                    case "1":
                        ticket.displayTickets();
                        break;
                    case "2":
                        System.out.println("Enter the details of the new ticket:");
                        System.out.println("Ticket ID:");
                        String ticketId = scanner.nextLine();
                        System.out.println("Ticket Type:");
                        String ticketType = scanner.nextLine();
                        System.out.println("Is the ticket valid? (true/false):");
                        boolean isValid = Boolean.parseBoolean(scanner.nextLine());
                        ticket.addTicket(ticketId, ticketType, isValid);
                        System.out.println("Ticket added successfully.");
                        break;
                    case "3":
                        System.out.println("Enter the ID of the ticket to remove:");
                        ticketId = scanner.nextLine();
                        ticket.removeTicket(ticketId);
                        System.out.println("Ticket removed successfully.");
                        break;
                    case "4":
                        System.out.println("Enter the ID of the ticket to change the type:");
                        ticketId = scanner.nextLine();
                        System.out.println("Enter the new ticket type:");
                        String newTicketType = scanner.nextLine();
                        ticket.changeTicketType(ticketId, newTicketType);
                        System.out.println("Ticket type updated successfully.");
                        break;
                    case "5":
                        System.out.println("Enter the ID of the ticket to validate:");
                        ticketId = scanner.nextLine();
                        ticket.validateTicket(ticketId);
                        System.out.println("Ticket validated successfully.");
                        break;
                    case "6":
                        System.out.println("Enter the ID of the ticket to invalidate:");
                        ticketId = scanner.nextLine();
                        ticket.invalidateTicket(ticketId);
                        System.out.println("Ticket invalidated successfully.");
                        break;
                    case "E":
                        System.out.println("Exiting the program...");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            } while (!option.equals("E"));
        }
    }
}