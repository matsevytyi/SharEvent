package ENTITY;

import java.time.LocalDate;;
import java.time.LocalTime;
import java.util.List;

public interface EventFactoryInterface {

 Event create(String eventName, String type, String description,  LocalDate eventDate, LocalTime eventTime,  User creator, List<User> attendants, double latitude, double longitude);
}
