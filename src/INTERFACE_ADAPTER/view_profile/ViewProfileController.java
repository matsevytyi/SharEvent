package INTERFACE_ADAPTER.view_profile;


import USE_CASE.view_event.ViewEventInputData;
import USE_CASE.view_profile.ViewProfileInputData;
import USE_CASE.view_profile.ViewProfileInputBoundary;

public class ViewProfileController {



  private final ViewProfileInputBoundary profileInputBoundary;

    public ViewProfileController(ViewProfileInputBoundary profileInputBoundary) {
        this.profileInputBoundary = profileInputBoundary;
    }


    public void execute(String username) {
        ViewProfileInputData inputData= new ViewProfileInputData(username);
       profileInputBoundary.execute(inputData);
    }


}
