package APP;

import data_access.DatabaseDAO;
import data_access.LoadEventsDataAccessInterface;
import entity.Event;
import interface_adapter.add_event.AddEventViewModel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import view.AddEventView;
import view.LoadMapView;
import view.ViewManagerModel;

public class main extends Application {
int primaryStageWidth;
    int primaryStageHeight;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setResizable(false);
        primaryStage.setTitle("SharEvent");
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        AddEventViewModel addEventViewModel = new AddEventViewModel();
        DatabaseDAO databaseDAO = new DatabaseDAO();

        LoadMapView loadMapView = MapUseCasesFactory.create(addEventViewModel , databaseDAO, viewManagerModel);
        //LoadMapView loadMapView = new LoadMapView();

        StackPane pane = loadMapView.getStackPane();
        primaryStageWidth = 1600;
        primaryStageHeight = 1200;
        primaryStage.setScene(new Scene(pane, primaryStageWidth, primaryStageWidth));
        primaryStage.show();
    }
}
