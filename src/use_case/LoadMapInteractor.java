package use_case;

import database.LoadMapDataAccessInterface;
import database.DatabaseDAO;
import lombok.Getter;
import org.jxmapviewer.viewer.GeoPosition;
import java.util.Set;

import Entities.Temporary_entites.Event;



public class LoadMapInteractor {
    @Getter
    Set<Event> events;
    LoadMapDataAccessInterface loadMapDataAccessInterface;

    public LoadMapInteractor(GeoPosition initialPoint) {
        loadMapDataAccessInterface = new DatabaseDAO();
        events = loadMapDataAccessInterface.getEventsInRange(String.valueOf(initialPoint.getLatitude()), String.valueOf(initialPoint.getLongitude()), String.valueOf(initialPoint.getLatitude()), String.valueOf(initialPoint.getLongitude()));
    }

    public Set<Event> updateEvents(GeoPosition newStartPoint) {
        events = loadMapDataAccessInterface.getEventsInRange(String.valueOf(newStartPoint.getLatitude()), String.valueOf(newStartPoint.getLongitude()), String.valueOf(newStartPoint.getLatitude()), String.valueOf(newStartPoint.getLongitude()));
        return events;
    }
}
