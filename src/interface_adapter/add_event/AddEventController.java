package interface_adapter.add_event;

import entity.User;
import use_case.add_event.AddEventInputBoundary;
import use_case.add_event.AddEventInputData;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class AddEventController {
    final AddEventInputBoundary addEventUseCaseInteractor;
    public AddEventController(AddEventInputBoundary addEventUseCaseInteractor) {
        this.addEventUseCaseInteractor = addEventUseCaseInteractor;
    }

    public void execute(String eventName, String type, String description, LocalDate eventDate, LocalTime eventTime, User creator, double latitude, double longitude) {
        AddEventInputData signupInputData = new AddEventInputData(
                eventName, type, description, eventDate, eventTime, creator, null, latitude, longitude);

        addEventUseCaseInteractor.execute(signupInputData);
    }


}
