package interface_adapter.view_event;


import use_case.view_event.ViewEventOutputBoundary;
import use_case.view_event.ViewEventOutputData;
import view.ViewManagerModel;


public class ViewEventPresenter implements ViewEventOutputBoundary {
    private final ViewEventViewModel viewEventViewModel;
    private final ViewManagerModel viewManagerModel;

    public ViewEventPresenter(ViewEventViewModel viewEventViewModel, ViewManagerModel viewManagerModel) {
        this.viewEventViewModel = viewEventViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    public void successesView(ViewEventOutputData event) {

        ViewEventState viewEventState = viewEventViewModel.getState();
        viewEventState.setDetails(event.getEventName(),event.getType(), event.getDescription(), event.getEventDate(), event.getEventTime(), event.getCreator(), event.getRegisteredUsers());
        viewEventViewModel.setState(viewEventState);
        viewManagerModel.firePropertyChanged();
    }
}
