package INTERFACE_ADAPTER.add_event;


import INTERFACE_ADAPTER.ViewModel;
import org.jxmapviewer.viewer.GeoPosition;


import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AddEventViewModel extends ViewModel {

    public static final String ADD_EVENT_BUTTON_LABEL = "Add event";


    private AddEventState state = new AddEventState();

    public AddEventViewModel() {

        super("Add event");
    }

    public void setState(AddEventState state) {
        this.state = state;
    }


    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("add", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public  AddEventState getState() {
        return state;
    }

    public void setClickedPosition(GeoPosition clickedPosition) {
        state.setEventLatitude(clickedPosition.getLatitude());
        state.setEventLongitude(clickedPosition.getLongitude());
    }


}
