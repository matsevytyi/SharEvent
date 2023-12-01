package USE_CASE.register_for_event;


import DATA_ACCESS.loadevents_dataaccess.LoadEventsDataAccessInterface;


public class RegisterInteractor implements RegisterInputBoundary {


    final LoadEventsDataAccessInterface loadEventsDataAccessInterface;
    final RegisterOutputBoundary registerOutputBoundary;

    public RegisterInteractor(LoadEventsDataAccessInterface loadEventsDataAccessInterface, RegisterOutputBoundary registerOutputBoundary) {
        this.loadEventsDataAccessInterface = loadEventsDataAccessInterface;
        this.registerOutputBoundary= registerOutputBoundary;
    }

    @Override
    public void execute(RegisterInputData registerInputData) {



        loadEventsDataAccessInterface.registerUserForEvent( registerInputData.getUserName(), registerInputData.getEventId());


        registerOutputBoundary.prepareSuccessCase();
    }
}
