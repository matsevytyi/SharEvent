package database;

import java.sql.*;

public class Database {

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

    public ResultSet executeQuery(String input_query) throws SQLException {
        Connection connection = connect();
        if (connection != null) {
            System.out.println("Connected to the database!");

            PreparedStatement getResult = connection.prepareStatement(input_query);

            ResultSet resultSet = getResult.executeQuery();

            // TODO Consider moving the nect try_cath so that it will be executed only once, after the app is closed
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return resultSet;


        } else {
            System.out.println("Failed to connect to the database.");
        }
        return null;
    }
}
