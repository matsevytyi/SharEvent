package INTERFACE_ADAPTER.add_event;


import INTERFACE_ADAPTER.ViewModel;
import org.jxmapviewer.viewer.GeoPosition;


import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
/**
 * The {@code AddEventViewModel} class serves as the view model for the "add event" functionality.
 * It extends the {@link ViewModel} class and encapsulates the state of the view, including the
 * current state of the "add event" form and methods for managing property change events.
 */
public class AddEventViewModel extends ViewModel {

    public static final String ADD_EVENT_BUTTON_LABEL = "Add event";


    private AddEventState state = new AddEventState();

    public AddEventViewModel() {

        super("Add event");
    }
    /**
     * Sets the state of the "add event" form.
     *
     * @param state The state object representing the current state of the form.
     */
    public void setState(AddEventState state) {
        this.state = state;
    }


    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    /**
     * Notifies observers about property changes in the view model.
     */
    public void firePropertyChanged() {
        support.firePropertyChange("add", null, this.state);
    }
    /**
     * Adds a property change listener to the view model.
     *
     * @param listener The property change listener to be added.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    /**
     * Gets the current state of the "add event" form.
     *
     * @return The state object representing the current state of the form.
     */
    public  AddEventState getState() {
        return state;
    }
    /**
     * Sets the latitude and longitude of the event location based on the provided {@link GeoPosition}.
     *
     * @param clickedPosition The geographic position representing the clicked location.
     */
    public void setClickedPosition(GeoPosition clickedPosition) {
        state.setEventLatitude(clickedPosition.getLatitude());
        state.setEventLongitude(clickedPosition.getLongitude());
    }


}
