package interface_adapter.view_event;


import use_case.add_event.AddEventInputBoundary;
import use_case.add_event.AddEventInputData;
import use_case.view_event.ViewEventInputBoundary;
import use_case.view_event.ViewEventInputData;

import java.time.LocalDate;
import java.time.LocalTime;

public class ViewEventController {


    public ViewEventController(ViewEventInputBoundary viewEventUseCaseInteractor) {
        this.viewEventUseCaseInteractor = viewEventUseCaseInteractor;
    }

    final ViewEventInputBoundary viewEventUseCaseInteractor;


    public void execute(ViewEventInputData viewEventInputData) {
        viewEventUseCaseInteractor.execute(viewEventInputData);
    }

}
