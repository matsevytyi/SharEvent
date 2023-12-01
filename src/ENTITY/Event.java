package ENTITY;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;

@Getter
@Setter
@AllArgsConstructor
public class Event extends DefaultWaypoint {

    public Event(int idEvent, String eventName, String description, String type, String time, String date, double longitude, double latitude, String creator) {
        super(latitude, longitude);
        this.idEvent = idEvent;
        this.eventName = eventName;
        this.description = description;
        this.type = type;
        this.time = time;
        this.date = date;
        this.creator = creator;
        this.longitude = longitude;
        this.latitude = latitude;
        this.geoPosition = new GeoPosition(latitude, longitude);

    }

    int idEvent;
    String eventName;
    String description;
    String type;
    String time;
    String date;
    double longitude;
    double latitude;
    String creator;
    GeoPosition geoPosition;


}
