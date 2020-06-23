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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.Alcohol;

public class AddNewBottleBox {

    private static final int WIDTH = 500;
    private static final int HEIGHT = 300;
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

        Scene scene = new Scene(generateAlcoholLayout(window), WIDTH, HEIGHT);
        scene.getStylesheets().add(STYLE_SHEET);
        window.setScene(scene);
        window.showAndWait();

        return alcohol;
    }

    private static GridPane generateAlcoholLayout(Stage window) {
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
        GridPane.setConstraints(addAlcohol, 1, 5);
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

        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll("Alcohol", "Wine", "Beer");
        GridPane.setConstraints(choiceBox, 2,5);

        choiceBox.getSelectionModel().selectedItemProperty().addListener( (v, oldVal, newVal) -> {
            switch(newVal) {
                case "Alcohol" :
                    Scene scene = new Scene(generateAlcoholLayout(window), WIDTH, HEIGHT);
                    scene.getStylesheets().add(STYLE_SHEET);
                    window.setScene(scene);
                    break;
                case "Wine" :
                    scene = new Scene(generateWineLayout(window), WIDTH, HEIGHT + 200);
                    scene.getStylesheets().add(STYLE_SHEET);
                    window.setScene(scene);
                    break;
                case "Beer" :
                    scene = new Scene(generateBeerLayout(window), WIDTH, HEIGHT + 200);
                    scene.getStylesheets().add(STYLE_SHEET);
                    window.setScene(scene);
                    break;
            }
        });

        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10,10,10,10));
        layout.setVgap(8);
        layout.setHgap(8);
        layout.getChildren().addAll(nameLabel, abvLabel, countryLabel);
        layout.getChildren().addAll(nameField, abvField, countryField);
        layout.getChildren().addAll(addAlcohol, choiceBox);

        return layout;
    }

    private static GridPane generateWineLayout(Stage window) {
        GridPane layout = generateAlcoholLayout(window);

        Text colourLabel = new Text("Colour: ");
        GridPane.setConstraints(colourLabel, 0,3);
        Text varietyLabel = new Text("Variety: ");
        GridPane.setConstraints(varietyLabel, 0,4);

        TextField colourField = new TextField();
        colourField.setPromptText("Wine colour");
        GridPane.setConstraints(colourField, 1,3);
        TextField varietyField = new TextField();
        varietyField.setPromptText("Grape variety");
        GridPane.setConstraints(varietyField, 1,4);

        /**TODO: change add alcohol button **/
        layout.getChildren().addAll(colourLabel, varietyLabel, colourField, varietyField);

        return layout;
    }

    private static GridPane generateBeerLayout(Stage window) {
        GridPane layout = generateAlcoholLayout(window);

        Text colourLabel = new Text("Colour: ");
        GridPane.setConstraints(colourLabel, 0,3);
        Text ibuLabel = new Text("IBU: ");
        GridPane.setConstraints(ibuLabel, 0,4);

        TextField colourField = new TextField();
        colourField.setPromptText("Beer colour");
        GridPane.setConstraints(colourField, 1,3);
        TextField ibuField = new TextField();
        ibuField.setPromptText("International Bitterness Unit");
        GridPane.setConstraints(ibuField, 1,4);

        /**TODO: change add alcohol button **/
        layout.getChildren().addAll(colourLabel, ibuLabel, colourField, ibuField);

        return layout;
    }

}
