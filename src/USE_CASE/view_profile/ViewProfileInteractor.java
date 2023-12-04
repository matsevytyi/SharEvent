package USE_CASE.view_profile;



import DATA_ACCESS.loadevents_dataaccess.LoadEventsDataAccessInterface;
import ENTITY.Event;
import ENTITY.User;

public class ViewProfileInteractor implements ViewProfileInputBoundary {
    private final LoadEventsDataAccessInterface eventDataAccessInterface;
    private final ViewProfileOutputBoundary viewEventPresenter;
    /**
     * Constructs a ViewProfileInteractor with the specified data access interface for user data
     * and the output boundary (presenter) for handling the result of the view profile operation.
     * @param eventDataAccessInterface The data access interface for loading user profile data.
     * @param viewEventPresenter The output boundary (presenter) for handling the result of the view profile operation.
     */
    public ViewProfileInteractor(LoadEventsDataAccessInterface eventDataAccessInterface, ViewProfileOutputBoundary viewEventPresenter) {
        this.eventDataAccessInterface = eventDataAccessInterface;
        this.viewEventPresenter = viewEventPresenter;
    }
    /**
     * Method executes the viewing of user profile details based on the provided ViewProfileInputData.
     * @param username The input data containing parameters for viewing user profile details.
     */
    @Override
    public void execute(ViewProfileInputData username) {

       User user = eventDataAccessInterface.getUserByUsername(username.getUsername());

        if (user != null) {
            ViewProfileOutputData outputData = new ViewProfileOutputData(
                    user.getUsername(),
                    user.getName(),
                    user.getEmail(),
                    user.getRegisteredEvents(),
                    user.getHostedEvents()
            );;
            viewEventPresenter.successesView(outputData);
        } else{
            viewEventPresenter.prepareFailView("Something went wrong. Please, try again");
        }
    }



}
