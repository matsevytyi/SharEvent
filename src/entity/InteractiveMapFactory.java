package entity;

import java.util.List;

public class InteractiveMapFactory implements InteractiveMapFactoryInterface{

    @Override
    public InteractiveMap put(List<Event> events) {
        return  new InteractiveMap(events);
    }
}
