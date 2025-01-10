package JavaFX;

import javafx.geometry.Insets;
import models.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.scene.control.*;
import javafx.scene.layout.*;


public class MuseumApp extends Application {

    private Scene mainScene;
    private double windowWidth = 600;
    private double windowHeight = 400;
    private Museum museum;
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        // Setting the museum
        museum = new Museum("Uffizi Gallery", "Florence, Italy", 1000000);

        // Creating primary scene
        mainScene = createMainScene();

        primaryStage.setTitle("Museum App");
        primaryStage.setScene(mainScene);
        primaryStage.setWidth(windowWidth);
        primaryStage.setHeight(windowHeight);
        primaryStage.show();
    }

    private Scene createMainScene() {
        // Background
        String imageUrl = "file:G:/Jubii/FACULTATE/ANU II SEM 1/MIP/Ufizzi Gallery Museum/src/main/resources/images/9.1.jpg";
        Image image = new Image(imageUrl);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);


        // Labels
        Label messageLabel1 = new Label("Uffizi Gallery");
        messageLabel1.setStyle("-fx-font-size: 88px; -fx-font-weight: bold; -fx-text-fill:#aea6a6; -fx-alignment: center;");
        messageLabel1.setAlignment(Pos.CENTER);

        Label messageLabel2 = new Label("Italian Renaissance Art");
        messageLabel2.setStyle("-fx-font-size: 40px; -fx-font-weight: bold; -fx-text-fill: #aea6a6; -fx-alignment: center;");
        messageLabel2.setAlignment(Pos.CENTER);


        // Button
        Button menuButton = new Button("View");
        menuButton.setStyle("-fx-font-size: 28px; -fx-background-color: #44403c; -fx-text-fill: #aea6a6;");
        menuButton.setOnAction(event -> {
            saveWindowDimensions();
            primaryStage.setScene(createMenuScene());
        });


        // Layout
        VBox mainLayout = new VBox(20);
        mainLayout.setAlignment(Pos.CENTER_LEFT);
        mainLayout.setPadding(new Insets(0, 0, 0, 50));
        mainLayout.setBackground(background);
        mainLayout.getChildren().addAll(messageLabel1, messageLabel2, menuButton);

        return new Scene(mainLayout, windowWidth, windowHeight);
    }

    private Scene createMenuScene() {
        // Background
        String imageUrl = "file:G:/Jubii/FACULTATE/ANU II SEM 1/MIP/Ufizzi Gallery Museum/src/main/resources/images/9.1.jpg";
        Image image = new Image(imageUrl);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);


        // Buttons
        Button option1Button = new Button("View Museum Details");
        Button option2Button = new Button("View Exhibitions");
        Button option3Button = new Button("View Visitors");
        Button option4Button = new Button("View Tickets");
        Button backButton = new Button("Back to Main");


        // Styles for buttons
        option1Button.setStyle("-fx-font-size: 20px; -fx-background-color: #44403c; -fx-text-fill: #aea6a6;");
        option2Button.setStyle("-fx-font-size: 20px; -fx-background-color: #44403c; -fx-text-fill: #aea6a6;");
        option3Button.setStyle("-fx-font-size: 20px; -fx-background-color: #44403c; -fx-text-fill: #aea6a6;");
        option4Button.setStyle("-fx-font-size: 20px; -fx-background-color: #44403c; -fx-text-fill: #aea6a6;");
        backButton.setStyle("-fx-font-size: 20px; -fx-background-color: #292828; -fx-text-fill: #aea6a6;");


        // Functionality of buttons
        option1Button.setOnAction(event -> {
            saveWindowDimensions();
            primaryStage.setScene(createMuseumDetailsScene());
        });
        backButton.setOnAction(event -> {
            saveWindowDimensions();
            primaryStage.setScene(mainScene);
        });
        option2Button.setOnAction(event -> {
            saveWindowDimensions();
            showExhibition(primaryStage);
        });
        option3Button.setOnAction(event -> {
            saveWindowDimensions();
            showVisitors(primaryStage);
        });
        option4Button.setOnAction(event -> {
            saveWindowDimensions();
            showTickets(primaryStage);
        });


        // Layout
        VBox menuLayout = new VBox(20);
        menuLayout.setAlignment(Pos.CENTER_LEFT);
        menuLayout.setPadding(new Insets(0, 0, 0, 50));
        menuLayout.setBackground(background);
        menuLayout.getChildren().addAll(option1Button, option2Button,option3Button,option4Button,backButton);

        return new Scene(menuLayout, windowWidth, windowHeight);
    }

    private Scene createMuseumDetailsScene() {
        // Background
        String imageUrl = "file:G:/Jubii/FACULTATE/ANU II SEM 1/MIP/Ufizzi Gallery Museum/src/main/resources/images/4.jpg";
        Image image = new Image(imageUrl);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);


        // Label
        String details = museum.getMuseumDetails();
        Label museumLabel = new Label("Established in 1584\n" + details);
        museumLabel.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: white; -fx-alignment: center;");


        // Buttons
        Button scheduleButton = new Button("Display Museum Schedule");
        scheduleButton.setStyle("-fx-font-size: 18px; -fx-background-color: #988268; -fx-text-fill: white;");
        scheduleButton.setOnAction(event -> {
            saveWindowDimensions();
            showSchedule(primaryStage);
                });


        Button backButton = new Button("Back to Menu");
        backButton.setStyle("-fx-font-size: 18px; -fx-background-color: #988268; -fx-text-fill: white;");
        backButton.setOnAction(event -> {
            saveWindowDimensions();
            primaryStage.setScene(createMenuScene());
        });

        Button mainButton = new Button("Back to Main");
        mainButton.setStyle("-fx-font-size: 18px; -fx-background-color: #988268; -fx-text-fill: white;");
        mainButton.setOnAction(event -> {
            saveWindowDimensions();
            primaryStage.setScene(createMainScene());
        });


        // Layout
        VBox detailsLayout = new VBox(20);
        detailsLayout.setAlignment(Pos.CENTER);
        detailsLayout.setBackground(background);
        detailsLayout.getChildren().addAll(museumLabel, scheduleButton, backButton, mainButton);

        return new Scene(detailsLayout, windowWidth, windowHeight);
    }

    private void showSchedule(Stage primaryStage) {

        // Background
        String imageUrl = "file:G:/Jubii/FACULTATE/ANU II SEM 1/MIP/Ufizzi Gallery Museum/src/main/resources/images/4.jpg";
        Image image = new Image(imageUrl);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);


        // Label
        String schedule = museum.getMuseumScheduleString();
        Label titleLabel = new Label("Museum Schedule:");
        titleLabel.setStyle("-fx-font-size: 48px; -fx-font-weight: bold; -fx-text-fill: white;");

        Label scheduleLabel = new Label(schedule);
        scheduleLabel.setStyle("-fx-font-size: 28px; -fx-font-weight: italic; -fx-text-fill: #cdb89e;");


        // Buttons
        Button backButton = new Button("Back to Main");
        backButton.setStyle("-fx-font-size: 18px; -fx-background-color: #988268; -fx-text-fill: white;");
        backButton.setOnAction(event -> {
                    saveWindowDimensions();
                    primaryStage.setScene(mainScene);
                });

        Button menuButton = new Button("Back to Menu");
        menuButton.setStyle("-fx-font-size: 18px; -fx-background-color: #988268; -fx-text-fill: white;");
        menuButton.setOnAction(event -> {
            saveWindowDimensions();
            primaryStage.setScene(createMenuScene());
        });


        // Layout
        VBox scheduleLayout = new VBox(20);
        scheduleLayout.setAlignment(Pos.CENTER);
        scheduleLayout.setBackground(background);
        scheduleLayout.getChildren().addAll(titleLabel,scheduleLabel, menuButton,backButton);


        // Scene
        Scene scheduleScene = new Scene(scheduleLayout, 600, 400);
        primaryStage.setScene(scheduleScene);
    }

