package USE_CASE.view_event;



public interface ViewEventInputBoundary {

    /**
     * Executes the viewing of event details based on the provided ViewEventInputData.
     * @param viewEventInputData The input data containing parameters for viewing event details.
     */
    void execute(ViewEventInputData viewEventInputData);

}

