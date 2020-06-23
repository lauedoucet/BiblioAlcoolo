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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.*;

public class AddNewBottleBox {

    private static final int WIDTH = 550;
    private static final int HEIGHT = 300;
    private static final String STYLE_SHEET = "ui/biblioStyle.css";

    private static Text nameLabel, abvLabel, countryLabel;
    private static TextField nameField, abvField, countryField;
    private static Button addAlcohol;
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

    /**
     * Generates a GridPane layout which takes input from user to create an Alcohol object
     * @param window = stage for the layout
     * @return the layout in a GridPane
     */
    private static GridPane generateAlcoholLayout(Stage window) {
        // Basic labels
        nameLabel = new Text("Name: ");
        GridPane.setConstraints(nameLabel, 0,0);
        abvLabel = new Text("ABV: ");
        GridPane.setConstraints(abvLabel, 0,1);
        countryLabel = new Text("Country: ");
        GridPane.setConstraints(countryLabel, 0, 2);

        // Basic fields
        nameField = new TextField();
        nameField.setPromptText("Name of alcohol");
        GridPane.setConstraints(nameField, 1,0);
        abvField = new TextField();
        abvField.setPromptText("Alcohol by volume %");
        GridPane.setConstraints(abvField, 1,1);
        countryField = new TextField();
        countryField.setPromptText("Country of origin");
        GridPane.setConstraints(countryField, 1, 2);

        // Button takes user input and creates a generic Alcohol object
        addAlcohol = new Button("Add");
        GridPane.setConstraints(addAlcohol, 1, 5);
        addAlcohol.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String name = nameField.getText();
                try {
                    double abv = Double.parseDouble(abvField.getText());
                    String country = countryField.getText();
                    alcohol = new Alcohol(name, abv, country);
                    window.close();
                } catch (NumberFormatException e) {
                    AlertBox.display("Invalid Input", "Please enter a number for ABV");
                }
            }
        });

        // User choice for which type of alcohol they want to add, changes the layout accordingly
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll("Alcohol", "Wine", "Beer");
        comboBox.setPromptText("Alcohol");
        comboBox.setOnAction(e -> {
            switch(comboBox.getValue()) {
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
        GridPane.setConstraints(comboBox, 2, 5);

        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10,10,10,10));
        layout.setVgap(8);
        layout.setHgap(8);
        layout.getChildren().addAll(nameLabel, abvLabel, countryLabel);
        layout.getChildren().addAll(nameField, abvField, countryField);
        layout.getChildren().addAll(addAlcohol, comboBox);

        return layout;
    }

    /**
     * Generates a GridPane layout which takes input from user to create a Wine object
     * @param window = stage for the layout
     * @return the layout in a GridPane
     */
    private static GridPane generateWineLayout(Stage window) {
        // Basic layout
        GridPane layout = generateAlcoholLayout(window);

        // Wine specific labels
        Text colourLabel = new Text("Colour: ");
        GridPane.setConstraints(colourLabel, 0,3);
        Text varietyLabel = new Text("Variety*: ");
        GridPane.setConstraints(varietyLabel, 0,4);

        // Wine specific fields, ComboBox for WineColours choices
        ComboBox<String> colourField = new ComboBox<>();
        colourField.setPromptText("Wine colour");
        int i = 0;
        String[] colours = new String[WineColour.values().length];
        for (WineColour wineColour : WineColour.values()) {
            colours[i] = wineColour.name().toLowerCase();
            i++;
        }
        colourField.getItems().addAll(colours);
        GridPane.setConstraints(colourField, 1,3);

        TextField varietyField = new TextField();
        varietyField.setPromptText("Grape variety");
        GridPane.setConstraints(varietyField, 1,4);

        // Update addAlcohol button to create a Wine object
        layout.getChildren().remove(addAlcohol);
        addAlcohol = new Button("Add");
        GridPane.setConstraints(addAlcohol, 1, 5);
        addAlcohol.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    String name = nameField.getText();
                    double abv = Double.parseDouble(abvField.getText());
                    String country = countryField.getText();
                    String colourName = colourField.getValue().toUpperCase();
                    WineColour colour = WineColour.valueOf(colourName);

                    if (!varietyField.getText().isEmpty()) {
                        String variety = varietyField.getText();
                        alcohol = new Wine(name, abv, country, colour, variety);
                        alcohol.displayInfo();
                    } else if (colour != WineColour.NULL) {
                        alcohol = new Wine(name, abv, country, colour);
                        alcohol.displayInfo();
                    } else {
                        alcohol = new Wine(name, abv, country);
                    }

                    window.close();
                } catch (NumberFormatException e) {
                    AlertBox.display("Invalid Input", "Please enter a number for ABV");
                } catch (IllegalArgumentException e) {
                    AlertBox.display("Invalid Input", "oops something went wrong");
                } catch (NullPointerException e) {
                    AlertBox.display("Invalid Input", "Please enter all required information");
                }
            }
        });

        layout.getChildren().addAll(colourLabel, varietyLabel, colourField, varietyField, addAlcohol);

        return layout;
    }

    /**
     * Generates a GridPane layout which takes input from user to create a Beer object
     * @param window = stage for the layout
     * @return the layout in a GridPane
     */
    private static GridPane generateBeerLayout(Stage window) {
        // Basic layout
        GridPane layout = generateAlcoholLayout(window);

        // Beer specific labels
        Text colourLabel = new Text("Colour: ");
        GridPane.setConstraints(colourLabel, 0,3);
        Text ibuLabel = new Text("IBU*: ");
        GridPane.setConstraints(ibuLabel, 0,4);

        // Beer specific fields, ComboBox for BeerColour choices
        ComboBox<String> colourField = new ComboBox<>();
        colourField.setPromptText("Beer colour");
        int i = 0;
        String[] colours = new String[BeerColour.values().length];
        for (BeerColour beerColour : BeerColour.values()) {
            colours[i] = beerColour.name().toLowerCase();
            i++;
        }
        colourField.getItems().addAll(colours);
        GridPane.setConstraints(colourField, 1,3);

        TextField ibuField = new TextField();
        ibuField.setPromptText("International Bitterness Unit");
        GridPane.setConstraints(ibuField, 1,4);

        // Updates addAlcohol button to create a Beer object
        layout.getChildren().remove(addAlcohol);
        addAlcohol = new Button("Add");
        GridPane.setConstraints(addAlcohol, 1, 5);
        addAlcohol.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    String name = nameField.getText();
                    double abv = Double.parseDouble(abvField.getText());
                    String country = countryField.getText();
                    String colourName = colourField.getValue().toUpperCase();
                    BeerColour colour = BeerColour.valueOf(colourName);

                    if (!ibuField.getText().isEmpty()) {
                        int ibu = Integer.parseInt(ibuField.getText());
                        alcohol = new Beer(name, abv, country, colour, ibu);
                        alcohol.displayInfo();
                    } else if (colour != BeerColour.NULL) {
                        alcohol = new Beer(name, abv, country, colour);
                        alcohol.displayInfo();
                    } else {
                        alcohol = new Beer(name, abv, country);
                    }

                    window.close();
                } catch (NumberFormatException e) {
                    AlertBox.display("Invalid Input", "Please enter a number for ABV and IBU");
                } catch (IllegalArgumentException e) {
                    AlertBox.display("Invalid Input", "oops something went wrong");
                } catch (NullPointerException e) {
                    AlertBox.display("Invalid Input", "Please enter all required information");
                }
            }
        });

        layout.getChildren().addAll(colourLabel, ibuLabel, colourField, ibuField, addAlcohol);

        return layout;
    }

}
