package USE_CASE.register_for_event;

public class RegisterInputData {
    public RegisterInputData(int eventId, String userName) {
        this.eventId = eventId;
        this.userName = userName;
    }

    public int getEventId() {
        return eventId;
    }

    public String getUserName() {
        return userName;
    }

    private final int eventId;

    private final String userName;
}
