package ENTITY;

import java.time.LocalDate;;
import java.time.LocalTime;
import java.util.List;

public interface EventFactoryInterface {
 /**
  * Creates and returns a new instance of the Event class with the parameters.
  * @param eventName  The name of the event.
  * @param type       The type/category of the event.
  * @param description The description of the event.
  * @param eventDate   The date of the event.
  * @param eventTime   The time of the event.
  * @param creator     The user who created the event.
  * @param attendants  The list of users attending the event.
  * @param latitude    The latitude of the event location.
  * @param longitude   The longitude of the event location.
  * @return A new instance of the Event class.
  */
 Event create(String eventName, String type, String description,  LocalDate eventDate, LocalTime eventTime,  User creator, List<User> attendants, double latitude, double longitude);
}
