package DATA_ACCESS;

import java.sql.SQLException;
import java.util.HashSet;

import DATA_ACCESS.loadevents_dataaccess.LoadEventsDAO_InputData;
import DATA_ACCESS.loadevents_dataaccess.LoadEventsDAO_OutputData;
import DATA_ACCESS.loadevents_dataaccess.LoadEventsDataAccessInterface;
import ENTITY.User;
import ENTITY.Event;
import java.util.List;
import java.util.Set;


public class DatabaseDAO implements LoadEventsDataAccessInterface, UserLoginDataAccessInterface, UserSignUpDataAccessInterface {
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

    public LoadEventsDAO_OutputData getEventsInRange(LoadEventsDAO_InputData inputData) throws SQLException {
        String query = "SELECT * FROM public.event " +
                "WHERE latitude > " + inputData.getLatitude1() + " AND latitude < " + inputData.getLatitude2() +
                " AND longitude > " + inputData.getLongitude1() + " AND longitude < " + inputData.getLongitude2() +
                ";";

        Set<Event> events = new HashSet<>();

        events.addAll(database.executeQueryEventList(query, "TODO"));

        return new LoadEventsDAO_OutputData(events);
    }


    //TODO: implement GetEventFunction for VIEW_EVENT use case

    // User

    @Override
    public boolean existsByName(String identifier) throws SQLException {
        String query = "SELECT * FROM public.user WHERE username=?";
        Object result = database.executeQuery(query, false, identifier);


        return result != null;
    }


    @Override
    public boolean save(User user) {

        String query = "INSERT INTO public.user (username, name, email, password)" +
                "VALUES (?,?,?,?)";
        database.executeQuery(query, true, user.getUsername(), user.getName(), user.getEmail(), user.getPassword());
        return true;
    }

    public User getUserByUsername(String username) {
        String query = "select * from public.user where username=?";
        Object result = database.executeQueryUser(query, username);

        return (User) result;
    }

    @Override
    public boolean checkPassword(String password) {
        return false;
    }

    public List<User> FindFollowersOfUser(String username) throws SQLException {
        String query = "select * from public.user \n" +
                "\t where username in (select follower\n" +
                "                  from public.following\n" +
                "                  where target_user = ?)";

        Object userList = database.executeQueryUserList(query, username);

        return (List<User>) userList;
    }

    public List<Event> FindUsersEventsToAttend(String username) throws SQLException {
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



    /*static Connection connection;
    public static UserDataAccessObject dt = new UserDataAccessObject();
    private final Map<String, User> accounts = new HashMap<>();

    public static void connect() {

        String url = "jdbc:mysql://localhost:3306/sharEvent";
        String user = "root";
        String password = "loppp888";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection is Successful to the database" + url);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean existsByName(String identifier) {
        String query = "SELECT * FROM User WHERE username=?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, identifier);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            System.out.println(resultSet.getString(1));
            return true;
        } catch (SQLException e) {
            System.out.println("Не вірний SQL запит existsByName");
            return false;
        }

    }

    public boolean save(User user) {
        String query = "INSERT INTO User (username, name, email, password)" +
                "VALUES (?,?,?,?)";
        try(PreparedStatement statement = connection.prepareStatement(query)){

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getName());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getEmail());

            int rows = statement.executeUpdate();

            if (rows == 0) {
                System.out.println("Failed to save");
            }

        }catch(SQLException e){
            System.out.println("Не вірний SQL запит на вибірку даних");
            e.printStackTrace();
        }
        return true;

    }*/












}
