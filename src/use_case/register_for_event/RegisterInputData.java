/**
 * The RegisterInputData class represents the input data for the user registration for an event process.
 */

package USE_CASE.register_for_event;

public class RegisterInputData {

    /**
     * Constructs a new RegisterInputData with the specified event ID and username.
     *
     * @param eventId  The ID of the event for registration.
     * @param userName The username of the user registering for the event.
     */
    public RegisterInputData(int eventId, String userName) {
        this.eventId = eventId;
        this.userName = userName;
    }

    /**
     * Gets the ID of the event for registration.
     *
     * @return The event ID.
     */
    public int getEventId() {
        return eventId;
    }

    /**
     * Gets the username of the user registering for the event.
     *
     * @return The username.
     */
    public String getUserName() {
        return userName;
    }

    private final int eventId;

    private final String userName;
}
