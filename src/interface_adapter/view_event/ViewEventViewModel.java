package interface_adapter.view_event;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ViewEventViewModel extends ViewModel {
    private static final String VIEW_NAME = "View Event";



    private ViewEventState state = new ViewEventState();

    public ViewEventViewModel() {
        super("View Event");
    }




    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("view", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }



    public String getViewName() {
        return VIEW_NAME;
    }


    public ViewEventState getState() {
        return state;
    }

    public void setState(ViewEventState state) {
        this.state = state;
    }
}
