package entity;

import java.time.LocalDateTime;
import java.util.Date;

public interface EventFactoryInterface {

   Event create(int eventId, String eventName, long latitude, long longtitude, Date eventDate, String description, User creator);
}
