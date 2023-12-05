package USE_CASE.view_profile;


public interface ViewProfileInputBoundary {
    /**
     * Method executes the viewing of user profile details based on the provided ViewProfileInputData.
     * @param viewProfileInputData The input data containing parameters for viewing user profile details.
     */
    void execute(ViewProfileInputData viewProfileInputData);

}

