package interface_adapter.load_events;

import entity.Event;
import lombok.Getter;


import java.util.Set;

public class LoadEventsInputData {

    @Getter
    private Set<Event> events;

    public LoadEventsInputData(Set<Event> events) {
        this.events = events;
    }

}
