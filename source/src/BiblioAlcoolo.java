/*
 *
 *   Copyright (C) 2020 Laurence Doucet
 *   Application that allows the management and record of alcohol
 *   Application class for the library of alcohol
 *
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.*;

public class BiblioAlcoolo extends Application {

    private static final int WIDTH = 680;
    private static final int HEIGHT = 500;
    private static final int MARGIN_OUTER = 10;
    private static final String TITLE = "BiblioAlcolo";
    private static final String VERSION = "1.0";
    private static final String STYLE_SHEET = "./biblioStyle.css";

    private GridPane root;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle(TITLE + " " + VERSION);

        root = new GridPane();
        root.setHgap(MARGIN_OUTER);
        root.setVgap(MARGIN_OUTER);
        root.setPadding(new Insets(MARGIN_OUTER));

        /***********Setting up closing button**********/
        Button closeButton = new Button("close");
        closeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (actionEvent.getSource() == closeButton) {
                    primaryStage.close();
                }
            }
        });
        GridPane.setConstraints(closeButton, 0,5);
        root.getChildren().add(closeButton);

        /******Setting up labels*************/
        Text nameLabel = new Text("Name: ");
        GridPane.setConstraints(nameLabel, 0,0);
        Text sizeLabel = new Text("Size: ");
        GridPane.setConstraints(sizeLabel, 0,1);
        Text abvLabel = new Text("ABV: ");
        GridPane.setConstraints(abvLabel, 0,2);
        root.getChildren().addAll(nameLabel, sizeLabel, abvLabel);

        /**********Setting up fields**********/
        TextField nameField = new TextField();
        GridPane.setConstraints(nameField, 1,0);
        nameField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                // if press enter then record the name
            }
        });
        TextField sizeField = new TextField();
        GridPane.setConstraints(sizeField, 1,1);
        TextField abvField = new TextField();
        GridPane.setConstraints(abvField, 1,2);
        root.getChildren().addAll(nameField, sizeField, abvField);

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.getStylesheets().add(STYLE_SHEET);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
