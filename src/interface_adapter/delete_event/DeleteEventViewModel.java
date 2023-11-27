package interface_adapter.delete_event;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class DeleteEventViewModel extends ViewModel {

    public DeleteEventState state = new DeleteEventState();

    public DeleteEventViewModel(){
        super("clear");
    }

    public void setState(DeleteEventState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);


    public void firePropertyChanged() {
        support.firePropertyChange("clear", null, this.state);
    }


    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public DeleteEventState getState(){
        return state;
    }
}
