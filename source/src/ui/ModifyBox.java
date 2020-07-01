package ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.*;

public class ModifyBox extends AddNewBox {
    private static Button modAlcohol;
    private static TextField nameField, abvField, countryField;

    public static void modifyLibrary(Library library) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Modify library");

        Scene scene = new Scene(generateLibraryLayout(window, library), getWIDTH(), getHEIGHT());
        scene.getStylesheets().add(getStyleSheet());
        window.setScene(scene);
        window.showAndWait();
    }

    /**
     * @Overload of AddNewBox.generateLibraryLayout
     * @param window
     * @param library
     * @return
     */
    public static GridPane generateLibraryLayout(Stage window, Library library) {
        Label nameLabel = new Label("Name: ");
        nameLabel.setId("mandatory-label");
        GridPane.setConstraints(nameLabel, 0,0);

        TextField nameField = new TextField();
        nameField.setText(library.getName());
        GridPane.setConstraints(nameField, 1,0);

        javafx.scene.control.Button modLibrary = new Button("Modify");
        GridPane.setConstraints(modLibrary, 1, 5);
        modLibrary.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String name = nameField.getText();
                library.setName(name);
                window.close();
            }
        });
        modLibrary.setId("add-button");

        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10,10,10,10));
        layout.setVgap(8);
        layout.setHgap(8);
        layout.getChildren().addAll(nameLabel, nameField, modLibrary);

        return layout;
    }

    public static void modifyAlcohol(Alcohol alcohol) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Modify alcohol");

        if (alcohol.getClass() == Wine.class) {
            Wine wine = (Wine) alcohol;
            Scene scene = new Scene(generateWineLayout(window, wine), getWIDTH(), getHEIGHT() + 50);
            scene.getStylesheets().add(getStyleSheet());
            window.setScene(scene);
            window.showAndWait();
        } else if (alcohol.getClass() == Beer.class) {
            Beer beer = (Beer) alcohol;
            Scene scene = new Scene(generateBeerLayout(window, beer), getWIDTH(), getHEIGHT() + 50);
            scene.getStylesheets().add(getStyleSheet());
            window.setScene(scene);
            window.showAndWait();
        } else {
            Scene scene = new Scene(generateAlcoholLayout(window, alcohol), getWIDTH(), getHEIGHT());
            scene.getStylesheets().add(getStyleSheet());
            window.setScene(scene);
            window.showAndWait();
        }
    }

    private static GridPane generateAlcoholLayout(Stage window, Alcohol alcohol) {
        // Basic labels
        Label nameLabel = new Label("Name: ");
        nameLabel.setId("mandatory-label");
        GridPane.setConstraints(nameLabel, 0,0);
        Label abvLabel = new Label("ABV: ");
        abvLabel.setId("mandatory-label");
        GridPane.setConstraints(abvLabel, 0,1);
        Label countryLabel = new Label("Country: ");
        countryLabel.setId("mandatory-label");
        GridPane.setConstraints(countryLabel, 0, 2);

        // Basic fields
        nameField = new TextField();
        nameField.setText(alcohol.getName());
        GridPane.setConstraints(nameField, 1,0);
        abvField = new TextField();
        abvField.setText("" + alcohol.getABV());
        GridPane.setConstraints(abvField, 1,1);
        countryField = new TextField();
        countryField.setText(alcohol.getCountry());
        GridPane.setConstraints(countryField, 1, 2);

        // Button takes user input and creates a generic Alcohol object
        modAlcohol = new Button("Modify");
        GridPane.setConstraints(modAlcohol, 1, 5);
        modAlcohol.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String name = nameField.getText();
                try {
                    double abv = Double.parseDouble(abvField.getText());
                    String country = countryField.getText();
                    alcohol.setName(name);
                    alcohol.setABV(abv);
                    alcohol.setCountry(country);
                    window.close();
                } catch (NumberFormatException e) {
                    AlertBox.display("Invalid Input", "Please enter a number for ABV");
                }
            }
        });
        modAlcohol.setId("add-button");


        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10,10,10,10));
        layout.setVgap(8);
        layout.setHgap(8);
        layout.getChildren().addAll(nameLabel, abvLabel, countryLabel);
        layout.getChildren().addAll(nameField, abvField, countryField);
        layout.getChildren().addAll(modAlcohol);

        return layout;
    }

    private static GridPane generateWineLayout(Stage window, Wine wine) {
        // Basic layout
        GridPane layout = generateAlcoholLayout(window, wine);

        // Wine specific labels
        Label colourLabel = new Label("Colour: ");
        colourLabel.setId("mandatory-label");
        GridPane.setConstraints(colourLabel, 0,3);
        Label varietyLabel = new Label("Variety: ");
        GridPane.setConstraints(varietyLabel, 0,4);

        // Wine specific fields, ComboBox for WineColours choices
        ComboBox<String> colourField = new ComboBox<>();
        colourField.setValue(wine.getColour().name());
        int i = 0;
        String[] colours = new String[WineColour.values().length];
        for (WineColour wineColour : WineColour.values()) {
            colours[i] = wineColour.name().toLowerCase();
            i++;
        }
        colourField.getItems().addAll(colours);
        GridPane.setConstraints(colourField, 1,3);

        TextField varietyField = new TextField();
        if (wine.getVariety().equals("")) {
            varietyField.setPromptText("Grape variety");
        } else {
            varietyField.setText(wine.getVariety());
        }
        GridPane.setConstraints(varietyField, 1,4);

        // Update modAlcohol button to update Wine object
        layout.getChildren().remove(modAlcohol);
        modAlcohol = new Button("Modify");
        GridPane.setConstraints(modAlcohol, 1, 5);
        modAlcohol.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    String name = nameField.getText();
                    double abv = Double.parseDouble(abvField.getText());
                    String country = countryField.getText();
                    String colourName = colourField.getValue().toUpperCase();
                    WineColour colour = WineColour.valueOf(colourName);
                    String variety = varietyField.getText();

                    wine.setName(name);
                    wine.setABV(abv);
                    wine.setCountry(country);
                    wine.setColour(colour);
                    wine.setVariety(variety);

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
        modAlcohol.setId("add-button");

        layout.getChildren().addAll(colourLabel, varietyLabel, colourField, varietyField, modAlcohol);

        return layout;
    }

    private static GridPane generateBeerLayout(Stage window, Beer beer) {
        // Basic layout
        GridPane layout = generateAlcoholLayout(window, beer);
        layout.getChildren().remove(1,0);

        // Beer specific labels
        Label colourLabel = new Label("Colour: ");
        colourLabel.setId("mandatory-label");
        GridPane.setConstraints(colourLabel, 0,3);
        Label ibuLabel = new Label("IBU*: ");
        GridPane.setConstraints(ibuLabel, 0,4);

        // Beer specific fields, ComboBox for BeerColour choices
        ComboBox<String> colourField = new ComboBox<>();
        colourField.setValue(beer.getColour().name());
        int i = 0;
        String[] colours = new String[BeerColour.values().length];
        for (BeerColour beerColour : BeerColour.values()) {
            colours[i] = beerColour.name().toLowerCase();
            i++;
        }
        colourField.getItems().addAll(colours);
        GridPane.setConstraints(colourField, 1,3);

        TextField ibuField = new TextField();
        ibuField.setText("" + beer.getIBU());
        GridPane.setConstraints(ibuField, 1,4);

        // Updates addAlcohol button to create a Beer object
        layout.getChildren().remove(modAlcohol);
        modAlcohol = new Button("Modify");
        GridPane.setConstraints(modAlcohol, 1, 5);
        modAlcohol.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    String name = nameField.getText();
                    double abv = Double.parseDouble(abvField.getText());
                    String country = countryField.getText();
                    String colourName = colourField.getValue().toUpperCase();
                    BeerColour colour = BeerColour.valueOf(colourName);

                    beer.setName(name);
                    beer.setABV(abv);
                    beer.setCountry(country);
                    beer.setColour(colour);
                    if (!ibuField.getText().isEmpty()) {
                        int ibu = Integer.parseInt(ibuField.getText());
                        beer.setIBU(ibu);
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
        modAlcohol.setId("add-button");
        layout.getChildren().addAll(colourLabel, ibuLabel, colourField, ibuField, modAlcohol);

        return layout;
    }

}
