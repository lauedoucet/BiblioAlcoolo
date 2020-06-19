/*
 *
 *   Copyright (C) 2020 Laurence Doucet
 *   Application that allows the management and record of alcohol
 *   Application class for the library of alcohol using JavaFX
 *
 */

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.*;

public class BiblioAlcoolo extends Application {

    private static final int WIDTH = 680;
    private static final int HEIGHT = 500;
    private static final String TITLE = "BiblioAlcoolo";
    private static final String VERSION = "1.0";
    private static final String STYLE_SHEET = "./biblioStyle.css";

    private Stage window;
    private Scene welcomePage, librariesPage;

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
        closeButton.setOnAction(e -> closeProgram());
        StackPane.setAlignment(closeButton, Pos.BOTTOM_RIGHT);
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(welcomeButton, closeButton);
        welcomePage = new Scene(stackPane, WIDTH, HEIGHT);
        welcomePage.getStylesheets().add(STYLE_SHEET);

        /*************************libraryPage**********************/
        Button addNewAlcohol = new Button("Add new alcohol");
        GridPane.setConstraints(addNewAlcohol, 0, 0);
        GridPane.setFillWidth(addNewAlcohol, true);
        addNewAlcohol.setOnAction(e -> {
            Alcohol alcohol = AddNewBottleBox.getNewAlcohol();
            library.addBottle(alcohol);
            /**TODO: remove later**/
            library.displayCollection();
        });

        Button goBack = new Button("back");
        GridPane.setConstraints(goBack, 1,1);
        goBack.setOnAction(e -> window.setScene(welcomePage));

        /*************addNewAlcoholPopUp**************************/
        GridPane gridPane1 = new GridPane();
        gridPane1.getChildren().addAll(addNewAlcohol, goBack);
        librariesPage = new Scene(gridPane1, WIDTH, HEIGHT);
        librariesPage.getStylesheets().add(STYLE_SHEET);

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
}