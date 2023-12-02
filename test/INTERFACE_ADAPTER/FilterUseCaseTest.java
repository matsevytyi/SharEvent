package INTERFACE_ADAPTER;

import DATA_ACCESS.DatabaseDAO;
import ENTITY.Event;
import ENTITY.EventFactory;
import ENTITY.EventFactoryInterface;
import ENTITY.User;
import VIEW.FilterEventsView;
import VIEW_CREATOR.FilterEventsViewFactory;
import VIEW_CREATOR.LoadMapViewModel;
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

public class FilterUseCaseTest {
    static String message;
    static boolean popUpDiscovered;
    public Event addEvent() {
        EventFactoryInterface eventFactory = new EventFactory();
        DatabaseDAO databaseDAO = new DatabaseDAO();
        User newUser = databaseDAO.getUserByUsername("ff");
        List<User> attendees = new ArrayList<>();
        Event event = eventFactory.create("TestEvent", "Food and Drinks", "Example Event", LocalDate.now(), LocalTime.now(), newUser, attendees, 43.645306, -79.380247);

        databaseDAO.addEvent(event);
        return event;
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

        FilterEventsView filterEventsView = MakeFilterEventsView();
        filterEventsView.getController().setEvents(allEvents);

        JRadioButton foodButton = getFilterButton(3);
        foodButton.setSelected(true);

        JButton applyButton = getButton(1);
        applyButton.doClick();

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

        FilterEventsView filterEventsView = MakeFilterEventsView();
        filterEventsView.getController().setEvents(allEvents);

        JRadioButton foodButton = getFilterButton(2);
        foodButton.setSelected(true);

        JButton applyButton = getButton(1);
        applyButton.doClick();

        JXMapKit mapKit = filterEventsView.getViewModel().getMapKit();

        Set<Event> eventsShown = ((WaypointPainter<Event>)mapKit.getMainMap().getOverlayPainter()).getWaypoints();

        Set<Integer> eventsShownID = new HashSet<>();
        for(Event event2 : eventsShown) {
            eventsShownID.add(event2.getEventId());
        }

        assert (!eventsShownID.contains(event_id));
        databaseDAO.deleteEvent(event_id);

    }

    @org.junit.Test
    public void testShowAllFilter() {
        DatabaseDAO databaseDAO = new DatabaseDAO();
        Set<Event> allEvents = databaseDAO.FilterEvents("");

        FilterEventsView filterEventsView = MakeFilterEventsView();
        filterEventsView.getController().setEvents(allEvents);

        JRadioButton foodButton = getFilterButton(2);
        foodButton.setSelected(true);

        JButton applyButton = getButton(1);
        applyButton.doClick();

        JRadioButton showAllButton = getFilterButton(8);
        showAllButton.setSelected(true);

        applyButton.doClick();

        JXMapKit mapKit = filterEventsView.getViewModel().getMapKit();
        Set<Event> eventsShown = ((WaypointPainter<Event>)mapKit.getMainMap().getOverlayPainter()).getWaypoints();

        assert (eventsShown.equals(allEvents));

    }

    @org.junit.Test
    public void testEmptyApplyButtonPopUp() {
        MakeFilterEventsView();
        JButton applyButton = getButton(1);
        createCloseTimer().start();
        applyButton.doClick();

        assert(popUpDiscovered);

    }

    @org.junit.Test
    public void testNoEventsFoundPopUp() {
        DatabaseDAO databaseDAO = new DatabaseDAO();
        Set<Event> allEvents = databaseDAO.FilterEvents("");

        FilterEventsView filterEventsView = MakeFilterEventsView();
        filterEventsView.getController().setEvents(allEvents);

        createCloseTimer().start();
        filterEventsView.getController().execute("NotRealType", filterEventsView.getViewModel());
        assert(popUpDiscovered);
    }

    @org.junit.Test
    public void testCancelButtonDisposal() {
        FilterEventsView filterEventsView = MakeFilterEventsView();
        filterEventsView.showMenu();
        JButton cancelButton = getButton(0);
        cancelButton.doClick();
        assert (!filterEventsView.getFrame().isVisible());
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

    public JRadioButton getFilterButton(int i) {
        JFrame mainFrame = getFrame();

        mainFrame.getComponent(0);
        Component root = mainFrame.getComponent(0);
        Component contentPant = ((JRootPane) root).getContentPane();
        JPanel jPanel = (JPanel) contentPant;
        JPanel mainPanel = (JPanel) jPanel.getComponent(0);
        JPanel filterButtonsPanel = (JPanel) mainPanel.getComponent(1);
        JRadioButton button = (JRadioButton) filterButtonsPanel.getComponent(i);

        return button;

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
                            FilterUseCaseTest.message = s;
                            FilterUseCaseTest.popUpDiscovered = true;

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
