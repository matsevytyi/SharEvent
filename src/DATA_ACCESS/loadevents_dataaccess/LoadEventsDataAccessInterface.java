package DATA_ACCESS.loadevents_dataaccess;

import ENTITY.Event;
import ENTITY.User;
import org.jxmapviewer.JXMapViewer;

import java.sql.SQLException;

public interface LoadEventsDataAccessInterface {
     LoadEventsDAO_OutputData getEventsInRange(LoadEventsDAO_InputData inputData) throws SQLException;
    void addEvent(Event event);

    void registerUserForEvent(String username, int event_id);

    Event getEventByPosition(double latitude, double longitude, JXMapViewer mapViewer);

    User getUserByUsername(String username);

    void deleteEvent(int eventId);

    String getEventById(int eventId);
}
