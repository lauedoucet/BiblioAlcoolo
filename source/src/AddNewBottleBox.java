/*
 *
 *   Copyright (C) 2020 Laurence Doucet
 *   App that allows the management and record of alcohol
 *   Class represents a pop-window which gets input from user to create add a new alcohol to library
 *
 */

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddNewBottleBox {

    private static final int WIDTH = 450;
    private static final int HEIGHT = 250;
    private static final String STYLE_SHEET = "./biblioStyle.css";
    private static Alcohol alcohol;

    /**
     * Sets up window which needs to be completed to go back to library page
     * Asks user for the name, size (in ml), and ABV (in %) for the new bottle
     * @returns the new alcohol object created
     */
    public static Alcohol getNewAlcohol() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Add new alcohol");

        Text nameLabel = new Text("Name: ");
        GridPane.setConstraints(nameLabel, 0,0);
        Text sizeLabel = new Text("Size: ");
        GridPane.setConstraints(sizeLabel, 0,1);
        Text abvLabel = new Text("ABV: ");
        GridPane.setConstraints(abvLabel, 0,2);

        TextField nameField = new TextField();
        nameField.setPromptText("Name of Alcohol");
        GridPane.setConstraints(nameField, 1,0);
        TextField sizeField = new TextField();
        sizeField.setPromptText("Size of bottle in ml");
        GridPane.setConstraints(sizeField, 1,1);
        TextField abvField = new TextField();
        abvField.setPromptText("Alcohol by volume %");
        GridPane.setConstraints(abvField, 1,2);

        Button addAlcohol = new Button("Add");
        GridPane.setConstraints(addAlcohol, 1, 3);
        GridPane.setFillWidth(addAlcohol, true);
        addAlcohol.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String name = nameField.getText();
                try {
                    int size = Integer.parseInt(sizeField.getText());
                    double abv = Double.parseDouble(abvField.getText());
                     alcohol = new Alcohol(name, size, abv);
                     /**TODO: remove later**/
                     System.out.println("new alcohol logged");
                     window.close();
                } catch (NumberFormatException e) {
                    AlertBox.display("Invalid Input", "Please enter numbers for size and ABV");
                }
            }
        });

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10,10,10,10));
        gridPane.setVgap(8);
        gridPane.setHgap(8);
        gridPane.getChildren().addAll(nameLabel, sizeLabel, abvLabel, nameField, sizeField, abvField, addAlcohol);

        Scene scene = new Scene(gridPane, WIDTH, HEIGHT);
        scene.getStylesheets().add(STYLE_SHEET);
        window.setScene(scene);
        window.showAndWait();
        // display/updateLibrary

        return alcohol;
    }
}
