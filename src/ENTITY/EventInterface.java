package ENTITY;

import java.time.LocalDate;

public interface EventInterface {
    /**
     * Gets the unique identifier for the event.
     * @return The event Id.
     */
     int getEventId();

    /**
     * Gets the name of the event.
     * @return The event name.
     */
    String getEventName();
    /**
     * Gets the latitude of the event location.
     * @return The latitude.
     */
  double getLatitude();
    /**
     * Gets the longitude of the event location.
     * @return The latitude.
     */
    double getLongitude();

    /**
     * Gets the date of the event.
     * @return The event date.
     */
    LocalDate getEventDate();
    /**
     * Gets the description of the event.
     * @return The event description.
     */
   String getDescription();

    /**
     * Gets the creator of the event.
     * @return The user who created the event.
     */
     UserInterface getCreator();
}
