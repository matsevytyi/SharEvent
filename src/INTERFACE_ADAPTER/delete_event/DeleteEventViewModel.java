package INTERFACE_ADAPTER.delete_event;



import INTERFACE_ADAPTER.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The class serves as the view model for the "delete event" functionality.
 * It extends the ViewModel class and encapsulates the state of the view
 * along with methods for managing property change events.
 */
public class DeleteEventViewModel extends ViewModel {

    public DeleteEventState state = new DeleteEventState();

    public DeleteEventViewModel(){
        super("clear");
    }
    /**
     * This method sets the state of the "delete event" form.
     * @param state The state object representing the current state of the form.
     */
    public void setState(DeleteEventState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Notifies observers about property changes in the view model.
     */
    public void firePropertyChanged() {
        support.firePropertyChange("clear", null, this.state);
    }

    /**
     * This method adds a property change listener to the view model.
     * @param listener The property change listener to be added.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * This method gets the current state of the "delete event" form.
     * @return The state object representing the current state of the form.
     */
    public DeleteEventState getState(){
        return state;
    }
}
