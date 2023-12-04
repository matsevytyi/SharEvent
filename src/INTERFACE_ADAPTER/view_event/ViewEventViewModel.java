package INTERFACE_ADAPTER.view_event;


import INTERFACE_ADAPTER.ViewModel;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.GeoPosition;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
/**
 * The  class serves as the view model for the "view event" functionality.
 * It extends the  ViewModel class and encapsulates the state of the view
 * along with methods for managing property change events.
 */
public class ViewEventViewModel extends ViewModel {
    private static final String VIEW_NAME = "View Event";

    private ViewEventState state = new ViewEventState();

    public ViewEventViewModel() {
        super("View Event");
    }


    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("VIEW", null, this.state);
    }
    /**
     * Adds a property change listener to the view model.
     * @param listener The property change listener to be added.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    /**
     * Gets the name associated with the "view event" functionality.
     * @return The name of the view.
     */
    public String getViewName() {
        return VIEW_NAME;
    }
    /**
     * Gets the current state of the "view event" form.
     * @return The state object representing the current state of the form.
     */

    public ViewEventState getState() {
        return state;
    }
    /**
     * This method ets the state of the "view event" form.
     * @param state The state object representing the current state of the form.
     */
    public void setState(ViewEventState state) {
        this.state = state;
    }
    /**
     * This method sets the map viewer for the "view event" form.
     * @param mapViewer used to display the map.
     */
    public void setMapViewer(JXMapViewer mapViewer){
        state.setMapViewer(mapViewer);
    }

    /**
     *This method sets the clicked position on the map for the "view event" form.
     * @param clickedPosition The geographical position clicked on the map.
     */
    public void setClickedPosition(GeoPosition clickedPosition) {
        state.setLatitude(clickedPosition.getLatitude());
        state.setLongitude(clickedPosition.getLongitude());
    }
    /**
     * This method sets the username of the logged-in user for the "view event" form.
     * @param loggedInUser The username of the logged-in user.
     */
    public void setLoggedInUser(String loggedInUser) {
        state.setLoggedinuser(loggedInUser);
    }
    /**
     * This method sets the username of the logged-in user for the "view event" form.
     * @return The username of the logged-in user.
     */
    public String getLoggedInUser() {
       return state.getLoggedinuser();
    }
}
