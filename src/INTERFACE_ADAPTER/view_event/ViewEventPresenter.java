package INTERFACE_ADAPTER.view_event;


import INTERFACE_ADAPTER.ViewManagerModel;
import INTERFACE_ADAPTER.add_event.AddEventState;
import USE_CASE.view_event.ViewEventOutputBoundary;
import USE_CASE.view_event.ViewEventOutputData;



public class ViewEventPresenter implements ViewEventOutputBoundary {
    private final ViewEventViewModel viewEventViewModel;
    private final ViewManagerModel viewManagerModel;

    public ViewEventPresenter(ViewEventViewModel viewEventViewModel, ViewManagerModel viewManagerModel) {
        this.viewEventViewModel = viewEventViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    public void successesView(ViewEventOutputData event) {

        ViewEventState viewEventState = viewEventViewModel.getState();
        viewEventState.setDetails(event.getEventId(),event.getEventName(),event.getType(), event.getDescription(), event.getEventDate(), event.getEventTime(), event.getCreator(), event.getRegisteredUsers());
        viewEventViewModel.setState(viewEventState);
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        ViewEventState viewEventState = viewEventViewModel.getState();
        viewEventState.setError(error);
        viewEventViewModel.firePropertyChanged();

    }


}
