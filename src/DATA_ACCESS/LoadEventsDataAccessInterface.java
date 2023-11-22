package DATA_ACCESS;

import java.util.Set;
import ENTITY.Temporary_entites.Event;

public interface LoadEventsDataAccessInterface {
    public Set<Event> getEventsInRange(String latitude1, String latitude2, String longitude1, String longitude2);
}
