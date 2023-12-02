package USE_CASE;
import DATA_ACCESS.DatabaseDAO;
import ENTITY.*;
import ENTITY.Event;
import USE_CASE.FindEventsStrategy.FindByFilter;
import USE_CASE.FindEventsStrategy.FindBySearch;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertNotNull;
public class FindEventsStrategyTest {
    List<Event> CreateEvents(){
        EventFactoryInterface eventFactory = new EventFactory();
        UserFactory userFactory =  new UserFactoryImplementation();
        User newUser = userFactory.create("username1", "name1", "email1", "password1");
        List<User> attendees = new ArrayList<>();
        Event event1 = eventFactory.create("Event1", "Food and Drinks", "Example Event", LocalDate.now(), LocalTime.now(), newUser, attendees, 43.645306, -79.380247);
        Event event2 = eventFactory.create("Event2", "Sports and Fitness", "Example Event", LocalDate.now(), LocalTime.now(), newUser, attendees, 43.645306, -79.380247);
        Event event3 = eventFactory.create("Event3", "Other", "Example Event", LocalDate.now(), LocalTime.now(), newUser, attendees, 43.645306, -79.380247);
        Event event4 = eventFactory.create("Event4", "Music", "Example Event", LocalDate.now(), LocalTime.now(), newUser, attendees, 43.645306, -79.380247);
        Event event5 = eventFactory.create("Event5", "Food and Drinks", "Example Event", LocalDate.now(), LocalTime.now(), newUser, attendees, 43.645306, -79.380247);
        Event event6 = eventFactory.create("Event6", "Gaming", "Example Event", LocalDate.now(), LocalTime.now(), newUser, attendees, 43.645306, -79.380247);
        List<Event> eventList =  new ArrayList<>();
        eventList.add(event1);
        eventList.add(event2);
        eventList.add(event3);
        eventList.add(event4);
        eventList.add(event5);
        eventList.add(event6);
        return eventList;
    }

    Set<Event> GetSet(List<Event> eventList){
        Set<Event> eventSet = new HashSet<>(eventList);
        return eventSet;
    }

    @org.junit.Test
    public void testFindBySearchForOne() {
        List<Event> eventList = CreateEvents();
        Set<Event> eventSet = GetSet(eventList);
        FindBySearch findBySearch = new FindBySearch();
        Set<Event> foundEvents = findBySearch.findEvents(eventSet, "Event1");

        Set<Event> event1set = new HashSet<>();

        event1set.add(eventList.get(0));
        assert(foundEvents.equals(event1set));
    }

    @org.junit.Test
    public void testFindBySearchForMany() {
        Set<Event> eventSet = GetSet(CreateEvents());
        FindBySearch findBySearch = new FindBySearch();
        Set<Event> foundEvents = findBySearch.findEvents(eventSet, "Event");

        assert(foundEvents.equals(eventSet));
    }

    @org.junit.Test
    public void testFindByFilterForOne() {
        List<Event> eventList = CreateEvents();
        Set<Event> eventSet = GetSet(eventList);
        FindByFilter findByFilter = new FindByFilter();
        Set<Event> foundEvents = findByFilter.findEvents(eventSet, "Other");

        Set<Event> event3set = new HashSet<>();

        event3set.add(eventList.get(2));
        assert(foundEvents.equals(event3set));
    }

    @org.junit.Test
    public void testFindByFilterForMany() {
        List<Event> eventList = CreateEvents();
        Set<Event> eventSet = GetSet(eventList);
        FindByFilter findByFilter = new FindByFilter();
        Set<Event> foundEvents = findByFilter.findEvents(eventSet, "Food and Drinks");

        Set<Event> event15set = new HashSet<>();

        event15set.add(eventList.get(0));
        event15set.add(eventList.get(4));
        assert(foundEvents.equals(event15set));
    }
}
