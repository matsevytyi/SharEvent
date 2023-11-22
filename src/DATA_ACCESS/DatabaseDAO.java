package DATA_ACCESS;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.LinkedList;

import lombok.SneakyThrows;
import java.util.List;
import java.util.Set;
import ENTITY.Temporary_entites.*;



public class DatabaseDAO implements LoadEventsDataAccessInterface {
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

    public void registerUserForEvent(String username, String event_id) {


        //TODO: implement additional logic if needed (for holding unappropriate cases)

        String query = "INSERT INTO public.attendedEvents (visitor, event) VALUES (" + username + ", " + event_id + ")";

        database.executeQuery(query, true);

    }

    public void unregisterUserFromEvent(String username, String event_id) {

        //TODO: implement additional logic if needed (for holding unappropriate cases) --
        // + only particular visitor or event creator can perform such operation for particular event

        String query = "DELETE FROM public.attendedEvents WHERE visitor = " + username + " AND event = " + event_id;

        database.executeQuery(query, true);

    }

    public void getUsersRegisteredForEvent(String event_id) {

        //TODO: implement additional logic if needed (for holding unappropriate cases) --
        // + write query to get event_id or add event_id to the event class

        String query = "SELECT * " +
                " FROM public.user JOIN public.attendedEvents " +
                " WHERE public.attendedEvents.event = " + event_id +
                ";";


        database.executeQuery(query, false);

        //TODO return List<User>
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

    public Set<Event> getEventsInRange(String latitude1, String latitude2, String longitude1, String longitude2)  {
        String query = "SELECT * FROM public.event " +
                "WHERE latitude > " + latitude1 + " AND latitude < " + latitude2 +
                " AND longitude > " + longitude1 + " AND longitude < " + longitude2 +
                ";";

        Set<Event> events = new HashSet<>();

        ResultSet resultSet = (ResultSet) database.executeQuery(query, false);

        try {
            while (resultSet.next()) {
                events.add(extractEvent(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error processing the ResultSet");
            // TODO: Handle the exception or log it as needed
        } finally {
            // Close the ResultSet and connection in the finally block
            database.closeConnection();
        }

        return events;
    }

    private void closeResultSet(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error closing the ResultSet");
            // TODO: Handle the exception or log it as needed
        }
    }


    //TODO: implement GetEventFunction for VIEW_EVENT use case

    public boolean existsByName(String identifier) throws SQLException {
        String query = "SELECT * FROM public.user WHERE username=?";
        Object result = database.executeQuery(query, false, identifier);

        return result != null;
    }

    public boolean save(User user) {

        String query = "INSERT INTO public.user (username, name, email, password)" +
                "VALUES (?,?,?,?)";
        database.executeQuery(query, true, user.getUsername(), user.getName(), user.getEmail(), user.getPassword());
        return true;
    }

    public User getUserByUsername(String username) {
        String query = "select * from user where username=?";
        Object result = database.executeQuery(query, false, username);

        return extractUser((ResultSet) result);
    }

    public List<User> FindFollowersOfUser(String username) throws SQLException {
        String query = "select * from public.user \n" +
                "\t where username in (select follower\n" +
                "                  from public.following\n" +
                "                  where target_user = ?)";

        ResultSet resultSet = (ResultSet) database.executeQuery(query, false, username);

        List<User> userList = new LinkedList<>();

        while (resultSet.next()) {
            userList.add(extractUser(resultSet));
        }

        return userList;
    }

    public List<Event> FindUsersEventsToAttend(String username) throws SQLException {
        String query = "select event.event_name from public.event\n" +
                "   where id_event in (select event\n" +
                "                    from public.attendedEvents\n" +
                "                    where visitor = ?)";

        ResultSet resultSet = (ResultSet) database.executeQuery(query, false, username);

        List<Event> eventList = new LinkedList<>();

        while (resultSet.next()) {
            eventList.add(extractEvent(resultSet));
        }

        return eventList;
    }

    public List<Event> FindEventsUserHosts(String username) throws SQLException {
        String query = "select event_name from public.event\n" +
                "   where creator in (select username\n" +
                "                  from public.user\n" +
                "                  where username = ?)\n";

        ResultSet resultSet = (ResultSet) database.executeQuery(query, false, username);

        List<Event> eventList = new LinkedList<>();

        while (resultSet.next()) {
            eventList.add(extractEvent(resultSet));
        }

        return eventList;
    }

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

    @SneakyThrows
    private User extractUser(ResultSet resultSet) {
        String username = resultSet.getString("username");
        String name = resultSet.getString("name");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");

        // need to somehow deal with nulls
        return new User(username, name, email, password, null, null);
    }

    @SneakyThrows
    private Event extractEvent(ResultSet resultSet) {
        int id_event = resultSet.getInt("id_event ");
        String event_name = resultSet.getString("event_name");
        String description = resultSet.getString("description");
        String type = resultSet.getString("type");
        String time = resultSet.getString("time");
        String date = resultSet.getString("date");
        float longitude = resultSet.getFloat("longitude");
        float latitude = resultSet.getFloat("latitude");
        String creator = resultSet.getString("creator");

        // треба якось переробити з цими налами
        return new Event(id_event, event_name, description, type, time, date, creator, longitude, latitude);
    }










}
