package interface_adapter.add_event;

import use_case.add_event.AddEventInputBoundary;
import use_case.add_event.AddEventInputData;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class AddEventController {
    final AddEventInputBoundary addEventUseCaseInteractor;
    public AddEventController(AddEventInputBoundary addEventUseCaseInteractor) {
        this.addEventUseCaseInteractor = addEventUseCaseInteractor;
    }

    public void execute(String eventName, long latitude, long longtitude, LocalDate eventDate, LocalTime eventTime, String description) {
        AddEventInputData signupInputData = new AddEventInputData(
                eventName, latitude, eventTime, longtitude, eventDate, description);

        addEventUseCaseInteractor.execute(signupInputData);
    }

    public void executeMapClick(double latitude, double longitude) {
        // Handle map clicks if needed
        // For example, show a dialog for additional event details
        // or navigate to the screen where the user can provide more information.
    }
}
