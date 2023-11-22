package entity;

import java.time.LocalDate;
import java.util.Date;

public interface EventInterface {
     int getEventId();


    String getEventName();

   long getLatitude();
    long getLongtitude();


    LocalDate getEventDate();

   String getDescription();


     User getCreator();
}
