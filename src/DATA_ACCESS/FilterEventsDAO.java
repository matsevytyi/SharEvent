package DATA_ACCESS;

public interface FilterEventsDAO {
    public List<EventInterface> FilterEvents(String type, String latitude1, String latitude2, String longitude1, String longitude2);
}
