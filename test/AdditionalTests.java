import ENTITY.Event;
import ENTITY.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AdditionalTests {

    @Test
    void eventConstructor_CreateEventWithNonNullValues_SuccessfulCreation() {
        // Arrange
        int eventId = 1;
        String eventName = "TestEvent";
        String type = "Type";
        String description = "Description";
        LocalDate eventDate = LocalDate.of(2023, 12, 1);
        LocalTime eventTime = LocalTime.of(12, 30);
        User creator = new User("Creator", "username", "password", "email");
        List<User> eventAttendants = new ArrayList<>();
        double latitude = 1.0;
        double longitude = 2.0;

        // Act
        Event event = new Event(eventId, eventName, type, description, eventDate, eventTime, creator, eventAttendants, latitude, longitude);

        // Assert
        assertNotNull(event);
        assertEquals(eventId, event.getEventId());
        assertEquals(eventName, event.getEventName());
        assertEquals(type, event.getType());
        assertEquals(description, event.getDescription());
        assertEquals(eventDate, event.getEventDate());
        assertEquals(eventTime, event.getEventTime());
        assertEquals(creator, event.getCreator());
        assertEquals(eventAttendants, event.getEventAttendants());
        assertEquals(latitude, event.getLatitude());
        assertEquals(longitude, event.getLongitude());
        assertNotNull(event.getGeoPosition());
    }

    @Test
    void eventConstructor_CreateEventWithNullEventAttendants_SuccessfulCreation() {
        // Arrange
        int eventId = 1;
        String eventName = "TestEvent";
        String type = "Type";
        String description = "Description";
        LocalDate eventDate = LocalDate.of(2023, 12, 1);
        LocalTime eventTime = LocalTime.of(12, 30);
        User creator = new User("Creator", "username", "password", "email");
        double latitude = 1.0;
        double longitude = 2.0;

        // Act
        Event event = new Event(eventId, eventName, type, description, eventDate, eventTime, creator, null, latitude, longitude);

        // Assert
        assertNotNull(event);
        assertEquals(eventId, event.getEventId());
        assertEquals(eventName, event.getEventName());
        assertEquals(type, event.getType());
        assertEquals(description, event.getDescription());
        assertEquals(eventDate, event.getEventDate());
        assertEquals(eventTime, event.getEventTime());
        assertEquals(creator, event.getCreator());
        assertEquals(latitude, event.getLatitude());
        assertEquals(longitude, event.getLongitude());
        assertNotNull(event.getGeoPosition());
    }
}
