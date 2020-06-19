/*
 *
 *   Copyright (C) 2020 Laurence Doucet
 *   Application that allows the management and record of alcohol
 *   Application class for the library of alcohol using JavaFX
 *
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.*;

public class BiblioAlcoolo extends Application {

    private static final int WIDTH = 680;
    private static final int HEIGHT = 500;
    private static final int MARGIN_OUTER = 10;
    private static final String TITLE = "BiblioAlcoolo";
    private static final String VERSION = "1.0";
    private static final String STYLE_SHEET = "./biblioStyle.css";

    private Stage window;
    private Scene welcomePage, librariesPage, libraryHomePage;

    private Library library = new Library();
    private User user = new User();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle(TITLE + " " + VERSION);

        /*************************welcomePage**********************/
        Button welcomeButton = new Button(TITLE);
        StackPane.setAlignment(welcomeButton, Pos.CENTER);
        welcomeButton.setOnAction(e -> window.setScene(librariesPage));

        Button closeButton = new Button("close");
        closeButton.setOnAction(e -> window.close());
        StackPane.setAlignment(closeButton, Pos.BOTTOM_RIGHT);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(welcomeButton, closeButton);
        welcomePage = new Scene(stackPane, WIDTH, HEIGHT);
        welcomePage.getStylesheets().add(STYLE_SHEET);

        /*************************librariesPage**********************/
        Button addNewAlcohol = new Button("Add new alcohol");
        GridPane.setConstraints(addNewAlcohol, 0, 0);
        GridPane.setFillWidth(addNewAlcohol, true);
        addNewAlcohol.setOnAction(e -> window.setScene(libraryHomePage));

        Button goBack = new Button("back");
        GridPane.setConstraints(goBack, 1,1);
        goBack.setOnAction(e -> window.setScene(welcomePage));

        GridPane gridPane1 = new GridPane();
        gridPane1.getChildren().addAll(addNewAlcohol, goBack);
        librariesPage = new Scene(gridPane1, WIDTH, HEIGHT);
        librariesPage.getStylesheets().add(STYLE_SHEET);

        /*************************libraryHomePage**********************/
        Text nameLabel = new Text("Name: ");
        GridPane.setConstraints(nameLabel, 0,0);
        Text sizeLabel = new Text("Size: ");
        GridPane.setConstraints(sizeLabel, 0,1);
        Text abvLabel = new Text("ABV: ");
        GridPane.setConstraints(abvLabel, 0,2);

        TextField nameField = new TextField();
        GridPane.setConstraints(nameField, 1,0);
        TextField sizeField = new TextField();
        GridPane.setConstraints(sizeField, 1,1);
        TextField abvField = new TextField();
        GridPane.setConstraints(abvField, 1,2);

        Button addAlcohol = new Button("Add to library");
        GridPane.setConstraints(addAlcohol, 0, 3);
        addAlcohol.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String name = nameField.getText();
                try {
                    int size = Integer.parseInt(sizeField.getText());
                    double abv = Double.parseDouble(abvField.getText());
                    Alcohol newAlcohol = new Alcohol(name, size, abv);
                    library.addBottle(newAlcohol);
                    newAlcohol.displayInfo();
                } catch (NumberFormatException e) {
                    /********TODO: display error message*************/
                    // tells user it needs to be a whole number for size and double for abv
                    // clicking 'ok' clears the field and exits that window
                    AlertBox.display("Invalid Input", "Please enter numbers for size and ABV");
                }
            }
        });

        GridPane gridPane2 = new GridPane();
        gridPane2.setHgap(MARGIN_OUTER);
        gridPane2.setVgap(MARGIN_OUTER);
        gridPane2.setPadding(new Insets(MARGIN_OUTER));
        gridPane2.getChildren().addAll(nameLabel, sizeLabel, abvLabel, nameField, sizeField, abvField, addAlcohol);
        libraryHomePage = new Scene(gridPane2, WIDTH, HEIGHT);
        libraryHomePage.getStylesheets().add(STYLE_SHEET);

        window.setScene(welcomePage);
        window.setResizable(false);
        window.show();
    }
}