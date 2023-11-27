package DATA_ACCESS;

import ENTITY.Event;
import org.jxmapviewer.JXMapViewer;

import java.sql.SQLException;

public interface LoadEventsDataAccessInterface {
     LoadEventsDAO_OutputData getEventsInRange(LoadEventsDAO_InputData inputData) throws SQLException;
    //void addEvent(String event_name,  String type, String event_description, String date, String time,  String creator,  String latitude, String longitude) ;
void addEvent(Event event);

    Event getEventByPosition(double latitude, double longitude, JXMapViewer mapViewer);

    void deleteEvent(int eventId);

    String getEventById(int eventId);
}
