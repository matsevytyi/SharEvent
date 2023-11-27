package APP;

import data_access.DatabaseDAO;
import data_access.LoadEventsDataAccessInterface;
import entity.Event;
import entity.User;
import interface_adapter.add_event.AddEventViewModel;
import interface_adapter.view_event.ViewEventViewModel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;
import view.AddEventView;
import view.LoadMapView;
import view.ViewManagerModel;

import java.time.LocalDate;
import java.time.LocalTime;

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
        ViewEventViewModel viewEventViewModel = new ViewEventViewModel();
        DatabaseDAO databaseDAO = new DatabaseDAO();

        LoadMapView loadMapView = MapUseCasesFactory.create(addEventViewModel , viewEventViewModel, databaseDAO, viewManagerModel);
        //LoadMapView loadMapView = new LoadMapView();

        StackPane pane = loadMapView.getStackPane();
        primaryStageWidth = 1600;
        primaryStageHeight = 1200;
        primaryStage.setScene(new Scene(pane, primaryStageWidth, primaryStageWidth));
//        LocalDate localDate = LocalDate.of(2023, 12, 3);
//        LocalTime localTime = LocalTime.of(12, 12, 12);
//        Event event = new Event("music show", "music", "jdhjvhf", localDate, localTime, new User("ff","ff","ff", "ff"), null, 43.66171701890102, -79.40012991428375
//        );

        primaryStage.show();
    }
}
