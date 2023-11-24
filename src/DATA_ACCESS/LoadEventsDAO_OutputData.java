package DATA_ACCESS;

import java.util.Set;
import entity2.Temporary_entites.Event;
import lombok.Getter;

public class LoadEventsDAO_OutputData {

    //TODO: Fill this class for other queries data as well

    @Getter
    Set<Event> events;

    public LoadEventsDAO_OutputData(Set<Event> events) {
        this.events = events;
    }
}
