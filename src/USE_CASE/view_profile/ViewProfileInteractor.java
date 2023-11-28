package USE_CASE.view_profile;



import DATA_ACCESS.loadevents_dataaccess.LoadEventsDataAccessInterface;
import ENTITY.Event;

public class ViewProfileInteractor implements ViewProfileInputBoundary {
    private final LoadEventsDataAccessInterface eventDataAccessInterface;
    private final ViewProfileOutputBoundary viewEventPresenter;

    public ViewProfileInteractor(LoadEventsDataAccessInterface eventDataAccessInterface, ViewProfileOutputBoundary viewEventPresenter) {
        this.eventDataAccessInterface = eventDataAccessInterface;
        this.viewEventPresenter = viewEventPresenter;
    }

    @Override
    public void execute(ViewProfileInputData position) {

        Event event = eventDataAccessInterface.getEventByPosition(position.getLatitude(), position.getLongitude(), position.getMapViewer());

        if (event != null) {
            ViewProfileOutputData outputData = new ViewProfileOutputData(
                    event.getEventId(),
                    event.getEventName(),
                    event.getType(),
                    event.getDescription(),
                    event.getEventDate(),
                    event.getEventTime(),
                    event.getCreator().getName(),
                    event.getEventAttendants().toString()

            );;
            viewEventPresenter.successesView(outputData);
        } // додати фейл сітуейшн
    }



}
