package DATA_ACCESS;

import ENTITY.Event;
import ENTITY.User;
import lombok.SneakyThrows;

import java.sql.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Database {
    Connection connection;

    public Database() {
        connection = connect();
    }
    public Connection connect() {
        String url = "jdbc:postgresql://db.bqeyxdersfsiysrpyzqb.supabase.co:5432/postgres";
        String user = "basic_user";
        String password = "pass1111";


        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean closeConnection() {
        try {
            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public Object executeQuery(String query, boolean isUpdate, Object... parameters) {
        connection = connect();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            // Set parameters if any
            for (int i = 0; i < parameters.length; i++) {
                statement.setObject(i + 1, parameters[i]);
            }

            ResultSet resultSet;
            // Execute the query
            if (isUpdate) {
                // For INSERT, UPDATE, DELETE
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected == 0) {
                    System.out.println("Failed to save");
                    return false;
                } else {
                    return true;
                }
            } else {
                // For SELECT
                resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    //TODO What are we supposed to do gere instead of printing resultSet?
                    System.out.println(resultSet.getString(1));
                    return resultSet;
                } else {
                    System.out.println("No results found");
                    return null;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error executing SQL query");
            e.printStackTrace();
            return null;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // USER
    public Object executeQueryUserList(String query, String username) {
        connection = connect();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            List<User> userList = new LinkedList<>();

            while (resultSet.next()) {
                userList.add(extractUser(resultSet));
            }

            return extractUser(resultSet);

        }  catch (SQLException e) {
            System.out.println("Error executing SQL query");
            e.printStackTrace();
            return null;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public Object executeQueryUser(String query, String username) {
        connection = connect();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                return extractUser(resultSet);
            }

            return null;

        }  catch (SQLException e) {
            System.out.println("Error executing SQL query");
            e.printStackTrace();
            return null;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public boolean executeQueryCheckPassword(String query, String username, String password) {
        connection = connect();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            User user = extractUser(resultSet);
            if (password.equals(user.getPassword())) {
                return true;
            }

        }  catch (SQLException e) {
            System.out.println("Error executing SQL query");
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @SneakyThrows
    private User extractUser(ResultSet resultSet) {
        String username = resultSet.getString("username");
        String name = resultSet.getString("name");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");

        // треба якось переробити з цими налами
        return new User(username, name, email, password, null, null);
    }


    public Object executeQueryEventList(String query, String username) {

        Connection connection = connect();
        try (PreparedStatement statement = connection.prepareStatement(query)) {

            ResultSet resultSet = statement.executeQuery();
            List<Event> eventList = new LinkedList<>();

            while (resultSet.next()) {
                eventList.add(extractEvent(resultSet));
            }

            return eventList;

        }  catch (SQLException e) {
            System.out.println("Error executing SQL query");
            e.printStackTrace();
            return null;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @SneakyThrows
    private Event extractEvent(ResultSet resultSet) {
        int id_event = resultSet.getInt("id_event");
        String event_name = resultSet.getString("event_name");
        String description = resultSet.getString("description");
        String type = resultSet.getString("type");
        String time = resultSet.getString("time");
        String date = resultSet.getString("date");
        double longitude = resultSet.getFloat("longitude");
        double latitude = resultSet.getFloat("latitude");
        String creator = resultSet.getString("creator");

        // треба якось переробити з цими налами
        return new Event(id_event, event_name, description, type, time, date, longitude, latitude, creator);
    }

    public Object executeQueryEvent(String query) {

        Set<Event> events = new HashSet<>();

        connection = connect();
        try (PreparedStatement statement = connection.prepareStatement(query)) {


            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                events.add(extractEvent(resultSet));

            } else {
                System.out.println("No results found");
                return null;
            }

        } catch (SQLException e) {
            System.out.println("Error executing SQL query");
            e.printStackTrace();
            return null;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        System.out.println("our event set: " + events.isEmpty());

        return events;
    }




}




