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
import java.util.ArrayList;

public class BiblioAlcoolo extends Application {

    private static final int WIDTH = 680;
    private static final int HEIGHT = 500;
    private static final int MARGIN_OUTER = 10;
    private static final String TITLE = "BiblioAlcolo";
    private static final String VERSION = "1.0";
    private static final String STYLE_SHEET = "./biblioStyle.css";

    private StackPane stack;
    private GridPane root;
    private Stage window;
    private Scene scene1, scene2;

    private ArrayList<Alcohol> boozes = new ArrayList<Alcohol>();
    private Library library = new Library();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle(TITLE + " " + VERSION);

        /************Setting up first page***********/
        Label welcomeLabel = new Label("Welcome to BiblioAlcoolo!");
        StackPane.setAlignment(welcomeLabel, Pos.TOP_CENTER);

        Button moveButton = new Button("Log new alcohol");
        StackPane.setAlignment(moveButton, Pos.CENTER);
        moveButton.setOnAction(e -> window.setScene(scene2));

        Button closeButton = new Button("Bye bye");
        closeButton.setOnAction(e -> window.close());
        StackPane.setAlignment(closeButton, Pos.BOTTOM_RIGHT);

        stack = new StackPane();
        stack.getChildren().addAll(welcomeLabel, moveButton, closeButton);
        scene1 = new Scene(stack, WIDTH, HEIGHT);
        scene1.getStylesheets().add(STYLE_SHEET);

        /************Setting up second page*****************/
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


        Button addAlcohol = new Button("Add to collection");
        addAlcohol.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String name = nameField.getText();
                try {
                    int size = Integer.parseInt(sizeField.getText());
                    double abv = Double.parseDouble(abvField.getText());
                    Alcohol newAlcohol = new Alcohol(name, size, abv);
                    library.addBottle(newAlcohol);
                } catch (NumberFormatException e) {
                    /********TODO: display error message*************/
                    // tells user it needs to be a whole number for size and double for abv
                    // clicking 'ok' clears the field and exits that window
                    AlertBox.display("Invalid Input", "Please enter numbers for size and ABV");
                }
            }
        });
        GridPane.setConstraints(addAlcohol, 0, 3);

        root = new GridPane();
        root.setHgap(MARGIN_OUTER);
        root.setVgap(MARGIN_OUTER);
        root.setPadding(new Insets(MARGIN_OUTER));
        root.getChildren().addAll(nameLabel, sizeLabel, abvLabel, nameField, sizeField, abvField, addAlcohol);
        scene2 = new Scene(root, WIDTH, HEIGHT);
        scene2.getStylesheets().add(STYLE_SHEET);

        window.setScene(scene1);
        window.setResizable(false);
        window.show();
    }
}