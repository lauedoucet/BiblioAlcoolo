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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.*;
import main.*;


public class BiblioAlcoolo extends Application {

    private static final int WIDTH = 680;
    private static final int HEIGHT = 500;
    private static final String TITLE = "BiblioAlcoolo";
    private static final String VERSION = "1.0";
    private static final String STYLE_SHEET = "ui/biblioStyle.css";

    private Stage window;
    private Scene welcomePage, librariesPage, libraryHomePage;

    private User user = new User();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        /** TEST START **/
        user.addLibrary(new Library("Collection 1"));
        Wine redWine = new Wine("19 Crimes", 14.5, "Australia");
        Wine whiteWine = new Wine("Albert Bichot", 11.8, "France");
        Beer beer = new Beer("Amacord Gradisca", 5.2, "Italy");
        user.getLibrary("Collection 1").addAll(redWine, whiteWine, beer);
        /** TEST END **/

        window = primaryStage;
        window.setTitle(TITLE + " " + VERSION);

        welcomePage = generateWelcomePage();

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
        welcomeButton.setOnAction(e ->
        {
            librariesPage = generateLibrariesPage();
            librariesPage.getStylesheets().add(STYLE_SHEET);
            window.setScene(librariesPage);
        });

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

    private Scene generateLibrariesPage() {
        VBox layout = new VBox();
        layout.setPadding(new Insets(10));
        layout.setSpacing(8);

        Label label = new Label("Your Libraries: ");
        layout.getChildren().add(label);

        for (Library library : user) {
            Button button = new Button(library.getName());
            button.setOnAction(e -> {
                libraryHomePage = generateLibraryHomePage(library);;
                window.setScene(libraryHomePage);
            });
            layout.getChildren().add(button);
        }

        Button back = new Button("back");
        back.setOnAction(e -> window.setScene(welcomePage));
        layout.getChildren().add(back);

        Scene scene = new Scene(layout, WIDTH, HEIGHT);
        scene.getStylesheets().add(STYLE_SHEET);
        return scene;
    }

    private Scene generateLibraryHomePage(Library library) {
        VBox layout = new VBox();
        layout.setPadding(new Insets(10));
        layout.setSpacing(8);

        Label libraryName = new Label(library.getName());
        layout.getChildren().add(libraryName);

        for (Alcohol alcohol : library) {
            Label label = new Label(alcohol.getName());
            layout.getChildren().add(label);
        }

        Button addNewAlcohol = new Button("Add new alcohol");
        addNewAlcohol.setOnAction(e -> {
            Alcohol alcohol = AddNewBottleBox.getNewAlcohol();
            library.addAlcohol(alcohol);
            libraryHomePage = generateLibraryHomePage(library);
            window.setScene(libraryHomePage);
        });

        Button back = new Button("back");
        back.setOnAction(e -> {
            librariesPage = generateLibrariesPage();
            window.setScene(librariesPage);
        });
        layout.getChildren().addAll(addNewAlcohol,back);

        Scene scene = new Scene(layout, WIDTH, HEIGHT);
        scene.getStylesheets().add(STYLE_SHEET);
        return scene;
    }
}