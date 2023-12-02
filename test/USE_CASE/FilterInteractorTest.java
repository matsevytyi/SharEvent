package USE_CASE;

import DATA_ACCESS.DatabaseDAO;
import ENTITY.*;
import USE_CASE.filter.FilterInputBoundary;
import USE_CASE.filter.FilterInputData;
import VIEW.FilterEventsView;
import VIEW_CREATOR.FilterEventsViewFactory;
import VIEW_CREATOR.LoadMapViewModel;
import org.jxmapviewer.JXMapKit;
import org.jxmapviewer.viewer.WaypointPainter;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.time.LocalDate;
import java.util.Set;

public class FilterInteractorTest {
    public Event addEvent() {
        EventFactoryInterface eventFactory = new EventFactory();
        //      UserFactory userFactory =  new UserFactoryImplementation();
//        User newUser = userFactory.create("username1", "name1", "email1", "password1");
        DatabaseDAO databaseDAO = new DatabaseDAO();
//        databaseDAO.save(newUser);
        User newUser = databaseDAO.getUserByUsername("ff");
        List<User> attendees = new ArrayList<>();
        Event event = eventFactory.create("TestEvent", "Food and Drinks", "Example Event", LocalDate.now(), LocalTime.now(), newUser, attendees, 43.645306, -79.380247);

        databaseDAO.addEvent(event);
        return event;
    }

    public void deleteEvent(Event event){
        DatabaseDAO databaseDAO = new DatabaseDAO();
        databaseDAO.deleteEvent(event.getEventId());
    }

    public FilterEventsView MakeFilterEventsView(){
        LoadMapViewModel viewModel = new LoadMapViewModel();
        FilterEventsViewFactory filterEventsViewFactory = new FilterEventsViewFactory();
        FilterEventsView filterEventsView = filterEventsViewFactory.create(viewModel);
        return filterEventsView;
    }

    @org.junit.Test
    public void testEventShownAfterFilter() {
        Event event = addEvent();

        DatabaseDAO databaseDAO = new DatabaseDAO();
        Set<Event> allEvents = databaseDAO.FilterEvents("");
        int event_id = -1;
        for(Event event2 : allEvents) {
            if (event2.getEventName().equals(event.getEventName())){
                event_id = event2.getEventId();
            }
        }

        FilterInputData filterInputData = new FilterInputData("Food and Drinks", allEvents);

        FilterEventsView filterEventsView = MakeFilterEventsView();
        FilterInputBoundary filterUseCaseInteractor = filterEventsView.getController().getFilterUseCaseInteractor();
        filterUseCaseInteractor.execute(filterInputData);

        JXMapKit mapKit = filterEventsView.getViewModel().getMapKit();

        Set<Event> eventsShown = ((WaypointPainter<Event>)mapKit.getMainMap().getOverlayPainter()).getWaypoints();

        Set<Integer> eventsShownID = new HashSet<>();
        for(Event event2 : eventsShown) {
            eventsShownID.add(event2.getEventId());
        }

        assert (eventsShownID.contains(event_id));
        databaseDAO.deleteEvent(event_id);

    }

    @org.junit.Test
    public void testEventNotShownAfterFilter() {
        Event event = addEvent();

        DatabaseDAO databaseDAO = new DatabaseDAO();
        Set<Event> allEvents = databaseDAO.FilterEvents("");
        int event_id = -1;
        for(Event event2 : allEvents) {
            if (event2.getEventName().equals(event.getEventName())){
                event_id = event2.getEventId();
            }
        }

        FilterInputData filterInputData = new FilterInputData("Music", allEvents);

        FilterEventsView filterEventsView = MakeFilterEventsView();
        FilterInputBoundary filterUseCaseInteractor = filterEventsView.getController().getFilterUseCaseInteractor();
        filterUseCaseInteractor.execute(filterInputData);

        JXMapKit mapKit = filterEventsView.getViewModel().getMapKit();

        Set<Event> eventsShown = ((WaypointPainter<Event>)mapKit.getMainMap().getOverlayPainter()).getWaypoints();

        Set<Integer> eventsShownID = new HashSet<>();
        for(Event event2 : eventsShown) {
            eventsShownID.add(event2.getEventId());
        }

        assert (!eventsShownID.contains(event_id));
        databaseDAO.deleteEvent(event_id);

    }

}
