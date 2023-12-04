package INTERFACE_ADAPTER.add_event;

import ENTITY.User;
import USE_CASE.add_event.AddEventInputBoundary;
import USE_CASE.add_event.AddEventInputData;


import java.time.LocalDate;
import java.time.LocalTime;
/**
 * The {@code AddEventController} class serves as the controller for the "add event" use case.
 * It facilitates the communication between the external interface and the use case interactor.
 * This class receives input parameters related to the event and delegates the execution
 * of the use case to the provided {@link AddEventInputBoundary} instance.
 */
public class AddEventController {
    final AddEventInputBoundary addEventUseCaseInteractor;
    public AddEventController(AddEventInputBoundary addEventUseCaseInteractor) {
        this.addEventUseCaseInteractor = addEventUseCaseInteractor;
    }
    /**
     * Executes the "add event" use case with the provided parameters.
     *
     * @param eventName   The name of the event.
     * @param type        The type or category of the event.
     * @param description A description of the event.
     * @param eventDate   The date of the event (in {@link LocalDate} format).
     * @param eventTime   The time of the event (in {@link LocalTime} format).
     * @param creator     The user who is creating the event (in {@link User} format).
     * @param latitude    The latitude coordinate of the event location.
     * @param longitude   The longitude coordinate of the event location.
     */
    public void execute(String eventName, String type, String description, LocalDate eventDate, LocalTime eventTime, User creator, double latitude, double longitude) {
        AddEventInputData signupInputData = new AddEventInputData(
                eventName, type, description, eventDate, eventTime, creator, null, latitude, longitude);

        addEventUseCaseInteractor.execute(signupInputData);
    }


}
