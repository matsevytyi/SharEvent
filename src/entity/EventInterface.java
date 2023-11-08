package entity;

import java.util.Date;

public interface EventInterface {
     int getEventId();


    String getEventName();

   long getLatitude();
    long getLongtitude();


    Date getEventDate();

   String getDescription();


     User getCreator();
}
