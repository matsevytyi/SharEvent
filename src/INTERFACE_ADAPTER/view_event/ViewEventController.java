package INTERFACE_ADAPTER.view_event;


import USE_CASE.view_event.ViewEventInputBoundary;
import USE_CASE.view_event.ViewEventInputData;
import org.jxmapviewer.JXMapViewer;


import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class ViewEventController {


    public ViewEventController(ViewEventInputBoundary viewEventUseCaseInteractor) {
        this.viewEventUseCaseInteractor = viewEventUseCaseInteractor;
    }

    public final ViewEventInputBoundary viewEventUseCaseInteractor;


    public void execute(double latitude, double longitude, JXMapViewer mapViewer) {
        ViewEventInputData inputData= new ViewEventInputData(latitude, longitude, mapViewer);
        viewEventUseCaseInteractor.execute(inputData);
    }


}
