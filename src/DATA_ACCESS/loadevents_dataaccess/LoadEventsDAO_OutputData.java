package DATA_ACCESS.loadevents_dataaccess;

import java.util.Set;

import ENTITY.Event;
import lombok.Getter;

public class LoadEventsDAO_OutputData {

    //TODO: Fill this class for other queries data as well

    @Getter
   static Set<Event> events; //мені потрібно шоб воно було статік для вю

    public LoadEventsDAO_OutputData(Set<Event> events) {
        this.events = events;
    }
}