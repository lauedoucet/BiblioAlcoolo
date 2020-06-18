import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {
    private static final int WIDTH = 250;
    private static final int HEIGHT = 200;

    public static void display(String title, String message) {
        Stage window = new Stage();
        // Block user interaction with other windows until this is dealt with
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);

        Label label = new Label(message);
        Button closeButton = new Button("ok");
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, WIDTH, HEIGHT);
        window.setScene(scene);
        window.showAndWait();
    }
}
