package DATA_ACCESS;

import ENTITY.Event;

import java.util.List;
import java.util.Set;

public interface FilterEventsDAO {
//    public List<Event> FilterEvents(String type, String latitude1, String latitude2, String longitude1, String longitude2);
    public Set<Event> FilterEvents(String type);
}
