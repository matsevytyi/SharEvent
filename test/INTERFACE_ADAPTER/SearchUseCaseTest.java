package INTERFACE_ADAPTER;

import DATA_ACCESS.DatabaseDAO;
import ENTITY.Event;
import ENTITY.EventFactory;
import ENTITY.EventFactoryInterface;
import ENTITY.User;
import VIEW.SearchEventsView;
import VIEW_CREATOR.LoadMapViewModel;
import VIEW_CREATOR.SearchEventsViewFactory;
import org.jxmapviewer.JXMapKit;
import org.jxmapviewer.viewer.WaypointPainter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertNotNull;

public class SearchUseCaseTest {
    static String message;
    static boolean popUpDiscovered;
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
        Set<Event> allEvents = databaseDAO.SearchEvent("");
        int event_id = -1;
        for(Event event2 : allEvents) {
            if (event2.getEventName().equals(event.getEventName())){
                event_id = event2.getEventId();
            }
        }

        SearchEventsView searchEventsView = MakeSearchEventsView();
        searchEventsView.getController().setEvents(allEvents);

        JTextField searchTextField = getTextField();
        searchTextField.setText("TestEvent");

        JButton applyButton = getButton(1);
        applyButton.doClick();

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
        Set<Event> allEvents = databaseDAO.SearchEvent("");

        int event_id = -1;
        for(Event event2 : allEvents) {
            if (event2.getEventName().equals(event.getEventName())){
                event_id = event2.getEventId();
            }
        }

        SearchEventsView searchEventsView = MakeSearchEventsView();
        searchEventsView.getController().setEvents(allEvents);

        JTextField searchTextField = getTextField();
        searchTextField.setText("TestEvent1");

        JButton applyButton = getButton(1);
        applyButton.doClick();

        JXMapKit mapKit = searchEventsView.getViewModel().getMapKit();

        Set<Event> eventsShown = ((WaypointPainter<Event>)mapKit.getMainMap().getOverlayPainter()).getWaypoints();
        Set<Integer> eventsShownID = new HashSet<>();
        for(Event event2 : eventsShown) {
            eventsShownID.add(event2.getEventId());
        }

        assert (!eventsShownID.contains(event_id));
        databaseDAO.deleteEvent(event_id);

    }

    @org.junit.Test
    public void testNoEventsFoundPopUp(){
        DatabaseDAO databaseDAO = new DatabaseDAO();
        Set<Event> allEvents = databaseDAO.SearchEvent("");

        SearchEventsView searchEventsView = MakeSearchEventsView();
        searchEventsView.getController().setEvents(allEvents);
        createCloseTimer().start();
        searchEventsView.getController().execute("TestEvent12345677");

        assert(popUpDiscovered);
    }

    @org.junit.Test
    public void testCancelButtonFunctionality(){

        DatabaseDAO databaseDAO = new DatabaseDAO();
        Set<Event> allEvents = databaseDAO.SearchEvent("");

        SearchEventsView searchEventsView = MakeSearchEventsView();
        searchEventsView.getController().setEvents(allEvents);
        searchEventsView.getController().execute("TestEvent");
        JButton cancelButton = getButton(0);
        cancelButton.doClick();

        JXMapKit mapKit = searchEventsView.getViewModel().getMapKit();

        Set<Event> eventsShown = ((WaypointPainter<Event>)mapKit.getMainMap().getOverlayPainter()).getWaypoints();

        assert (eventsShown.equals(allEvents));

    }

    @org.junit.Test
    public void testEmptyStringApplyButtonPopUp() {

        MakeSearchEventsView();
        popUpDiscovered = false;

        JButton applyButton = getButton(1);
        createCloseTimer().start();
        applyButton.doClick();

        assert(popUpDiscovered);

    }
    public JFrame getFrame(){
        JFrame mainFrame = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                mainFrame = (JFrame) window;
            }
        }

        assertNotNull(mainFrame); // found the window?
        return mainFrame;
    }

    public JButton getButton(int i) {
        JFrame mainFrame = getFrame();

        mainFrame.getComponent(0);
        Component root = mainFrame.getComponent(0);
        Component contentPant = ((JRootPane) root).getContentPane();
        JPanel jPanel = (JPanel) contentPant;
        JPanel mainPanel = (JPanel) jPanel.getComponent(0);
        JPanel buttonsPanel = (JPanel) mainPanel.getComponent(2);
        JButton button = (JButton) buttonsPanel.getComponent(i);

        return button;

    }

    public JTextField getTextField(){
        JFrame mainFrame = getFrame();

        mainFrame.getComponent(0);
        Component root = mainFrame.getComponent(0);
        Component contentPant = ((JRootPane) root).getContentPane();
        JPanel jPanel = (JPanel) contentPant;
        JPanel mainPanel = (JPanel) jPanel.getComponent(0);
        JTextField jTextField = (JTextField) mainPanel.getComponent(1);

        return jTextField;
    }

    private Timer createCloseTimer() {
        ActionListener close = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                Window[] windows = Window.getWindows();
                for (Window window : windows) {

                    if (window instanceof JDialog) {

                        JDialog dialog = (JDialog)window;

                        // this ignores old dialogs
                        if (dialog.isVisible()) {
                            String s = ((JOptionPane) ((BorderLayout) dialog.getRootPane()
                                    .getContentPane().getLayout()).getLayoutComponent(BorderLayout.CENTER)).getMessage().toString();
                            System.out.println("message = " + s);

                            // store the information we got from the JDialog
                            SearchUseCaseTest.message = s;
                            SearchUseCaseTest.popUpDiscovered = true;

                            window.dispose();
                        }
                    }
                }
            }

        };

        Timer t = new Timer(1000, close);
        t.setRepeats(false);
        return t;
    }

}
