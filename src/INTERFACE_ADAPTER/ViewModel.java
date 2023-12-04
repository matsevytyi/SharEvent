package INTERFACE_ADAPTER;

import java.beans.PropertyChangeListener;

public abstract class ViewModel {

    private String viewName;

    public ViewModel(String viewName) {
        this.viewName = viewName;
    }
    /**
     * Пуеі the name of the currently active view.
     * @return The view name of the active view.
     */
    public String getViewName() {
        return this.viewName;
    }

    public abstract void firePropertyChanged();
    public abstract void addPropertyChangeListener(PropertyChangeListener listener);


}
