package entity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface EventFactoryInterface {

   Event create(int eventId, String eventName, long latitude, long longtitude, Date eventDate, String description, User creator, List<User> attendants);
}
