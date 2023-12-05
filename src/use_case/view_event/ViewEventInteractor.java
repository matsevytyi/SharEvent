package USE_CASE.view_event;



import DATA_ACCESS.loadevents_dataaccess.LoadEventsDataAccessInterface;
import ENTITY.Event;
import ENTITY.User;

import java.util.List;

public class ViewEventInteractor implements ViewEventInputBoundary {
    private final LoadEventsDataAccessInterface eventDataAccessInterface;
    private final ViewEventOutputBoundary viewEventPresenter;
    /**
     * Constructs a ViewEventInteractor with the specified data access interface and output boundary.
     * @param eventDataAccessInterface The data access interface for loading events.
     * @param viewEventPresenter      The output boundary (presenter) for handling the result of the view event operation.
     */
    public ViewEventInteractor(LoadEventsDataAccessInterface eventDataAccessInterface, ViewEventOutputBoundary viewEventPresenter) {
        this.eventDataAccessInterface = eventDataAccessInterface;
        this.viewEventPresenter = viewEventPresenter;
    }
    /**
     * Method executes the viewing of event details based on the provided ViewEventInputData.
     * @param viewEventInputData The input data containing parameters for viewing event details.
     */
    @Override
    public void execute(ViewEventInputData viewEventInputData) {

        Event event = eventDataAccessInterface.getEventByPosition(viewEventInputData.getLatitude(), viewEventInputData.getLongitude(), viewEventInputData.getMapViewer());

        if (event != null) {
            ViewEventOutputData outputData = new ViewEventOutputData(
                    event.getEventId(),
                    event.getEventName(),
                    event.getType(),
                    event.getDescription(),
                    event.getEventDate(),
                    event.getEventTime(),
                    event.getCreator().getName(),
                    event.getEventAttendants().toString()

            );
            viewEventPresenter.successesView(outputData);
        } else{
            viewEventPresenter.prepareFailView("Something went wrong. Please, try again");
        }
    }



}
