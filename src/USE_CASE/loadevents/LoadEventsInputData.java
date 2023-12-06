package USE_CASE.loadevents;

import ENTITY.Event;
import lombok.Getter;

import java.util.Set;

import ENTITY.Event;

/**
 * Is used to pass data from LoadEventsInteractor to LoadEventsPresenter
 * */
public class LoadEventsInputData {

    @Getter
    private Set<Event> events;

    public LoadEventsInputData(Set<Event> events) {
        this.events = events;
    }

}
