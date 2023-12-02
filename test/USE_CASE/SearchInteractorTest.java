package USE_CASE;

import DATA_ACCESS.DatabaseDAO;
import ENTITY.Event;
import ENTITY.EventFactory;
import ENTITY.EventFactoryInterface;
import ENTITY.User;
import USE_CASE.filter.FilterInputBoundary;
import USE_CASE.filter.FilterInputData;
import USE_CASE.search.SearchInputBoundary;
import USE_CASE.search.SearchInputData;
import VIEW.FilterEventsView;
import VIEW.SearchEventsView;
import VIEW_CREATOR.FilterEventsViewFactory;
import VIEW_CREATOR.LoadMapViewModel;
import VIEW_CREATOR.SearchEventsViewFactory;
import org.jxmapviewer.JXMapKit;
import org.jxmapviewer.viewer.WaypointPainter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SearchInteractorTest {
    public Event addEvent() {
        EventFactoryInterface eventFactory = new EventFactory();
        DatabaseDAO databaseDAO = new DatabaseDAO();
        User newUser = databaseDAO.getUserByUsername("ff");
        List<User> attendees = new ArrayList<>();
        Event event = eventFactory.create("TestEvent2", "Food and Drinks", "Example Event", LocalDate.now(), LocalTime.now(), newUser, attendees, 43.645306, -79.380247);
        Event event2 = eventFactory.create("TestEvent1", "Food and Drinks", "Example Event", LocalDate.now(), LocalTime.now(), newUser, attendees, 43.645306, -79.380247);

        databaseDAO.addEvent(event);
        databaseDAO.addEvent(event2);
        return event;
    }

    public SearchEventsView MakeSearchEventsView(){
        LoadMapViewModel viewModel = new LoadMapViewModel();
        SearchEventsViewFactory searchEventsViewFactory = new SearchEventsViewFactory();
        SearchEventsView searchEventsView = searchEventsViewFactory.create(viewModel);
        searchEventsView.setVisible(false);
        return searchEventsView;
    }

    @org.junit.Test
    public void testEventShownAfterSearch() {
        Event event = addEvent();

        DatabaseDAO databaseDAO = new DatabaseDAO();
        Set<Event> allEvents = databaseDAO.FilterEvents("");
        int event_id = -1;
        for(Event event2 : allEvents) {
            if (event2.getEventName().equals(event.getEventName())){
                event_id = event2.getEventId();
            }
        }

        SearchInputData searchInputData = new SearchInputData("TestEvent", allEvents);

        SearchEventsView searchEventsView = MakeSearchEventsView();
        SearchInputBoundary searchUseCaseInteractor = searchEventsView.getController().getSearchUseCaseInteractor();
        searchUseCaseInteractor.execute(searchInputData);

        JXMapKit mapKit = searchEventsView.getViewModel().getMapKit();

        Set<Event> eventsShown = ((WaypointPainter<Event>)mapKit.getMainMap().getOverlayPainter()).getWaypoints();

        Set<Integer> eventsShownID = new HashSet<>();
        for(Event event2 : eventsShown) {
            eventsShownID.add(event2.getEventId());
        }

        assert (eventsShownID.contains(event_id));
        databaseDAO.deleteEvent(event_id);

    }

    @org.junit.Test
    public void testEventNotShownAfterSearch() {
        Event event = addEvent();

        DatabaseDAO databaseDAO = new DatabaseDAO();
        Set<Event> allEvents = databaseDAO.FilterEvents("");
        int event_id = -1;
        for(Event event2 : allEvents) {
            if (event2.getEventName().equals(event.getEventName())){
                event_id = event2.getEventId();
            }
        }

        SearchInputData searchInputData = new SearchInputData("TestEvent1", allEvents);

        SearchEventsView searchEventsView = MakeSearchEventsView();
        SearchInputBoundary searchUseCaseInteractor = searchEventsView.getController().getSearchUseCaseInteractor();
        searchUseCaseInteractor.execute(searchInputData);

        JXMapKit mapKit = searchEventsView.getViewModel().getMapKit();

        Set<Event> eventsShown = ((WaypointPainter<Event>)mapKit.getMainMap().getOverlayPainter()).getWaypoints();

        Set<Integer> eventsShownID = new HashSet<>();
        for(Event event2 : eventsShown) {
            eventsShownID.add(event2.getEventId());
        }

        assert (!eventsShownID.contains(event_id));
        databaseDAO.deleteEvent(event_id);

    }
}
