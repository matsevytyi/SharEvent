package use_case.add_event;

public class AddEventOutputData {

    private final String eventName;

    private boolean useCaseFailed;

    public AddEventOutputData(String eventName,boolean useCaseFailed) {
        this.eventName = eventName;
        this.useCaseFailed = useCaseFailed;
    }

}
