package entity;

import java.time.LocalDate;
import java.util.Date;

public interface EventInterface {
     int getEventId();


    String getEventName();

  double getLatitude();
    double getLongitude();


    LocalDate getEventDate();

   String getDescription();


     UserInterface getCreator();
}
