package use_case.delete_event;

public class DeleteEventInputData {
    final private String eventName;

    public DeleteEventInputData(String eventName) {
        this.eventName = eventName;
    }

    public String getEventName() {
        return eventName;
    }
}
