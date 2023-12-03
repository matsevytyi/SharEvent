import static org.junit.jupiter.api.Assertions.*;


import APP.MapUseCasesFactory;
import DATA_ACCESS.DatabaseDAO;
import DATA_ACCESS.loadevents_dataaccess.LoadEventsDataAccessInterface;
import ENTITY.EventFactory;
import ENTITY.User;
import INTERFACE_ADAPTER.ViewManagerModel;
import INTERFACE_ADAPTER.add_event.AddEventController;
import INTERFACE_ADAPTER.add_event.AddEventPresenter;
import INTERFACE_ADAPTER.add_event.AddEventState;
import INTERFACE_ADAPTER.add_event.AddEventViewModel;
import USE_CASE.add_event.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AddEventTests  {

    @Test
    void testAddEventUseCase()  {
        // Create real instance
        DatabaseDAO dataAccessInterface  = new DatabaseDAO();

        User user = new User("ff", "ff", "ff", "ff");
        // Set up real user input
        AddEventInputData inputData = new AddEventInputData(
                "Event Name",
                "Event Type",
                "Event Description",
                LocalDate.now() ,
                LocalTime.now(),
                user, // Your actual user object
                Collections.emptyList(),
                43, -79
        );

        // Create a real presenter that captures the output
        CapturingAddEventPresenter capturingPresenter = new CapturingAddEventPresenter();

        // Execute the use case
        AddEventInteractor addEventInteractor = new AddEventInteractor(dataAccessInterface, capturingPresenter, new EventFactory());
        addEventInteractor.execute(inputData);

        // Verify the captured output
        AddEventOutputData capturedOutput = capturingPresenter.getCapturedOutput();
        Assertions.assertNotNull(capturedOutput);
        Assertions.assertFalse(capturedOutput.isUseCaseFailed());
        Assertions.assertEquals("Event Name", capturedOutput.getEventName());






    }

    @Test
    void testExecute_NullEventName() {
        // Arrange
        LoadEventsDataAccessInterface eventDataAccessInterface = new DatabaseDAO();
        CapturingAddEventPresenter addEventPresenter = new CapturingAddEventPresenter();
                EventFactory eventFactory = new EventFactory();
        AddEventInteractor addEventInteractor = new AddEventInteractor(eventDataAccessInterface, addEventPresenter, eventFactory);
        User user = new User("ff", "ff", "ff", "ff");
        AddEventInputData inputData = new AddEventInputData(
                null,
                "Event Type",
                "Event Description",
                LocalDate.now(),
                LocalTime.now(),
                user,
                Collections.emptyList(),
                43, -79
        );

        addEventInteractor.execute(inputData);
        Assertions.assertNotNull(addEventPresenter.getCapturedOutput());
        Assertions.assertTrue(addEventPresenter.getCapturedOutput().isUseCaseFailed());


        String errorMessage = addEventPresenter.getCapturedOutput().getEventName();
        Assertions.assertNotNull(errorMessage);
        Assertions.assertTrue(errorMessage.contains("Enter name of event"));

        Assertions.assertEquals(inputData.getEventAttendants().size(),0);


        AddEventInputData inputData2 = new AddEventInputData(
                "cool",
                null,
                "Event Description",
                LocalDate.now(),
                LocalTime.now(),
                user,
                Collections.emptyList(),
                43, -79
        );

        addEventInteractor.execute(inputData2);
        Assertions.assertNotNull(addEventPresenter.getCapturedOutput());
        Assertions.assertTrue(addEventPresenter.getCapturedOutput().isUseCaseFailed());


        errorMessage = addEventPresenter.getCapturedOutput().getEventName();
        Assertions.assertNotNull(errorMessage);
        Assertions.assertTrue(errorMessage.contains("Add type of event, please"));


        AddEventInputData inputData3 = new AddEventInputData(
                "cool",
                "Sport",
                null,
                LocalDate.now(),
                LocalTime.now(),
                user,
                Collections.emptyList(),
                43, -79
        );

        addEventInteractor.execute(inputData3);


        errorMessage = addEventPresenter.getCapturedOutput().getEventName();
        Assertions.assertTrue(errorMessage.contains("Enter description of event, please"));

        AddEventInputData inputData4 = new AddEventInputData(
                "cool",
                "Sport",
                "ndjnjdn",
                null,
                LocalTime.now(),
                user,
                Collections.emptyList(),
                43, -79
        );

        addEventInteractor.execute(inputData4);

        errorMessage = addEventPresenter.getCapturedOutput().getEventName();
        Assertions.assertNotNull(errorMessage);
        Assertions.assertTrue(errorMessage.contains("Enter date,please"));
LocalDate l = LocalDate.of(2002,10,7);
        AddEventInputData inputData5 = new AddEventInputData(
                "cool",
                "Sport",
                "ndjnjdn",
               l,
                LocalTime.now(),
                user,
                Collections.emptyList(),
                43, -79
        );

        addEventInteractor.execute(inputData5);

        errorMessage = addEventPresenter.getCapturedOutput().getEventName();
        Assertions.assertTrue(errorMessage.contains("Enter right date, please"));
    }


    private static class CapturingAddEventPresenter implements AddEventOutputBoundary {
        private AddEventOutputData capturedOutput;

        @Override
        public void prepareSuccessView(AddEventOutputData event) {
            this.capturedOutput = event;
        }

        @Override
        public void prepareFailView(String error) {
            this.capturedOutput = new AddEventOutputData(error, true);
        }

        public AddEventOutputData getCapturedOutput() {
            return capturedOutput;
        }
    }
}
