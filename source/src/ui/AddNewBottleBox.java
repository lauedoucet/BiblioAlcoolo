/*
 *
 *   Copyright (C) 2020 Laurence Doucet
 *   App that allows the management and record of alcohol
 *   Class represents a pop-window which gets input from user to create add a new alcohol to library
 *
 */

package ui;
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
import main.Alcohol;

public class AddNewBottleBox {

    private static final int WIDTH = 450;
    private static final int HEIGHT = 250;
    private static final String STYLE_SHEET = "ui/biblioStyle.css";
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
        Text abvLabel = new Text("ABV: ");
        GridPane.setConstraints(abvLabel, 0,1);
        Text countryLabel = new Text("Country: ");
        GridPane.setConstraints(countryLabel, 0, 2);

        TextField nameField = new TextField();
        nameField.setPromptText("Name of alcohol");
        GridPane.setConstraints(nameField, 1,0);
        TextField abvField = new TextField();
        abvField.setPromptText("Alcohol by volume %");
        GridPane.setConstraints(abvField, 1,1);
        TextField countryField = new TextField();
        countryField.setPromptText("Country of origin");
        GridPane.setConstraints(countryField, 1, 2);

        Button addAlcohol = new Button("Add");
        GridPane.setConstraints(addAlcohol, 1, 3);
        GridPane.setFillWidth(addAlcohol, true);
        addAlcohol.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String name = nameField.getText();
                try {
                    double abv = Double.parseDouble(abvField.getText());
                    String country = countryField.getText();
                     alcohol = new Alcohol(name, abv, country);
                     /**TODO: update**/
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
        gridPane.getChildren().addAll(nameLabel, abvLabel, countryLabel, nameField, abvField, countryField, addAlcohol);

        Scene scene = new Scene(gridPane, WIDTH, HEIGHT);
        scene.getStylesheets().add(STYLE_SHEET);
        window.setScene(scene);
        window.showAndWait();

        return alcohol;
    }
}
