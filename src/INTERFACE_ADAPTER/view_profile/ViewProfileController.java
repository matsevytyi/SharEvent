package INTERFACE_ADAPTER.view_profile;


import USE_CASE.view_profile.ViewProfileInputData;
import USE_CASE.view_profile.ViewProfileInputBoundary;
/**
 * The class is the controller for the "view profile" use case.
 * It facilitates the communication between the external interface and the use case interactor.
 * This class receives input parameters related to the username and delegates the execution
 * of the use case to the provided ViewProfileInputBoundary instance.
 */
public class ViewProfileController {



  private final ViewProfileInputBoundary profileInputBoundary;
    /**
     * Constructor with the specified ViewProfileInputBoundary.
     * @param profileInputBoundary The use case input boundary  for the "view profile" functionality.
     */
    public ViewProfileController(ViewProfileInputBoundary profileInputBoundary) {
        this.profileInputBoundary = profileInputBoundary;
    }

    /**
     * Method executes the "view profile" use case with the provided username.
     * @param username The username for which the profile is to be viewed.
     */
    public void execute(String username) {
        ViewProfileInputData inputData= new ViewProfileInputData(username);
       profileInputBoundary.execute(inputData);
    }


}
