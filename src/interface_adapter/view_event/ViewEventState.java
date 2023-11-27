package interface_adapter.view_event;

import lombok.Getter;
import lombok.Setter;
import org.jxmapviewer.JXMapViewer;

import java.time.LocalDate;
import java.time.LocalTime;
@Getter @Setter
public class ViewEventState {


    public void setDetails(String eventName, String type, String description,LocalDate date, LocalTime time, String createdBy, String registeredUsers ){
        setDate(date);
        setTime(time);
        setDescription(description);
        setCreatedBy(createdBy);
        setEventName(eventName);
        setRegisteredUsers(registeredUsers);
        setType(type);
    }



    private  String eventName ;
    private String description ;
    private String type;
    private  LocalDate date ;
        private  LocalTime time ;
        private  String createdBy;
        private  String registeredUsers ;
    private double latitude;
    private double longitude;
    private JXMapViewer mapViewer;

    }

