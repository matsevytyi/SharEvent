package USE_CASE.add_event;

public class AddEventOutputData {

    public String getEventName() {
        return eventName;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }

    private final String eventName;
    private boolean useCaseFailed;

    public AddEventOutputData(String eventName,boolean useCaseFailed) {
        this.eventName = eventName;
        this.useCaseFailed = useCaseFailed;
    }

}
