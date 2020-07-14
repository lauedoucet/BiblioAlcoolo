/*
 *
 *   Copyright (C) 2020 Laurence Doucet
 *   Application that allows the management and record of alcohol
 *   Controller class for the library of alcohol using JavaFX
 *
 */
package ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;

    public void handleWelcomeButton() throws IOException {
        /*
        Parent root = FXMLLoader.load(getClass().getResource("librariespage.fxml"));
        BiblioAlcoolo.setWindowScene(new Scene(root, WIDTH, HEIGHT));
        */
    }

    public void handleWelcomeClose() {
        System.out.println("Closing program...");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // load user info from save file
        BiblioAlcoolo.getUser().loadFile();
    }
}
