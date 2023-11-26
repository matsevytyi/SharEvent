package data_access;

import entity.Event;

import java.sql.SQLException;

public interface LoadEventsDataAccessInterface {
    public LoadEventsDAO_OutputData getEventsInRange(LoadEventsDAO_InputData inputData) throws SQLException;
    //void addEvent(String event_name,  String type, String event_description, String date, String time,  String creator,  String latitude, String longitude) ;
void addEvent(Event event);

    Event getEventById(int id);
}
