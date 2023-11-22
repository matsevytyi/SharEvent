package use_case.view_event;

public class ViewEventInputData {

    public int getEventId() {
        return eventId;
    }

    private final int eventId;

    public ViewEventInputData(int eventId) {
        this.eventId = eventId;
    }
}
