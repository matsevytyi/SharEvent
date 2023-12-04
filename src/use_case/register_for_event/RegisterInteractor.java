/**
 * The RegisterInteractor class represents the business logic for user registration for an event.
 */

package USE_CASE.register_for_event;


import DATA_ACCESS.loadevents_dataaccess.LoadEventsDataAccessInterface;


public class RegisterInteractor implements RegisterInputBoundary {


    final LoadEventsDataAccessInterface loadEventsDataAccessInterface;
    final RegisterOutputBoundary registerOutputBoundary;

    /**
     * Constructs a new RegisterInteractor with the specified dependencies.
     *
     * @param loadEventsDataAccessInterface The data access interface for loading events.
     * @param registerOutputBoundary       The presenter for handling output data.
     */
    public RegisterInteractor(LoadEventsDataAccessInterface loadEventsDataAccessInterface, RegisterOutputBoundary registerOutputBoundary) {
        this.loadEventsDataAccessInterface = loadEventsDataAccessInterface;
        this.registerOutputBoundary= registerOutputBoundary;
    }


    /**
     * Executes the user registration for an event based on the provided input data.
     *
     * @param registerInputData The input data containing the event ID and username for registration.
     */
    @Override
    public void execute(RegisterInputData registerInputData) {

        loadEventsDataAccessInterface.registerUserForEvent( registerInputData.getUserName(), registerInputData.getEventId());
        registerOutputBoundary.prepareSuccessCase();
    }
}
