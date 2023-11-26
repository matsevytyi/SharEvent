package data_access;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.LinkedList;

import entity.User;
import lombok.SneakyThrows;
import java.util.List;
import java.util.Set;
import entity.Event;



public class DatabaseDAO implements LoadEventsDataAccessInterface {
    Database database = new Database();

    @Override
    public Event getEventById(int id) {

        String query = "select * from public.event where id_event=?";

        Event event = (Event) database.executeQueryEvent(query, id);
        return event;
    }

    public void deleteEvent(String event_id) {

        String query = "DELETE FROM events WHERE event_id = " + event_id;

        database.executeQuery(query, true);
    }

    public void updateEvent(String old_event_name, String old_event_time, String old_event_date, String event_name, String event_description, String type, String time, String date, String creator, String longitude, String latitude) {

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

    public void registerUserForEvent(String username, String event_id) {

        String query = "INSERT INTO public.attendedEvents (visitor, event) VALUES (" + username + ", " + event_id + ")";

        database.executeQuery(query, true);

    }

    public void unregisterUserFromEvent(String username, String event_id) {

        String query = "DELETE FROM public.attendedEvents WHERE visitor = " + username + " AND event = " + event_id;

        database.executeQuery(query, true);

    }

    public List<User> getUsersRegisteredForEvent(int event_id) {

        String query = "SELECT * " +
                " FROM public.user JOIN public.attendedEvents " +
                " WHERE public.attendedEvents.event = " + event_id +
                ";";


        Object userList = database.executeQueryUserListForEvent(query, event_id);

        return (List<User>) userList;
    }

    public void FilterEvents(String type, String latitude1, String latitude2, String longitude1, String longitude2) {

        String query = "SELECT * " +
                "FROM public.events " +
                "WHERE type = " + type +
                " AND " +
                "EVENTLAT > " + latitude1 +" AND EVENTLAT < " + latitude2 +
                " AND " +
                "EVENTLONG > " + longitude1 +" AND EVENTLONG < " + longitude2 +
                ";";

        database.executeQuery(query, false);

        //TODO return List<Event>

    }

    public void SearchEvent(String event_name) {

        String query = "SELECT * " +
                "FROM public.events " +
                "WHERE event_name = " + event_name +
                ";";


        database.executeQuery(query, false);

        //TODO return Event
    }

    public LoadEventsDAO_OutputData getEventsInRange(LoadEventsDAO_InputData inputData) throws SQLException {
        String query = "SELECT * FROM public.event ";

        //String q = "SELECT * FROM public.user ";
        //database.executeQueryEventList(q);

        database.executeQueryEventList(query);

        System.out.println(inputData.getLatitude1());
        System.out.println(inputData.getLongitude1());
        System.out.println(inputData.getLatitude2());
        System.out.println(inputData.getLongitude2());

        Set<Event> events = null;
        return new LoadEventsDAO_OutputData(events);
    }

    /*public LoadEventsDAO_OutputData getEventsInRange(LoadEventsDAO_InputData inputData) throws SQLException {
        String query = "SELECT * FROM public.event " +
                "WHERE latitude > " + inputData.getLatitude1() + " AND latitude < " + inputData.getLatitude2() +
                " AND longitude > " + inputData.getLongitude1() + " AND longitude < " + inputData.getLongitude2() +
                ";";

        Set<Event> events = database.executeQueryEventList(query);

        System.out.println(inputData.getLatitude1());
        System.out.println(inputData.getLongitude1());
        System.out.println(inputData.getLatitude2());
        System.out.println(inputData.getLongitude2());


        return new LoadEventsDAO_OutputData(events);
    }*/

    @Override
    public void addEvent(Event event) {
        String query = "INSERT INTO public.event (event_name, type, description, date, time, creator, latitude, longitude) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        int generatedId = database.executeInsertAndGetGeneratedId(query,
                event.getEventName(), event.getType(), event.getDescription(),
                event.getEventDate(), event.getEventTime(), event.getCreator().getUsername(),
                event.getLatitude(), event.getLongitude());

        if (generatedId != -1) {
            System.out.println("Event added with ID: " + generatedId);
            event.setEventId(generatedId);
        } else {
            System.out.println("Failed to add event");
            // Handle failure (throw an exception or take appropriate action)
        }
    }

    public boolean existsByName(String identifier) throws SQLException {
        String query = "SELECT * FROM public.user WHERE username=?";
        Object result = database.executeQuery(query, false, identifier);

        return result != null;
    }

//    public boolean save(User user) {
//
//        String query = "INSERT INTO public.user (username, name, email, password)" +
//                "VALUES (?,?,?,?)";
//        database.executeQuery(query, true, user.getUsername(), user.getName(), user.getEmail(), user.getPassword());
//        return true;
//    }

    public User getUserByUsername(String username) {
        String query = "select * from public.user where username=?";
        Object result = database.executeQueryUser(query, username);

        return (User) result;
    }



//    public List<User> FindFollowersOfUser(String username) throws SQLException {
//        String query = "select * from public.user \n" +
//                "\t where username in (select follower\n" +
//                "                  from public.following\n" +
//                "                  where target_user = ?)";
//
//        ResultSet resultSet = (ResultSet) database.executeQuery(query, false, username);
//
//        List<User> userList = new LinkedList<>();
//
//        while (resultSet.next()) {
//            userList.add(extractUser(resultSet));
//        }
//
//        return userList;
//    }

//    public List<Event> FindUsersEventsToAttend(String username) throws SQLException {
//        String query = "select event.event_name from public.event\n" +
//                "   where id_event in (select event\n" +
//                "                    from public.attendedEvents\n" +
//                "                    where visitor = ?)";
//
//        ResultSet resultSet = (ResultSet) database.executeQuery(query, false, username);
//
//        List<Event> eventList = new LinkedList<>();
//
//        while (resultSet.next()) {
//            eventList.add(extractEvent(resultSet));
//        }
//
//        return eventList;
//    }

//    public List<Event> FindEventsUserHosts(String username) throws SQLException {
//        String query = "select event_name from public.event\n" +
//                "   where creator in (select username\n" +
//                "                  from public.user\n" +
//                "                  where username = ?)\n";
//
//        ResultSet resultSet = (ResultSet) database.executeQuery(query, false, username);
//
//        List<Event> eventList = new LinkedList<>();
//
//        while (resultSet.next()) {
//            eventList.add(extractEvent(resultSet));
//        }
//
//        return eventList;
//    }

    public boolean FollowUser(String username) {
        String query = "insert into public.following (id_relation, target_user, follower)\n" +
                "\t values (?, ?, ?)\n";

        Object result = database.executeQuery(query, true, username);

        return (boolean) result;

    }

    public boolean UnFollowUser(String username) {
        String query = "delete from public.following where follower = ?";

        Object result = database.executeQuery(query, true, username);

        return (boolean) result;

    }
















}
