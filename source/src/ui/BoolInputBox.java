package ui;/*
 *
 *   Copyright (C) 2020 Laurence Doucet
 *   App that allows the management and record of alcohol
 *   Class represents a pop-window which gets a boolean input from the user
 *
 */

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class BoolInputBox {

    private static final int WIDTH = 250;
    private static final int HEIGHT = 200;
    private static boolean answer;

    /**
     * Sets up a pop-up window that asks user for yes/no question
     * @param title = title of window
     * @param message = yes/no question to user
     * @return answer to the pop-up window
     */
    public static boolean display(String title, String message) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);

        Label label = new Label(message);
        Button yesButton = new Button("yes");
        yesButton.setOnAction(e -> {
            answer = true;
            window.close();
        });
        Button noButton = new Button("no");
        noButton.setOnAction(e -> {
            answer = false;
            window.close();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, yesButton, noButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, WIDTH, HEIGHT);
        window.setScene(scene);
        window.showAndWait();

        return answer;
    }
}