/*
 *
 *   Copyright (C) 2020 Laurence Doucet
 *   App that allows the management and record of alcohol
 *   Class represents a pop-window which gets input from user to create add a new alcohol to library
 *
 */

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddNewBottleBox {

    private static final int WIDTH = 450;
    private static final int HEIGHT = 400;
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
        gridPane.getChildren().addAll(nameLabel, sizeLabel, abvLabel, nameField, sizeField, abvField, addAlcohol);

        Scene scene = new Scene(gridPane, WIDTH, HEIGHT);
        scene.getStylesheets().add(STYLE_SHEET);
        window.setScene(scene);
        window.showAndWait();

        return alcohol;
    }
}
