package ENTITY;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jxmapviewer.viewer.DefaultWaypoint;

@Getter
@Setter
@AllArgsConstructor
public class Event extends DefaultWaypoint {

    int idEvent;
    String eventName;
    String description;
    String type;
    String time;
    String date;
    double longitude;
    double latitude;
    String creator;


}
