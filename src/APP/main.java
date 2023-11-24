package APP;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import view2.LoadMapView;

public class main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setResizable(false);
        primaryStage.setTitle("SharEvent");

        LoadMapView loadMapView = new LoadMapView();

        StackPane pane = loadMapView.getStackPane();
        primaryStage.setScene(new Scene(pane, 1600, 1200));
        primaryStage.show();
    }
}
