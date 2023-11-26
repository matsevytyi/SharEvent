package interface_adapter.add_event;

import entity.User;
import lombok.Getter;
import lombok.Setter;
import org.jxmapviewer.viewer.GeoPosition;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
@Getter @Setter
public class AddEventState {


    private String eventName = "";
    private String eventNameError = null;
    private String eventType = "";
    private String eventTypeError = null;
    private String eventDescription = "";
    private String eventDescriptionError = null;
    private LocalDate eventDate = null;
    private String eventDateError = null;
    private LocalTime eventTime = null;
    private String eventTimeError = null;

    private User creator = null;
    private List<User> attendant = null;
    private double eventLongitude = 0;

    private String eventLongitudeError = null;

    private double eventLatitude = 0;
    private String eventLatitudeError = null;
//    private GeoPosition clickedPosition = null;

    public AddEventState(){}





}
