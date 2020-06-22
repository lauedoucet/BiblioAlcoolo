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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.*;
import main.Alcohol;
import main.Library;
import main.User;

public class BiblioAlcoolo extends Application {

    private static final int WIDTH = 680;
    private static final int HEIGHT = 500;
    private static final String TITLE = "BiblioAlcoolo";
    private static final String VERSION = "1.0";
    private static final String STYLE_SHEET = "ui/biblioStyle.css";

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
        welcomePage = new Scene(stackPane, WIDTH, HEIGHT);
        welcomePage.getStylesheets().add(STYLE_SHEET);

        /*************************libraryPage**********************/
        Label libraryLabel = new Label("Your library: ");
        GridPane.setConstraints(libraryLabel, 0,0);

        GridPane alcoholGrid = displayLibrary();
        GridPane.setConstraints(alcoholGrid, 0, 1);

        Button addNewAlcohol = new Button("Add new alcohol");
        GridPane.setConstraints(addNewAlcohol, 1, 0);
        GridPane.setFillWidth(addNewAlcohol, true);
        addNewAlcohol.setOnAction(e -> {
            Alcohol alcohol = AddNewBottleBox.getNewAlcohol();
            if (!library.addAlcohol(alcohol)) {
                library.addAlcohol(alcohol.getName(), alcohol.getSize(), alcohol.getABV());
            }
            /**TODO: remove later**/
            library.displayCollection();
        });

        Button goBack = new Button("back");
        GridPane.setConstraints(goBack, 1,1);
        goBack.setOnAction(e -> window.setScene(welcomePage));

        GridPane gridPane1 = new GridPane();
        gridPane1.setPadding(new Insets(10,10,10,10));
        gridPane1.setVgap(8);
        gridPane1.setHgap(8);
        gridPane1.getChildren().addAll(libraryLabel, alcoholGrid, addNewAlcohol, goBack);

        libraryHomePage = new Scene(gridPane1, WIDTH, HEIGHT);
        libraryHomePage.getStylesheets().add(STYLE_SHEET);

        window.setScene(welcomePage);
        window.setResizable(false);
        window.show();
    }

    private GridPane displayLibrary() {
        GridPane grid = new GridPane();
        int i = 0;
        for (Alcohol alcohol : library) {
            Label label = new Label(alcohol.getName());
            GridPane.setConstraints(label, 0, i);
            grid.getChildren().add(label);
            i++;
        }
        return grid;
    }

    private void closeProgram() {
        /**TODO: save file**/
        boolean answer = BoolInputBox.display("Closing?", "Sure you want to exit?");
        if (answer) {
            window.close();
        }
    }
}