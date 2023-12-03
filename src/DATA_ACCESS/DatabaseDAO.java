/**
 * DatabaseDAO provides data access operations for operations with data
 */

package DATA_ACCESS;

import java.awt.geom.Point2D;
import java.sql.ResultSet;
import java.sql.SQLException;


import DATA_ACCESS.loadevents_dataaccess.LoadEventsDAO_InputData;
import DATA_ACCESS.loadevents_dataaccess.LoadEventsDAO_OutputData;
import DATA_ACCESS.loadevents_dataaccess.LoadEventsDataAccessInterface;
import ENTITY.EventInterface;
import ENTITY.User;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import ENTITY.Event;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.GeoPosition;


public class DatabaseDAO implements LoadEventsDataAccessInterface, UserLoginDataAccessInterface, UserSignUpDataAccessInterface, FilterEventsDAO, SearchEventsDAO {
    Database database = Database.getInstance();

    /**
     * Deletes an event from the database based on its ID.
     *
     * @param event_id The ID of the event to be deleted.
     */
    public void deleteEvent(int event_id) {

        String query = "DELETE FROM public.event WHERE id_event = " + event_id;

        database.executeQuery(query, true);

    }

    /**
     * Retrieves the name of an event based on its ID.
     *
     * @param eventId The ID of the event to retrieve.
     * @return The name of the event with the specified ID.
     */
    @Override
    public String getEventById(int eventId) {
        String query = "select * from public.event where id_event = " + eventId;

        Event event = database.executeQueryEvent(query, eventId);

        return event.getEventName();
    }

    /*public void updateEvent(String old_event_name, String old_event_time, String old_event_date, String event_name, String event_description, String type, String time, String date, String creator, String longitude, String latitude) {

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

    }*/

    /**
     * Registers a user for a specific event.
     *
     * @param username The username of the user to register.
     * @param event_id The ID of the event to register for.
     */
    public void registerUserForEvent(String username, int event_id) {

        String query = "INSERT INTO public.attendedEvents (visitor, event) VALUES (?, ?)";

        database.executeQuery(query, true, username, event_id);
    }

    public void unregisterUserFromEvent(String username, String event_id) {

        String query = "DELETE FROM public.attendedEvents WHERE visitor = " + username + " AND event = " + event_id;

        database.executeQuery(query, true);

    }

    /**
     * Filters events based on the provided type.
     *
     * @param type The type of events to filter.
     * @return A set of events matching the specified type.
     */
    public Set<Event> FilterEvents(String type) {

//        String query = "SELECT * " +
//                "FROM public.event " +
//                "WHERE type = " + type +
//                " AND " +
//                "EVENTLAT > " + latitude1 +" AND EVENTLAT < " + latitude2 +
//                " AND " +
//                "EVENTLONG > " + longitude1 +" AND EVENTLONG < " + longitude2 +
//                ";";
        String query = "SELECT * " +
                "FROM public.event ";
//                +
//                "WHERE type = " + type;

        Set<Event> eventList = (Set<Event>) database.executeQueryEvent(query);


        return (Set<Event>) eventList;

    }

    /**
     * Searches for events based on the provided event name.
     *
     * @param event_name The name of the event to search for.
     * @return A set of events matching the specified event name.
     */
    public Set<Event> SearchEvent(String event_name) {

//        String query = "SELECT * " +
//                "FROM public.events " +
//                "WHERE event_name = " + event_name +
//                ";";


        String query = "SELECT * " + "FROM public.event ";

        Set<Event> eventList = (Set<Event>) database.executeQueryEvent(query);

        return (Set<Event>) eventList;
    }

    /**
     * Retrieves events within a specified range.
     *
     * @param inputData Input data specifying the range.
     * @return Output data containing events within the specified range.
     * @throws SQLException If a SQL-related error occurs during data retrieval.
     */
    public LoadEventsDAO_OutputData getEventsInRange(LoadEventsDAO_InputData inputData) throws SQLException {

        String query = "SELECT * FROM public.event;";
        Set<Event> events = (Set<Event>) database.executeQueryEvent(query);

        return new LoadEventsDAO_OutputData(events);
    }


