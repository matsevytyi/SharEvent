package INTERFACE_ADAPTER.view_profile;


import INTERFACE_ADAPTER.ViewModel;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.GeoPosition;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ViewProfileViewModel extends ViewModel {
    private static final String VIEW_NAME = "View Event";

    private ViewProfileState state = new ViewProfileState();

    public ViewProfileViewModel() {
        super("View Event");
    }


    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("VIEW", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public String getViewName() {
        return VIEW_NAME;
    }


    public ViewProfileState getState() {
        return state;
    }

    public void setState(ViewProfileState state) {
        this.state = state;
    }

    public void setClickedPosition(GeoPosition clickedPosition) {
        state.setLatitude(clickedPosition.getLatitude());
        state.setLongitude(clickedPosition.getLongitude());
    }

    public void setMapViewer(JXMapViewer mapViewer) {
        state.setMapViewer(mapViewer);
    }

    public void setLoggedInUser(String loggedInUser) {
        state.setLoggedinuser(loggedInUser);
    }

    public String getLoggedInUser() {
       return state.getLoggedinuser();
    }
}
