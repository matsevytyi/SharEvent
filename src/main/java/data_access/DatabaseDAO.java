package data_access;
import entity.Event;
import entity.User;
import lombok.SneakyThrows;

import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class DatabaseDAO implements UserSignUpDataAccessInterface, UserLoginDataAccessInterface {


    private final Map<String, User> accounts = new HashMap<>();
    public static Database database = new Database();

    @Override
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