    /**
     * Adds a new event to the database.
     *
     * @param event The event to be added.
     */
    @Override
    public void addEvent(Event event) {
        String query = "INSERT INTO public.event (event_name, type, description, date, time, creator, latitude, longitude) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        database.executeQuery(query, true, event.getEventName(), event.getType(), event.getDescription(), event.getEventDate(), event.getEventTime(), event.getCreator().getName(), event.getLatitude(), event.getLongitude());
    }

    /**
     * Retrieves an event based on the geographical position.
     *
     * @param latitude    The latitude of the position.
     * @param longitude   The longitude of the position.
     * @param mapViewer   The JXMapViewer instance.
     * @return The event found near the specified position, or null if none is found.
     */
    @Override
    public Event getEventByPosition(double latitude, double longitude, JXMapViewer mapViewer) {

        Set<Event> events = LoadEventsDAO_OutputData.getEvents();
        for (Event event : events) {
            GeoPosition eventPosition = event.getPosition();
            GeoPosition clickedPosition = new GeoPosition(latitude, longitude);
            if (isClickNearWaypoint(clickedPosition, eventPosition, mapViewer)) {
                return event;
            }
        }

        return null;
    }


    private boolean isClickNearWaypoint(GeoPosition clickPosition, GeoPosition waypointPosition, JXMapViewer mapViewer) {
        Point2D.Double clickPoint = (Point2D.Double) mapViewer.getTileFactory().geoToPixel(clickPosition, mapViewer.getZoom());
        Point2D.Double waypointPoint = (Point2D.Double) mapViewer.getTileFactory().geoToPixel(waypointPosition, mapViewer.getZoom());

        int threshold = 23;

        return clickPoint.distance(waypointPoint) < threshold;
    }


    /**
     * Checks if a user with the given identifier (username) exists in the database.
     *
     * @param identifier The username to check for existence.
     * @return True if the user exists, false otherwise.
     * @throws SQLException If a SQL-related error occurs during the check.
     */
    @Override
    public boolean existsByName(String identifier) throws SQLException {
        String query = "SELECT * FROM public.user WHERE username=?";
        Object result = database.executeQuery(query, false, identifier);

        return result != null;
    }


    /**
     * Saves a new user to the database.
     *
     * @param user The user to be saved.
     * @return True if the user is successfully saved, false otherwise.
     */
    @Override
    public boolean save(User user) {

        String query = "INSERT INTO public.user (username, name, email, password)" +
                "VALUES (?,?,?,?)";
        database.executeQuery(query, true, user.getUsername(), user.getName(), user.getEmail(), user.getPassword());
        return true;
    }


    /**
     * Retrieves a user from the database based on the username.
     *
     * @param username The username of the user to retrieve.
     * @return The user found based on the provided username, or null if not found.
     */
    public User getUserByUsername(String username) {
        String query = "select * from public.user where username=?";
        Object result = database.executeQueryUser(query, username);

        return (User) result;

    }

    /**
     * Checks if the provided password matches the password of the user with the given username.
     *
     * @param username The username of the user for password verification.
     * @param password The password to check against the stored user password.
     * @return True if the password is correct, false otherwise.
     */
    @Override
    public boolean checkPassword(String username, String password) {
        String query = "select * from public.user where username=?";
        boolean result = database.executeQueryCheckPassword(query, username, password);

        return result;
    }


    /*public List<Event> FindUsersEventsToAttend(String username) throws SQLException {
        String query = "select event.event_name from public.event\n" +
                "   where id_event in (select event\n" +
                "                    from public.attendedEvents\n" +
                "                    where visitor = ?)";

        Object eventList = database.executeQueryEventList(query, username);

        return (List<Event>) eventList;
    }

    public List<Event> FindEventsUserHosts(String username) throws SQLException {
        String query = "select event_name from public.event\n" +
                "   where creator in (select username\n" +
                "                  from public.user\n" +
                "                  where username = ?)\n";

        Object eventList = database.executeQueryEventList(query, username);

        return (List<Event>) eventList;
    }*/

}

