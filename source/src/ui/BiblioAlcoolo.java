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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.*;
import main.*;

import java.io.*;
import java.util.ArrayList;


public class BiblioAlcoolo extends Application {

    private static final int WIDTH = 680;
    private static final int HEIGHT = 500;
    private static final String TITLE = "BiblioAlcoolo";
    private static final String VERSION = "1.0";
    private static final String STYLE_SHEET = "ui/biblioStyle.css";
    private static final String FILE_PATH = "savefile.txt";

    private Stage window;
    private Scene welcomePage, librariesPage, libraryHomePage;
    private Tooltip tooltip = new Tooltip();

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
        BorderPane layout = new BorderPane();

        VBox librariesName = new VBox();
        librariesName.setPadding(new Insets(10));
        librariesName.setSpacing(8);

        Label label = new Label("Your Libraries: ");
        librariesName.getChildren().add(label);

        for (Library library : user) {
            Button button = new Button(library.getName());
            button.setOnAction(e -> {
                libraryHomePage = generateLibraryHomePage(library);;
                window.setScene(libraryHomePage);
            });
            librariesName.getChildren().add(button);
        }
        layout.setLeft(librariesName);

        Button addNewLibrary = new Button("Add new library");
        addNewLibrary.setOnAction(e -> {
            Library library = AddNewBox.getNewLibrary();
            user.addLibrary(library);
            libraryHomePage = generateLibraryHomePage(library);
            window.setScene(libraryHomePage);
        });
        librariesName.getChildren().add(addNewLibrary);

        Button back = new Button("back");
        back.setOnAction(e -> window.setScene(welcomePage));
        layout.setBottom(back);
        BorderPane.setAlignment(back, Pos.BOTTOM_RIGHT);

        Scene scene = new Scene(layout, WIDTH, HEIGHT);
        scene.getStylesheets().add(STYLE_SHEET);
        return scene;
    }

    private Scene generateLibraryHomePage(Library library) {
        BorderPane layout = new BorderPane();
        Label libraryName = new Label(library.getName());
        layout.setTop(libraryName);
        BorderPane.setAlignment(libraryName, Pos.CENTER);

        // StackPane for alcohol info
        StackPane alcoholInfo = new StackPane();

        VBox info = new VBox();
        alcoholInfo.getChildren().add(info);
        StackPane.setAlignment(info, Pos.BOTTOM_CENTER);
        layout.setCenter(alcoholInfo);

        // VBox for alcohol names and add button
        VBox alcoholNames = new VBox();
        alcoholNames.setPadding(new Insets(10));
        alcoholNames.setSpacing(8);

        for (Alcohol alcohol : library) {
            Button button = new Button(alcohol.getName());
            button.setOnAction(e -> {
                info.getChildren().clear();
                info.getChildren().addAll(getAlcoholInfo(alcohol));
            });
            alcoholNames.getChildren().addAll(button);
        }

        Button addNewAlcohol = new Button("Add new alcohol");
        addNewAlcohol.setOnAction(e -> {
            Alcohol alcohol = AddNewBox.getNewAlcohol();
            library.addAlcohol(alcohol);
            libraryHomePage = generateLibraryHomePage(library);
            window.setScene(libraryHomePage);
        });
        alcoholNames.getChildren().add(addNewAlcohol);
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
            if (wine.getVariety() != "") {
                Label variety = new Label("Variety:          " + wine.getVariety());
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

    private void closeProgram() {

        boolean answer = BoolInputBox.display("Closing?", "Sure you want to exit?");
        if (answer) {
            /**TODO: save file**/
            String file = FILE_PATH;
            try {
                ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream((new FileOutputStream(file))));

                out.writeInt(user.getSize());
                for (Library library : user) {
                    out.writeUTF(library.getName());
                    out.writeInt(library.getSize());
                    for (Alcohol alcohol : library) {
                        out.writeObject(alcohol);
                    }
                }
            } catch (IOException e) {
                System.out.println(e);
            }

            /** TESTING **/
            try {
                ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));
                int userSize = in.readInt();
                while (userSize > 0) {
                    System.out.println("Library:    " + in.readUTF());
                    int libSize = in.readInt();

                    while (libSize > 0) {
                        Alcohol alcohol = (Alcohol) in.readObject();
                        System.out.println(alcohol.getName());
                        libSize--;
                    }
                    userSize--;
                }

            } catch (ClassNotFoundException e) {
                System.out.println(e);

            } catch (IOException e) {
                System.out.println(e);
            }


            window.close();
        }
    }
}