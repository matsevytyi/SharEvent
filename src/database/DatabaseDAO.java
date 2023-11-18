package database;

import javafx.scene.chart.PieChart;

import entities.Event;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseDAO {
    Database database = new Database();

    public void addEvent(String event_name, String event_description, String type, String time, String date, String creator, String longitude, String latitude) {

        String query = "INSERT INTO events (event_name, event_description, type, time, date, creator, longitude, latitude) VALUES (" + event_name + ", " + event_description + ", " + type + ", " + time + ", " + date + ", " + creator + ", " + longitude + ", " + latitude + ")";

        try {
            database.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




}
