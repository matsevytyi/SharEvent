package USE_CASE.view_event;



import DATA_ACCESS.loadevents_dataaccess.LoadEventsDataAccessInterface;
import ENTITY.Event;
import ENTITY.User;

import java.util.List;

public class ViewEventInteractor implements ViewEventInputBoundary {
    private final LoadEventsDataAccessInterface eventDataAccessInterface;
    private final ViewEventOutputBoundary viewEventPresenter;

    public ViewEventInteractor(LoadEventsDataAccessInterface eventDataAccessInterface, ViewEventOutputBoundary viewEventPresenter) {
        this.eventDataAccessInterface = eventDataAccessInterface;
        this.viewEventPresenter = viewEventPresenter;
    }

    @Override
    public void execute(ViewEventInputData position) {

        Event event = eventDataAccessInterface.getEventByPosition(position.getLatitude(), position.getLongitude(), position.getMapViewer());

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
