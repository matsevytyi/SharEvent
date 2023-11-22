package interface_adapter.view_event;

import entity.Event;

import java.time.LocalDate;
import java.time.LocalTime;

public class ViewEventState {


    public void setDetails(String eventName, String description,LocalDate date, LocalTime time, String createdBy, String registeredUsers ){
        setDate(date);
        setTime(time);
        setDescription(description);
        setCreatedBy(createdBy);
        setEventName(eventName);
        setRegisteredUsers(registeredUsers);
    }

    public ViewEventState() {
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getRegisteredUsers() {
        return registeredUsers;
    }

    public void setRegisteredUsers(String registeredUsers) {
        this.registeredUsers = registeredUsers;
    }

    private  String eventName ;
        private String description ;

    private  LocalDate date ;

        private  LocalTime time ;
        private  String createdBy;
        private  String registeredUsers ;

    }

