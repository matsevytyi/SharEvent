package DATA_ACCESS.loadevents_dataaccess;

import java.util.Set;

import ENTITY.Event;
import lombok.Getter;

/**
 * Class, used for passing data from Database to LoadEventsInteractor
 * */
public class LoadEventsDAO_OutputData {


    @Getter
   static Set<Event> events;

    public LoadEventsDAO_OutputData(Set<Event> events) {
        this.events = events;
    }
}
