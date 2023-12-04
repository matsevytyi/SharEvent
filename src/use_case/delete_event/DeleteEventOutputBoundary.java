package USE_CASE.delete_event;

public interface DeleteEventOutputBoundary {
    /**
     * Prepares the view for a successful deletion of an event based on the provided DeleteEventOutputData.
     * @param event The output data containing information about the deleted event.
     */
    void prepareSuccessCase(DeleteEventOutputData event);
    /**
     * Prepares the view for a failed deletion of an event based on the provided error message.
     * @param error The error message indicating the reason for the failed deletion.
     */
    void prepareFailCase(String error);
}
