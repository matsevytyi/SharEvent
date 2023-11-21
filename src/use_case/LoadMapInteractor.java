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
        events = loadMapDataAccessInterface.getEventsInRange(String.valueOf(initialPoint.getLatitude() - 5./111), String.valueOf(initialPoint.getLatitude() + 5./111), String.valueOf(initialPoint.getLongitude() - 5./111), String.valueOf(initialPoint.getLongitude() + 5./111));
    }

    public Set<Event> updateEvents(GeoPosition newStartPoint) {
        events = loadMapDataAccessInterface.getEventsInRange(String.valueOf(newStartPoint.getLatitude() - 5./111), String.valueOf(newStartPoint.getLatitude() + 5./111), String.valueOf(newStartPoint.getLongitude() - 5./111), String.valueOf(newStartPoint.getLongitude() + 5./111));
        return events;
    }
}
