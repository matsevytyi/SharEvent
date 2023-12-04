package USE_CASE.view_profile;

import INTERFACE_ADAPTER.view_event.ViewEventState;

public interface ViewProfileOutputBoundary {

    /**
     * Method handles the successful result of the view profile operation by updating the associated view state.
     * @param profileOutputData The output data containing user profile details.
     */
    void successesView(ViewProfileOutputData profileOutputData);

    /**
     * Method handles the failure result of the view profile operation by updating the associated view state with an error message.
     * @param error The error message describing the reason for the failure.
     */
     void prepareFailView(String error);
}