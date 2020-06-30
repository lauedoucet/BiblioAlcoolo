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

import java.util.ArrayList;


public class BiblioAlcoolo extends Application {

    private static final int WIDTH = 500;
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
       user.loadFile();

        window = primaryStage;
        window.setTitle(TITLE + " " + VERSION);

        welcomePage = generateWelcomePage();
        window.setScene(welcomePage);
        window.show();
    }

    /**
     * Scene displays simple view of App name and close button
     * TODO: add animation of wine flowing
     * @return scene layout for welcome page
     */
    private Scene generateWelcomePage() {
        Button welcomeButton = new Button(TITLE);
        StackPane.setAlignment(welcomeButton, Pos.CENTER);
        welcomeButton.setOnAction(e ->
        {
            librariesPage = generateLibrariesPage();
            librariesPage.getStylesheets().add(STYLE_SHEET);
            window.setScene(librariesPage);
        });
        welcomeButton.setId("bold-button");

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

    /**
     * Scene which displays the page containing all of the user's libraries
     * @return scene layout
     */
    private Scene generateLibrariesPage() {
        BorderPane layout = new BorderPane();

        GridPane librariesName = new GridPane();
        librariesName.setPadding(new Insets(10));

        Label label = new Label("Your Libraries");
        label.setId("bold-label");
        layout.setTop(label);
        BorderPane.setAlignment(label, Pos.CENTER);

        int i = 0;
        for (Library library : user) {
            Button button = new Button(library.getName());
            button.setOnAction(e -> {
                libraryHomePage = generateLibraryHomePage(library);;
                window.setScene(libraryHomePage);
            });
            librariesName.getChildren().add(button);
            GridPane.setConstraints(button, 0, i);
            i++;
        }
        layout.setLeft(librariesName);

        Button addNewLibrary = new Button("Add new library");
        addNewLibrary.setOnAction(e -> {
            Library library = AddNewBox.getNewLibrary();
            user.addLibrary(library);
            libraryHomePage = generateLibraryHomePage(library);
            window.setScene(libraryHomePage);
        });
        addNewLibrary.setId("add-button");
        librariesName.getChildren().add(addNewLibrary);
        GridPane.setConstraints(addNewLibrary, 0, i+1);

        Button modify = new Button("Modify");
        modify.setOnAction(e -> {
            GridPane modifyView = generateLibrariesModifyView(librariesName, modify);
            layout.setLeft(modifyView);
            window.setScene(librariesPage);
        });
        librariesName.getChildren().add(modify);
        GridPane.setConstraints(modify, 1, i+1);

        Button back = new Button("back");
        back.setOnAction(e -> window.setScene(welcomePage));
        layout.setBottom(back);
        BorderPane.setAlignment(back, Pos.BOTTOM_RIGHT);

        Scene scene = new Scene(layout, WIDTH, HEIGHT);
        scene.getStylesheets().add(STYLE_SHEET);
        return scene;
    }

    /**
     * Generates a scene which displays the contents of the input library
     * @param library library to display
     * @return scene layout
     */
    private Scene generateLibraryHomePage(Library library) {
        BorderPane layout = new BorderPane();
        Label libraryName = new Label(library.getName());
        libraryName.setId("bold-label");
        layout.setTop(libraryName);
        BorderPane.setAlignment(libraryName, Pos.CENTER);

        // StackPane for alcohol info
        StackPane alcoholInfo = new StackPane();

        VBox info = new VBox();
        alcoholInfo.getChildren().add(info);
        StackPane.setAlignment(info, Pos.BOTTOM_CENTER);
        layout.setCenter(alcoholInfo);

        // VBox for alcohol names and add button
        GridPane alcoholNames = new GridPane();
        alcoholNames.setPadding(new Insets(10));

        int i = 0;
        for (Alcohol alcohol : library) {
            Button button = new Button(alcohol.getName());
            button.setOnAction(e -> {
                info.getChildren().clear();
                info.getChildren().addAll(getAlcoholInfo(alcohol));
            });
            alcoholNames.getChildren().addAll(button);
            GridPane.setConstraints(button, 0, i);
            i++;
        }

        Button addNewAlcohol = new Button("Add new alcohol");
        addNewAlcohol.setOnAction(e -> {
            Alcohol alcohol = AddNewBox.getNewAlcohol();
            library.addAlcohol(alcohol);
            libraryHomePage = generateLibraryHomePage(library);
            window.setScene(libraryHomePage);
        });
        addNewAlcohol.setId("add-button");
        alcoholNames.getChildren().add(addNewAlcohol);
        GridPane.setConstraints(addNewAlcohol, 0, i+1);

        Button modify = new Button("Modify");
        modify.setOnAction(e -> {
            GridPane modifyView = generateLibraryHomeModifyView(alcoholNames, modify, library);
            layout.setLeft(modifyView);
            window.setScene(libraryHomePage);
        });
        alcoholNames.getChildren().add(modify);
        GridPane.setConstraints(modify, 1, i+1);
        layout.setLeft(alcoholNames);

        Button back = new Button("back");
        back.setOnAction(e -> {
            librariesPage = generateLibrariesPage();
            window.setScene(librariesPage);
        });
        layout.setBottom(back);
        BorderPane.setAlignment(back, Pos.BOTTOM_RIGHT);

        Scene scene = new Scene(layout, WIDTH, HEIGHT);
        scene.getStylesheets().add(STYLE_SHEET);
        return scene;
    }

    /**
     * Returns labels to display the information relevant to this alcohol
     * @param alcohol whose parameters we want to display
     * @return labels to be displayed in an ArrayList
     */
    private ArrayList<Label> getAlcoholInfo(Alcohol alcohol) {
        ArrayList<Label> labels = new ArrayList<>();
        Label name = new Label("Name:       " + alcohol.getName());
        Label abv = new Label("ABV:          " + alcohol.getABV() + "%");
        Label country = new Label("Country:     " + alcohol.getCountry());
        labels.add(name);
        labels.add(abv);
        labels.add(country);

        if (alcohol.getClass() == Wine.class) {
            Wine wine = (Wine) alcohol;
            if (wine.getColour() != WineColour.NULL) {
                Label colour = new Label("Colour:        " + wine.getColour().name().toLowerCase());
                labels.add(colour);
            }
            if (!wine.getVariety().equals("")) {
                Label variety = new Label("Variety:        " + wine.getVariety());
                labels.add(variety);
            }
        } else if (alcohol.getClass() == Beer.class) {
          Beer beer = (Beer) alcohol;
          if (beer.getColour() != BeerColour.NULL) {
              Label colour = new Label("Colour:   " + beer.getColour().name().toLowerCase());
              labels.add(colour);
          }
          if (beer.getIBU() != 0) {
              Label ibu = new Label("IBU:     " + beer.getIBU());
              labels.add(ibu);
          }
        }
        return labels;
    }

    private GridPane generateLibrariesModifyView(GridPane grid, Button modify) {
        GridPane newView = grid;

        int i = 0;
        for (Library library : user) {
            // remove old
            newView.getChildren().remove(0, i);
            // create new
            Button button = new Button(library.getName());
            button.setOnAction(event -> {
                ModifyBox.modifyLibrary(library);
                libraryHomePage = generateLibraryHomePage(library);
                window.setScene(libraryHomePage);
            });
            newView.getChildren().add(button);
            GridPane.setConstraints(button, 0, i);
            // create delete buttons
            Button delete = new Button("X");
            delete.setOnAction(actionEvent -> {
                user.removeLibrary(library);
                librariesPage = generateLibrariesPage();
                window.setScene(librariesPage);
            });
            newView.getChildren().add(delete);
            GridPane.setConstraints(delete, 1, i);
            i++;
        }
        // change modify button
        newView.getChildren().remove(modify);
        Button normal = new Button("back");
        normal.setOnAction(action -> {
            librariesPage = generateLibrariesPage();
            window.setScene(librariesPage);
        });
        newView.getChildren().add(normal);
        GridPane.setConstraints(normal, 1, i+1);

        return newView;
    }

    private GridPane generateLibraryHomeModifyView(GridPane grid, Button modify, Library library) {
        GridPane newView = grid;

        int i = 0;
        for (Alcohol alcohol : library) {
            // remove old
            newView.getChildren().remove(0, i);
            // create new
            Button button = new Button(alcohol.getName());
            button.setOnAction(event -> {
                ModifyBox.modifyAlcohol(alcohol);
                libraryHomePage = generateLibraryHomePage(library);
                window.setScene(libraryHomePage);
            });
            newView.getChildren().add(button);
            GridPane.setConstraints(button, 0, i);
            // create delete buttons
            Button delete = new Button("X");
            delete.setOnAction(actionEvent -> {
                library.removeAlcohol(alcohol);
                libraryHomePage = generateLibraryHomePage(library);
                window.setScene(libraryHomePage);
            });
            newView.getChildren().add(delete);
            GridPane.setConstraints(delete, 1, i);
            i++;
        }
        // change modify button
        newView.getChildren().remove(modify);
        Button normal = new Button("back");
        normal.setOnAction(action -> {
            libraryHomePage = generateLibraryHomePage(library);
            window.setScene(libraryHomePage);
        });
        newView.getChildren().add(normal);
        GridPane.setConstraints(normal, 1, i+1);
        return newView;
    }

    private void closeProgram() {
        boolean answer = BoolInputBox.display("Closing?", "Sure you want to exit?");
        if (answer) {
            user.saveFile();
            window.close();
        }
    }
}