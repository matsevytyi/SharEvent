package interface_adapter.view_event;

import interface_adapter.add_event.AddEventState;
import use_case.view_event.ViewEventOutputBoundary;
import use_case.view_event.ViewEventOutputData;
import view.ViewManagerModel;


public class ViewEventPresenter implements ViewEventOutputBoundary {
    private final ViewEventViewModel viewEventViewModel;
    private ViewManagerModel viewManagerModel;

    public ViewEventPresenter(ViewEventViewModel viewEventViewModel, ViewManagerModel viewManagerModel) {
        this.viewEventViewModel = viewEventViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    public void successesView(ViewEventOutputData event) {
        // Update the ViewModel with event details
        ViewEventState viewEventState = viewEventViewModel.getState();
        viewEventState.setDetails(event.getEventName(),event.getDescription(), event.getEventDate(), event.getEventTime(), event.getCreator(), event.getRegisteredUsers());


        viewManagerModel.firePropertyChanged();
    }
}
