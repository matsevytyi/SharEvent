package VIEW;

import APP.MapUseCasesFactory;
import DATA_ACCESS.DatabaseDAO;
import INTERFACE_ADAPTER.ViewManagerModel;
import INTERFACE_ADAPTER.add_event.AddEventController;
import INTERFACE_ADAPTER.add_event.AddEventState;
import INTERFACE_ADAPTER.add_event.AddEventViewModel;
import VIEW.AddEventView;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.beans.PropertyChangeEvent;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AddEventViewTest {
    private DatabaseDAO dao;
    private AddEventViewModel viewModel;
    private AddEventController controller;
    private AddEventView addEventView;
    private ViewManagerModel viewManagerModel;



    @SneakyThrows
    @BeforeEach
    void setUp() {
        new JFXPanel();
        viewModel = new AddEventViewModel();
        dao = new DatabaseDAO();
        viewManagerModel = new ViewManagerModel();
        controller = MapUseCasesFactory.addEventUseCase(viewModel, dao, viewManagerModel);
        addEventView = new AddEventView(viewModel, controller);

    }

    @Test
    void testAddEventButtonClickShowsInformationAlert() {

        Platform.runLater(() -> {
            Stage stage = new Stage();
            stage.setScene(new Scene(addEventView));
            stage.show();

            TextField eventNameInputField = find("#eventNameInputField");
            ComboBox<String> eventTypeComboBox = find("#eventTypeComboBox");
            DatePicker eventDatePicker = find("#eventDatePicker");
            TextField descriptionInputField = find("#descriptionInputField");
            Button addEventButton = find("#addEventButton");


            Platform.runLater(() -> {
                eventNameInputField.setText("Test Event");
                eventTypeComboBox.getSelectionModel().select(0);
                eventDatePicker.getEditor().setText("2023-12-31");
                descriptionInputField.setText("This is a test event description");
                addEventButton.fire();
            });


            sleep(1000);


            assertNotNull(addEventView.getScene());


        });
    }

    private <T> T find(String query) {
        return (T) addEventView.lookup(query);
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}


