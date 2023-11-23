package DATA_ACCESS;

import java.sql.*;

public class Database {

    Connection connection;

    public Database() {
        connection = connect();
    }

    //TODO: close should be executed when the application is closed
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
                System.out.println("Query executed");
                return resultSet;
            }
        } catch (SQLException e) {
            System.out.println("Error executing SQL query");
            e.printStackTrace();
            return null;
        }
    }




}