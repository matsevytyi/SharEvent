package interface_adapter.view_event;


import org.jxmapviewer.JXMapViewer;
import use_case.add_event.AddEventInputBoundary;
import use_case.add_event.AddEventInputData;
import use_case.view_event.ViewEventInputBoundary;
import use_case.view_event.ViewEventInputData;

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class ViewEventController {


    public ViewEventController(ViewEventInputBoundary viewEventUseCaseInteractor) {
        this.viewEventUseCaseInteractor = viewEventUseCaseInteractor;
    }

    final ViewEventInputBoundary viewEventUseCaseInteractor;


    public void execute(double latitude, double longitude, JXMapViewer mapViewer) {
        ViewEventInputData inputData= new ViewEventInputData(latitude, longitude, mapViewer);
        viewEventUseCaseInteractor.execute(inputData);
    }


}
