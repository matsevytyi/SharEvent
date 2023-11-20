package database;

import javafx.scene.chart.PieChart;

import java.sql.PreparedStatement;
import java.sql.SQLException;


public class DatabaseDAO {
    Database database = new Database();

    public void addEvent(String event_name, String event_description, String type, String time, String date, String creator, String longitude, String latitude) {

        //TODO: implement additional logic if needed (fof holding unappropriate cases)

        String query = "INSERT INTO events (event_name, event_description, type, time, date, creator, longitude, latitude) VALUES (" + event_name + ", " + event_description + ", " + type + ", " + time + ", " + date + ", " + creator + ", " + longitude + ", " + latitude + ")";

        database.executeQuery(query, true);
    }

    public void deleteEvent(String event_id) {

        //TODO: implement additional logic if needed (for holding unappropriate cases)
        // + only event creator can perform such operation for particular event

        String query = "DELETE FROM events WHERE event_id = " + event_id;

        database.executeQuery(query, true);
    }

    public void updateEvent(String old_event_name, String old_event_time, String old_event_date, String event_name, String event_description, String type, String time, String date, String creator, String longitude, String latitude) {

        //TODO: implement additional logic if needed (for holding unappropriate cases)
        // + only event creator can perform such operation for particular event
        // + consider adding event_id to the event entity and simplify the query

        String query = "update public.event" +
                " set " +
                "  event_name = " + event_name +
                "  description = " + event_description +
                "  type = " + type +
                "  time = " + time +
                "  date = " + date +
                "  longitude = " + longitude +
                "  latitude = " + latitude +
                " where " +
                "  event_name = " + old_event_name +
                "  and time = " + old_event_time +
                "  and date = " + old_event_date +
                ";";

        database.executeQuery(query, true);

    }

   










}
