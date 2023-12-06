package USE_CASE.loadevents;


import lombok.Getter;
import org.jxmapviewer.viewer.GeoPosition;

/**
 * Is used to pass data from LoadEventsController to LoadEventsInteractor
 * */
public class LoadEventsOuputData {

    @Getter
    private GeoPosition newLocationPoint;

    public LoadEventsOuputData(GeoPosition newLocationPoint) {
        this.newLocationPoint = newLocationPoint;
    }
}
