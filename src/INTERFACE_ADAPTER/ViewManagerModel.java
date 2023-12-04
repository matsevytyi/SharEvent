
package INTERFACE_ADAPTER;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ViewManagerModel {

    private String activeViewName;

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Sets the name of the currently active view.
     * @param activeView The name of the active view.
     */
    public void setActiveView(String activeView) {
        this.activeViewName = activeView;
    }

    /**
     * Notifies observers about property changes in the active view.
     */
    public void firePropertyChanged() {
        support.firePropertyChange("VIEW", null, this.activeViewName);
    }
    /**
     * Adds a property change listener to observe changes in the active view.
     * @param listener The property change listener to be added.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
