package app;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("SharEvent");



        primaryStage.setScene(new Scene(pane, 1600, 1200));
        primaryStage.show();
    }
}
