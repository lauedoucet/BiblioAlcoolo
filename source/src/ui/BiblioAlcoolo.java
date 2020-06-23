/*
 *
 *   Copyright (C) 2020 Laurence Doucet
 *   Application that allows the management and record of alcohol
 *   Application class for the library of alcohol using JavaFX
 *
 */
package ui;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.*;
import main.*;

import java.util.Stack;

public class BiblioAlcoolo extends Application {

    private static final int WIDTH = 680;
    private static final int HEIGHT = 500;
    private static final String TITLE = "BiblioAlcoolo";
    private static final String VERSION = "1.0";
    private static final String STYLE_SHEET = "ui/biblioStyle.css";

    private Stage window;
    private Scene welcomePage, librariesPage, libraryHomePage;

    private Library library = new Library("Library 1");
    private User user = new User();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle(TITLE + " " + VERSION);

        welcomePage = generateWelcomePage();

        libraryHomePage = generateLibraryHomePage();
        libraryHomePage.getStylesheets().add(STYLE_SHEET);

        window.setScene(welcomePage);
        window.setResizable(false);
        window.show();
    }

    private void closeProgram() {
        /**TODO: save file**/
        boolean answer = BoolInputBox.display("Closing?", "Sure you want to exit?");
        if (answer) {
            window.close();
        }
    }

    private Scene generateWelcomePage() {
        Button welcomeButton = new Button(TITLE);
        StackPane.setAlignment(welcomeButton, Pos.CENTER);
        welcomeButton.setOnAction(e -> window.setScene(libraryHomePage));

        Button closeButton = new Button("close");
        closeButton.setOnAction(e -> closeProgram());
        StackPane.setAlignment(closeButton, Pos.BOTTOM_RIGHT);
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(welcomeButton, closeButton);
        Scene scene = new Scene(stackPane, WIDTH, HEIGHT);
        scene.getStylesheets().add(STYLE_SHEET);
        return scene;
    }

    private Scene generateLibraryHomePage() {

        Wine redWine = new Wine("19 Crimes", 14.5, "Australia", WineVariety.WineColour.RED);
        Wine whiteWine = new Wine("Albert Bichot", 12.5, "France", WineVariety.CHARDONNAY);
        Beer beer = new Beer("Amacord Gradisca", 5.2, "Italy", BeerColour.BLONDE);
        Alcohol gin = new Alcohol("1769 Distillery Azura", 42, "Canada");
        library.addAll(redWine, whiteWine, beer, gin);

        Label libraryName = new Label(library.getName());
        VBox librariesLayout = new VBox();
        librariesLayout.setPadding(new Insets(10));
        librariesLayout.setSpacing(8);
        librariesLayout.getChildren().add(libraryName);

        for (Alcohol alcohol : library) {
            Label label = new Label(alcohol.getName());
            librariesLayout.getChildren().add(label);
        }

        Button addNewAlcohol = new Button("Add new alcohol");
        addNewAlcohol.setOnAction(e -> {
            Alcohol alcohol = AddNewBottleBox.getNewAlcohol();
            library.addAlcohol(alcohol);
            Scene scene = generateLibraryHomePage();
            window.setScene(scene);
        });

        Button goBack = new Button("back");
        goBack.setOnAction(e -> window.setScene(welcomePage));
        librariesLayout.getChildren().addAll(addNewAlcohol,goBack);

        Scene scene = new Scene(librariesLayout, WIDTH, HEIGHT);
        scene.getStylesheets().add(STYLE_SHEET);
        return scene;
    }
}