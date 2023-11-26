package use_case.view_event;


import data_access.LoadEventsDataAccessInterface;
import entity.Event;

public class ViewEventInteractor implements ViewEventInputBoundary {
    private final LoadEventsDataAccessInterface eventDataAccessInterface;
    private final ViewEventOutputBoundary viewEventPresenter;

    public ViewEventInteractor(LoadEventsDataAccessInterface eventDataAccessInterface, ViewEventOutputBoundary viewEventPresenter) {
        this.eventDataAccessInterface = eventDataAccessInterface;
        this.viewEventPresenter = viewEventPresenter;
    }

    @Override
    public void execute(ViewEventInputData eventId) {
        // Retrieve event details from the database
        Event event = eventDataAccessInterface.getEventById(eventId.getEventId());

        if (event != null) {
            // Map the event details to a format suitable for presentation
            ViewEventOutputData outputData = mapToOutputData(event);
            viewEventPresenter.successesView(outputData);
        } // додати фейл сітуейшн
    }
    private ViewEventOutputData mapToOutputData(Event event) {
        // Map the Event object to ViewEventOutputData or EventDetails
        // This could involve extracting relevant information from the Event entity
        return new ViewEventOutputData(
                        event.getEventName(),
                        event.getDescription(),
                event.getEventTime(),
                event.getEventDate(),
                event.getCreator().getName(),
                event.getEventAttendants().toString()

        );
    }

}
