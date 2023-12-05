package INTERFACE_ADAPTER.view_profile;


import INTERFACE_ADAPTER.ViewModel;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.GeoPosition;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
/**
 * The  class is the view model for the "view profile" functionality.
 * It extends the  ViewModel class and encapsulates the state of the view
 * along with methods for managing property change events.
 */
public class ViewProfileViewModel extends ViewModel {
    private static final String VIEW_NAME = "View Profile";

    private ViewProfileState state = new ViewProfileState();

    public ViewProfileViewModel() {
        super("View Profile");
    }


    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("PROFILE", null, this.state);
    }
    /**
     * Method adds a property change listener to the view model.
     * @param listener The property change listener to be added.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    /**
     * Method gets the name associated with the "view event" functionality.
     * @return The name of the view.
     */
    public String getViewName() {
        return VIEW_NAME;
    }

    /**
     * Method gets the current state of the "view profile" form.
     * @return The state object representing the current state of the form.
     */
    public ViewProfileState getState() {
        return state;
    }
    /**
     * This method ets the state of the "view profile" form.
     * @param state The state object representing the current state of the form.
     */
    public void setState(ViewProfileState state) {
        this.state = state;
    }


}
