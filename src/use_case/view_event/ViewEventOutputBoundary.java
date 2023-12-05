package USE_CASE.view_event;

import USE_CASE.delete_event.DeleteEventOutputData;

public interface ViewEventOutputBoundary {
    /**
     * Prepares the view for a successful showing of an event based on the provided DeleteEventOutputData.
     * @param viewEventOutputData The output data containing information about the viewing event.
     */
    void successesView(ViewEventOutputData viewEventOutputData);


    /**
     * Prepares the view for a failed view of an event based on the provided error message.
     * @param error The error message indicating the reason for the failed viewing.
     */
    void prepareFailView(String error);
}