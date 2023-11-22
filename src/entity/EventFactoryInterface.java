package entity;

import java.time.LocalDate;;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public interface EventFactoryInterface {

   Event create(int eventId, String eventName, long latitude, long longtitude, LocalDate eventDate, LocalTime eventTime, String description, User creator, List<User> attendants);
}
