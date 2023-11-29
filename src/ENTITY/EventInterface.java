package ENTITY;

import java.time.LocalDate;

public interface EventInterface {
     int getEventId();


    String getEventName();

  double getLatitude();
    double getLongitude();


    LocalDate getEventDate();

   String getDescription();


     UserInterface getCreator();
}
