package INTERFACE_ADAPTER;

import lombok.Getter;
import ENTITY.Temporary_entites.Event;

import java.util.Set;

public class LoadEventsInputData {

    @Getter
    private Set<Event> events;

    public LoadEventsInputData(Set<Event> events) {
        this.events = events;
    }

}
