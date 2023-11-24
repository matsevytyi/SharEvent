package interface_adapter2;

import lombok.Getter;
import entity2.Temporary_entites.Event;

import java.util.Set;

public class LoadEventsInputData {

    @Getter
    private Set<Event> events;

    public LoadEventsInputData(Set<Event> events) {
        this.events = events;
    }

}
