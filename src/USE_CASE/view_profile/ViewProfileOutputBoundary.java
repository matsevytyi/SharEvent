package USE_CASE.view_profile;

import INTERFACE_ADAPTER.view_event.ViewEventState;

public interface ViewProfileOutputBoundary {
    void successesView(ViewProfileOutputData profileIOutputData);


     void prepareFailView(String error);
}