private void showExhibition(Stage primaryStage) {
    // Setting background
    String imageUrl = "file:G:/Jubii/FACULTATE/ANU II SEM 1/MIP/Ufizzi Gallery Museum/src/main/resources/images/9.1.jpg";
    Image image = new Image(imageUrl);
    BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
    Background background = new Background(backgroundImage);


    // Layout
    BorderPane exhibitionsLayout = new BorderPane();
    exhibitionsLayout.setBackground(background);


    // Search bar
    TextField searchField = new TextField();
    searchField.setPromptText("Search Exhibition by Name");
    searchField.setStyle("-fx-font-size: 16px; -fx-background-color: #a29b9b;-fx-text-fill: black;");
    Button searchButton = new Button("Search");
    searchButton.setStyle("-fx-font-size: 16px; -fx-background-color: #44403c; -fx-text-fill: white;");


    // Layout search bar
    HBox searchBar = new HBox(30, searchField, searchButton);
    searchBar.setAlignment(Pos.TOP_LEFT);
    searchBar.setStyle("-fx-padding: 15;");
    exhibitionsLayout.setTop(searchBar);


    // Buttons
    Button addButton = new Button("Add");
    Button removeButton = new Button("Remove");
    Button editButton = new Button("Edit");
    Button menuButton = new Button("Menu");

    addButton.setStyle("-fx-font-size: 16px; -fx-background-color: #44403c; -fx-text-fill: white;");
    removeButton.setStyle("-fx-font-size: 16px; -fx-background-color: #44403c; -fx-text-fill: white;");
    editButton.setStyle("-fx-font-size: 16px; -fx-background-color: #44403c; -fx-text-fill: white;");
    menuButton.setStyle("-fx-font-size: 16px; -fx-background-color: #44403c; -fx-text-fill: white;");


    // Layout buttons
    HBox actionButtons = new HBox(30, addButton, removeButton, editButton, menuButton);
    actionButtons.setAlignment(Pos.TOP_LEFT);
    actionButtons.setStyle("-fx-padding: 15;");


    VBox searchSection = new VBox(30, searchBar, actionButtons);
    searchSection.setStyle("-fx-padding: 15;");
    exhibitionsLayout.setTop(searchSection);


    // Expositions list
    VBox exhibitionsList = new VBox(30);
    exhibitionsList.setStyle("-fx-padding: 30;");
    exhibitionsList.setAlignment(Pos.TOP_LEFT);
    exhibitionsLayout.setLeft(exhibitionsList);
    displayAllExhibitions(exhibitionsList);


    searchButton.setOnAction(event -> {
        String searchText = searchField.getText().trim();
        if (!searchText.isEmpty()) {
            displaySearchedExhibition(exhibitionsList, searchText);

        }
        else displayAllExhibitions(exhibitionsList);
    });


    // Scene
    Scene exhibitionsScene = new Scene(exhibitionsLayout, windowWidth, windowHeight);
    primaryStage.setScene(exhibitionsScene);


    // Functionality of buttons
    addButton.setOnAction(event -> showAddExhibitionScene(primaryStage));
    removeButton.setOnAction(event -> showRemoveExhibitionDialog(exhibitionsList));
    menuButton.setOnAction(event -> {
        saveWindowDimensions();
        primaryStage.setScene(createMenuScene());
    });
    editButton.setOnAction(event -> editExposition());

}
    private void displayAllExhibitions(VBox exhibitionsList) {
        exhibitionsList.getChildren().clear();

        // Exposition list
        List<Exhibition> exhibitions = Exhibition.getAllExhibitions();

        if (exhibitions.isEmpty()) {
            Label emptyMessage = new Label("No exhibitions available.");
            exhibitionsList.getChildren().add(emptyMessage);
        } else {
            for (Exhibition exhibition : exhibitions) {
                Label exhibitionLabel = new Label(exhibition.getExhibitionName());
                exhibitionLabel.setStyle("-fx-font-size: 15px; -fx-text-fill: white;");
                exhibitionsList.getChildren().add(exhibitionLabel);
            }
        }
    }

    private void displaySearchedExhibition(VBox exhibitionsList, String searchText) {
        exhibitionsList.getChildren().clear();

        Exhibition exhibition = Exhibition.getExhibitionByName(searchText);

        if (exhibition != null) {
            Label details = new Label("Details of " + exhibition.getExhibitionName() + ":");
            details.setStyle("-fx-font-size: 32px; -fx-font-weight: bold;");

            Label capacity = new Label("Capacity: " + exhibition.getMaxCapacity());
            Label duration = new Label("Duration: " + exhibition.getExhibitionDuration() + " days");
            Label startDate = new Label("Start Date: " + exhibition.getStartDate());
            Label endDate = new Label("End Date: " + exhibition.getEndDate());

            VBox detailsBox = new VBox(30, details, capacity, duration, startDate, endDate);
            detailsBox.setStyle("-fx-padding: 90; -fx-background-color: rgba(255,255,255,0.8);-fx-font-size: 18px;");
            exhibitionsList.getChildren().add(detailsBox);
        } else {
            Label notFoundMessage = new Label("No exhibition found with the name: " + searchText);
            notFoundMessage.setStyle("-fx-font-size: 18px; -fx-text-fill: white;");
            exhibitionsList.getChildren().add(notFoundMessage);
        }
    }

    private void showAddExhibitionScene(Stage primaryStage) {
        // Background
        String imageUrl = "file:G:/Jubii/FACULTATE/ANU II SEM 1/MIP/Ufizzi Gallery Museum/src/main/resources/images/9.1.jpg";
        Image image = new Image(imageUrl);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);


        // Layout
        VBox addLayout = new VBox(10);
        addLayout.setAlignment(Pos.TOP_LEFT);
        addLayout.setBackground(background);
        addLayout.setStyle("-fx-padding: 20;");


        // Fields
        TextField nameField = new TextField();
        nameField.setPromptText("Exhibition Name");
        nameField.setPrefWidth(400);
        nameField.setMaxWidth(400);

        TextField durationField = new TextField();
        durationField.setPromptText("Duration (days)");
        durationField.setPrefWidth(400);
        durationField.setMaxWidth(400);

        TextField capacityField = new TextField();
        capacityField.setPromptText("Capacity");
        capacityField.setPrefWidth(400);
        capacityField.setMaxWidth(400);

        TextField startDateField = new TextField();
        startDateField.setPromptText("Start Date (YYYY-MM-DD)");
        startDateField.setMaxWidth(400);
        startDateField.setPrefWidth(400);

        TextField endDateField = new TextField();
        endDateField.setPromptText("End Date (YYYY-MM-DD)");
        endDateField.setPrefWidth(400);
        endDateField.setMaxWidth(400);


        // Save button
        Button saveButton = new Button("Save");
        saveButton.setStyle("-fx-font-size: 16px; -fx-background-color: #44403c; -fx-text-fill: white;");


        // Back button
        Button backButton = new Button("Back to Exhibitions");
        backButton.setStyle("-fx-font-size: 16px; -fx-background-color: #44403c; -fx-text-fill: white;");
        backButton.setOnAction(event -> showExhibition(primaryStage));


        // Functionality of Save
        saveButton.setOnAction(event -> {
            String name = nameField.getText().trim();
            int duration = Integer.parseInt(durationField.getText().trim());
            int capacity = Integer.parseInt(capacityField.getText().trim());
            String startDateText = startDateField.getText().trim();
            String endDateText = endDateField.getText().trim();

            if (!startDateText.matches("\\d{4}-\\d{2}-\\d{2}") || !endDateText.matches("\\d{4}-\\d{2}-\\d{2}")) {
                showErrorDialog("Invalid date format! Use YYYY-MM-DD.");
                return;
            }

            java.sql.Date startDate = java.sql.Date.valueOf(startDateText);
            java.sql.Date endDate = java.sql.Date.valueOf(endDateText);

            if (name.isEmpty() || startDate == null || endDate == null) {
                showErrorDialog("All fields must be filled!");
                return;
            }

            boolean success = Exhibition.addExhibition(name, duration, capacity, startDate, endDate);

            if (success) {
                showConfirmationDialog("Exhibition added successfully!");
                showExhibition(primaryStage);
            } else {
                showErrorDialog("Failed to add exhibition. Please try again.");
            }
        });


        // Layout
        addLayout.getChildren().addAll(
                new Label("Add New Exhibition"),
                nameField, durationField, capacityField, startDateField, endDateField,
                saveButton, backButton
        );


        // Scene
        Scene addScene = new Scene(addLayout, windowWidth, windowHeight);
        primaryStage.setScene(addScene);
    }

    private void showRemoveExhibitionDialog(VBox exhibitionsList) {
        // Pop - Window
        Stage removeStage = new Stage();
        removeStage.setTitle("Remove Exhibition");

        VBox layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-padding: 20; -fx-background-color: #f4f4f4;");


        // Label
        Label instructionLabel = new Label("Enter the name of the exhibition to remove:");
        instructionLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");


        // Field
        TextField nameField = new TextField();
        nameField.setPromptText("Exhibition Name");
        nameField.setStyle("-fx-font-size: 14px;");


        // Remove button
        Button confirmButton = new Button("Remove");
        confirmButton.setStyle("-fx-background-color: #44403c; -fx-text-fill: white; -fx-font-size: 14px;");


        //  Cancel button
        Button cancelButton = new Button("Cancel");
        cancelButton.setStyle("-fx-background-color: #44403c; -fx-text-fill: white; -fx-font-size: 14px;");
        cancelButton.setOnAction(e -> removeStage.close()); // closing the window


        // Funcționality of Remove
        confirmButton.setOnAction(e -> {
            String exhibitionName = nameField.getText().trim();
            if (exhibitionName.isEmpty()) {
                showErrorDialog("Exhibition name cannot be empty!");
            } else {
                boolean success = Exhibition.removeExhibitionByName(exhibitionName);
                if (success) {
                    showConfirmationDialog("Exhibition removed successfully!");
                    displayAllExhibitions(exhibitionsList); // renew the list
                    removeStage.close();
                } else {
                    showErrorDialog("Failed to remove the exhibition. Check the name and try again.");
                }
            }
        });


        // Layout
        HBox buttonBox = new HBox(10, confirmButton, cancelButton);
        buttonBox.setAlignment(Pos.CENTER);

        layout.getChildren().addAll(instructionLabel, nameField, buttonBox);


        // Scene
        Scene scene = new Scene(layout, 400, 200);
        removeStage.setScene(scene);
        removeStage.show();
    }

    private void editExposition(){
        // Background
        String imageUrl = "file:G:/Jubii/FACULTATE/ANU II SEM 1/MIP/Ufizzi Gallery Museum/src/main/resources/images/9.1.jpg";
        Image image = new Image(imageUrl);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);

        TextField searchField = new TextField();
        searchField.setPromptText("Edit Exhibition by Name");
        searchField.setStyle("-fx-font-size: 16px; -fx-background-color: #a29b9b;-fx-text-fill: black;");
        Button searchButton = new Button("Edit");
        searchButton.setStyle("-fx-font-size: 16px; -fx-background-color: #44403c; -fx-text-fill: white;");


        // Layout search bar
        HBox searchBar = new HBox(30, searchField, searchButton);
        searchBar.setAlignment(Pos.TOP_LEFT);
        searchBar.setStyle("-fx-padding: 15;");


        // Fields
        TextField nameField = new TextField();
        nameField.setPromptText("Edit Exhibition Name");
        nameField.setPrefWidth(400); // Setăm lățimea preferată
        nameField.setMinWidth(400);

        TextField durationField = new TextField();
        durationField.setPromptText("Edit Duration (days)");
        durationField.setPrefWidth(400);
        durationField.setMinWidth(400);

        TextField capacityField = new TextField();
        capacityField.setPromptText("Edit Capacity");
        capacityField.setPrefWidth(400);
        capacityField.setMinWidth(400);

        TextField startDateField = new TextField();
        startDateField.setPromptText("Edit Start Date (YYYY-MM-DD)");
        startDateField.setPrefWidth(400);
        startDateField.setMinWidth(400);

        TextField endDateField = new TextField();
        endDateField.setPromptText("Edit End Date (YYYY-MM-DD)");
        endDateField.setPrefWidth(400);
        endDateField.setMinWidth(400);

        nameField.setMaxWidth(400);
        durationField.setMaxWidth(400);
        capacityField.setMaxWidth(400);
        startDateField.setMaxWidth(400);
        endDateField.setMaxWidth(400);


        // Search button functionality
        searchButton.setOnAction(event -> {
            String exhibitionName = searchField.getText();
            Exhibition selectedExhibition =  Exhibition.getExhibitionByName(exhibitionName);
            if (selectedExhibition != null) {
                nameField.setText(selectedExhibition.getExhibitionName());
                durationField.setText(String.valueOf(selectedExhibition.getExhibitionDuration()));
                capacityField.setText(String.valueOf(selectedExhibition.getMaxCapacity()));
                startDateField.setText(selectedExhibition.getStartDate().toString());
                endDateField.setText(selectedExhibition.getEndDate().toString());
            } else {
                System.out.println("Exhibition not found!");
            }
        });


        // Save button
        Button saveButton = new Button("Save");
        saveButton.setStyle("-fx-font-size: 16px; -fx-background-color: #44403c; -fx-text-fill: white;");
        saveButton.setOnAction(event -> {
            String exhibitionName = searchField.getText().trim();
            Exhibition selectedExhibition = Exhibition.getExhibitionByName(exhibitionName);
            if (selectedExhibition != null) {
                // Actualizăm expoziția cu noile valori
                selectedExhibition.setExhibitionName(nameField.getText());
                selectedExhibition.setExhibitionDuration(Integer.parseInt(durationField.getText()));
                selectedExhibition.setMaxCapacity(Integer.parseInt(capacityField.getText()));
                selectedExhibition.setStartDate(java.sql.Date.valueOf(startDateField.getText()));
                selectedExhibition.setEndDate(java.sql.Date.valueOf(endDateField.getText()));

                // Salvăm modificările - aici, de exemplu, apelăm o metodă care salvează în baza de date
                selectedExhibition.saveExhibitionChanges();

                System.out.println("Exhibition updated successfully!");
            }
        });


        // Back button
        Button backButton = new Button("Back to Exhibitions");
        backButton.setStyle("-fx-font-size: 16px; -fx-background-color: #44403c; -fx-text-fill: white;");
        backButton.setOnAction(event -> showExhibition(primaryStage));


        // Layout
        VBox editLayout = new VBox(30);
        editLayout.getChildren().addAll(
                new Label("Edit Exhibition"),
                searchBar,nameField, durationField, capacityField, startDateField, endDateField,
                saveButton, backButton
        );
        editLayout.setAlignment(Pos.TOP_LEFT);
        editLayout.setBackground(background);
        editLayout.setStyle("-fx-padding: 15;");


        // Scene
        Scene editScene = new Scene(editLayout, windowWidth, windowHeight);
        primaryStage.setScene(editScene);
    }

    private void showVisitors(Stage primaryStage) {
        // Setting background
        String imageUrl = "file:G:/Jubii/FACULTATE/ANU II SEM 1/MIP/Ufizzi Gallery Museum/src/main/resources/images/9.1.jpg";
        Image image = new Image(imageUrl);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);


        // Layout
        BorderPane visitorsLayout = new BorderPane();
        visitorsLayout.setBackground(background);


        // Search bar
        TextField searchField = new TextField();
        searchField.setPromptText("Search Visitor by ID:");
        searchField.setStyle("-fx-font-size: 16px; -fx-background-color: #a29b9b;-fx-text-fill: black;");
        Button searchButton = new Button("Search");
        searchButton.setStyle("-fx-font-size: 16px; -fx-background-color: #44403c; -fx-text-fill: white;");


        // Layout search bar
        HBox searchBar = new HBox(30, searchField, searchButton);
        searchBar.setAlignment(Pos.TOP_LEFT);
        searchBar.setStyle("-fx-padding: 15;");
        visitorsLayout.setTop(searchBar);


        // Buttons
        Button personDetailsButton = new Button("Person Details");
        Button menuButton = new Button("Menu");

        personDetailsButton.setStyle("-fx-font-size: 16px; -fx-background-color: #44403c; -fx-text-fill: white;");
        menuButton.setStyle("-fx-font-size: 16px; -fx-background-color: #44403c; -fx-text-fill: white;");


        // Layout buttons
        HBox actionButtons = new HBox(30, personDetailsButton, menuButton);
        actionButtons.setAlignment(Pos.TOP_LEFT);
        actionButtons.setStyle("-fx-padding: 15;");


        VBox searchSection = new VBox(30, searchBar, actionButtons);
        searchSection.setStyle("-fx-padding: 15;");
        visitorsLayout.setTop(searchSection);


        // Expositions list
        VBox visitorsList = new VBox(30);
        visitorsList.setStyle("-fx-padding: 30;");
        visitorsList.setAlignment(Pos.TOP_LEFT);
        visitorsLayout.setLeft(visitorsList);
        displayAllVisitors(visitorsList);

        searchButton.setOnAction(event -> {
            String searchText = searchField.getText().trim();
            if (!searchText.isEmpty()) {
                displaySearchedVisitor(visitorsList, searchText);

            }
            else displayAllVisitors(visitorsList);
        });


        // Scene
        Scene visitorsScene = new Scene(visitorsLayout, windowWidth, windowHeight);
        primaryStage.setScene(visitorsScene);


        // Functionality
        menuButton.setOnAction(event -> {
            saveWindowDimensions();
            primaryStage.setScene(createMenuScene());
        });
        personDetailsButton.setOnAction(event ->
        {
            String searchText = searchField.getText().trim();
            if (!searchText.isEmpty()) {
                displayPersonDetails(visitorsLayout, searchText);
            }
        });

    }

    private void displayAllVisitors(VBox visitorsList) {
        visitorsList.getChildren().clear();

        // Exposition list
        List<Visitor> visitors = Visitor.getAllVisitors();

        if (visitors.isEmpty()) {
            Label emptyMessage = new Label("No visitors yet.");
            visitorsList.getChildren().add(emptyMessage);
        } else {
            for (Visitor visitor : visitors) {
                Label visitorsLabel = new Label(String.valueOf(visitor.getId_visitor()));
                visitorsLabel.setStyle("-fx-font-size: 15px; -fx-text-fill: white;");
                visitorsList.getChildren().add(visitorsLabel);
            }
        }
    }

    private void displaySearchedVisitor(VBox visitorsList, String searchText) {
        visitorsList.getChildren().clear();

        try {
            int visitorId = Integer.parseInt(searchText); // Convert String -> int
            Visitor visitor = Visitor.getVisitorById(visitorId);

            if (visitor != null) {
                Label details = new Label("Details of " + visitor.getId_visitor() + ":");
                details.setStyle("-fx-font-size: 32px; -fx-font-weight: bold;");

                Label id_person = new Label("Id_person: " + visitor.getId_person());
                Label ticketid = new Label("Id_ticket: " + visitor.getTicketid());
                Label visit_date = new Label("Visit Date: " + visitor.getVisit_date());

                VBox detailsBox = new VBox(30, details, id_person, ticketid, visit_date);
                detailsBox.setStyle("-fx-padding: 90; -fx-background-color: rgba(255,255,255,0.8);-fx-font-size: 18px;");
                visitorsList.getChildren().add(detailsBox);
            } else {
                Label notFoundMessage = new Label("No visitor found with the ID: " + searchText);
                notFoundMessage.setStyle("-fx-font-size: 18px; -fx-text-fill: white;");
                visitorsList.getChildren().add(notFoundMessage);
            }
        } catch (NumberFormatException e) {
            Label errorMessage = new Label("Invalid ID format. Please enter a numeric ID.");
            errorMessage.setStyle("-fx-font-size: 18px; -fx-text-fill: white;");
            visitorsList.getChildren().add(errorMessage);
        }
    }

    private void displayPersonDetails(BorderPane visitorsLayout, String searchText) {
        try {
            int personId = Integer.parseInt(searchText); // Convert text to integer
            Person person = Person.getPersonById(personId);

            if (person != null) {
                // Display person details
                VBox personDetailsBox = new VBox(10);

                Label idLabel = new Label("ID: " + person.getID_person());
                Label firstNameLabel = new Label("First Name: " + person.getFirstName());
                Label lastNameLabel = new Label("Last Name: " + person.getLastName());
                Label ageLabel = new Label("Age: " + person.getAge());
                Label genderLabel = new Label("Gender: " + person.getGender());
                Label emailLabel = new Label("Email: " + person.getEmail());
                Label phoneNumberLabel = new Label("Phone Number: " + person.getPhoneNumber());

                // Styling labels
                idLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");
                firstNameLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");
                lastNameLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");
                ageLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");
                genderLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");
                emailLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");
                phoneNumberLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");

                personDetailsBox.getChildren().addAll(
                        idLabel, firstNameLabel, lastNameLabel, ageLabel, genderLabel, emailLabel, phoneNumberLabel
                );

                personDetailsBox.setStyle("-fx-padding: 10; -fx-background-color: rgba(255,255,255,0.8);");
                personDetailsBox.setMaxWidth(350); // Maximum width
                personDetailsBox.setMaxHeight(350); // Maximum height

                HBox detailsLayout = new HBox(20); // Horizontal layout with spacing
                detailsLayout.setAlignment(Pos.TOP_LEFT); // Align to top-left
                detailsLayout.setPadding(new Insets(20, 0, 0, 20)); // Add some padding

                VBox visitorsListPlaceholder = new VBox(); // Placeholder for visitors list
                visitorsListPlaceholder.setMinWidth(200); // Width to match visitors list
                detailsLayout.getChildren().addAll(visitorsListPlaceholder, personDetailsBox);

                visitorsLayout.setCenter(detailsLayout);
            } else {
                Label notFoundLabel = new Label("No person found with ID: " + searchText);
                notFoundLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: white;");
                visitorsLayout.setCenter(notFoundLabel);
            }
        } catch (NumberFormatException e) {
            Label errorLabel = new Label("Invalid ID format. Please enter a numeric ID.");
            errorLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: white;");
            visitorsLayout.setCenter(errorLabel);
        }
    }

    private void showTickets(Stage primaryStage) {
        // Setting background
        String imageUrl = "file:G:/Jubii/FACULTATE/ANU II SEM 1/MIP/Ufizzi Gallery Museum/src/main/resources/images/9.1.jpg";
        Image image = new Image(imageUrl);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);


        // Layout
        BorderPane ticketsLayout = new BorderPane();
        ticketsLayout.setBackground(background);


        // Buttons
        Button menuButton = new Button("Menu");
        menuButton.setStyle("-fx-font-size: 16px; -fx-background-color: #44403c; -fx-text-fill: white;");


        // Layout buttons
        HBox actionButtons = new HBox(30, menuButton);
        actionButtons.setAlignment(Pos.TOP_LEFT);
        actionButtons.setStyle("-fx-padding: 30;");
        ticketsLayout.setTop(actionButtons);


        // Tickets list
        VBox ticketsList = new VBox(30);
        ticketsList.setStyle("-fx-padding: 30;");
        ticketsList.setAlignment(Pos.TOP_LEFT);
        ticketsLayout.setLeft(ticketsList);
        displayAllTickets(ticketsList);


        // Scene
        Scene ticketsScene = new Scene(ticketsLayout, windowWidth, windowHeight);
        primaryStage.setScene(ticketsScene);


        // Functionality
        menuButton.setOnAction(event -> {
            saveWindowDimensions();
            primaryStage.setScene(createMenuScene());
        });

    }

    private void displayAllTickets(VBox ticketsList) {
        ticketsList.getChildren().clear();

        List<Ticket> tickets = Ticket.getAllTickets();

        if (tickets.isEmpty()) {
            Label emptyMessage = new Label("No tickets yet.");
            emptyMessage.setStyle("-fx-font-size: 15px; -fx-text-fill: white;");
            ticketsList.getChildren().add(emptyMessage);
        } else {
            for (Ticket ticket : tickets) {
                // HBox for each ticket
                HBox ticketRow = new HBox(10); //
                ticketRow.setStyle("-fx-padding: 5px;");

                Label ticketIdLabel = new Label("Ticket ID: " + ticket.getTicketId());
                Label ticketTypeLabel = new Label("Type: " + ticket.getTicketType());
                Label ticketValidLabel = new Label("Valid: " + ticket.isValid());

                ticketIdLabel.setStyle("-fx-font-size: 15px; -fx-text-fill: white;");
                ticketTypeLabel.setStyle("-fx-font-size: 15px; -fx-text-fill: white;");
                ticketValidLabel.setStyle("-fx-font-size: 15px; -fx-text-fill: white;");


                ticketRow.getChildren().addAll(ticketIdLabel, ticketTypeLabel, ticketValidLabel);

                ticketsList.getChildren().add(ticketRow);
            }
        }
    }

    private void showConfirmationDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showErrorDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void saveWindowDimensions() {
        windowWidth = primaryStage.getWidth();
        windowHeight = primaryStage.getHeight();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
