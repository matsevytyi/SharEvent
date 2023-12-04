package USE_CASE.delete_event;

public interface DeleteEventInputBoundary {



    /**
     * Executes the deletion of an event based on the provided Input Data}.
     * @param deleteEventInputData The input data containing information about the event to be deleted.
     */
    void execute(DeleteEventInputData deleteEventInputData);
}
