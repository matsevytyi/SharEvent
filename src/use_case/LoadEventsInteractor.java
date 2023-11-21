package use_case;

import database.LoadEventsDataAccessInterface;
import database.DatabaseDAO;
import lombok.Getter;
import org.jxmapviewer.viewer.GeoPosition;
import java.util.Set;

import Entities.Temporary_entites.Event;



public class LoadEventsInteractor {
    @Getter
    Set<Event> events;
    LoadEventsDataAccessInterface loadEventsDataAccessInterface;

    public LoadEventsInteractor(GeoPosition initialPoint) {
        loadEventsDataAccessInterface = new DatabaseDAO();
        events = loadEventsDataAccessInterface.getEventsInRange(String.valueOf(initialPoint.getLatitude() - 5./111), String.valueOf(initialPoint.getLatitude() + 5./111), String.valueOf(initialPoint.getLongitude() - 5./111), String.valueOf(initialPoint.getLongitude() + 5./111));
    }

    public Set<Event> updateEvents(GeoPosition newStartPoint) {
        events = loadEventsDataAccessInterface.getEventsInRange(String.valueOf(newStartPoint.getLatitude() - 5./111), String.valueOf(newStartPoint.getLatitude() + 5./111), String.valueOf(newStartPoint.getLongitude() - 5./111), String.valueOf(newStartPoint.getLongitude() + 5./111));
        return events;
    }
}
