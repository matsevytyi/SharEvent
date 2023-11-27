package use_case.view_event;


import data_access.LoadEventsDataAccessInterface;
import entity.Event;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.GeoPosition;

import java.awt.*;
import java.awt.geom.Point2D;

public class ViewEventInteractor implements ViewEventInputBoundary {
    private final LoadEventsDataAccessInterface eventDataAccessInterface;
    private final ViewEventOutputBoundary viewEventPresenter;

    public ViewEventInteractor(LoadEventsDataAccessInterface eventDataAccessInterface, ViewEventOutputBoundary viewEventPresenter) {
        this.eventDataAccessInterface = eventDataAccessInterface;
        this.viewEventPresenter = viewEventPresenter;
    }

    @Override
    public void execute(ViewEventInputData position) {

        Event event = eventDataAccessInterface.getEventByPosition(position.getLatitude(), position.getLongitude(), position.getMapViewer());

        if (event != null) {
            ViewEventOutputData outputData = new ViewEventOutputData(
                    event.getEventId(),
                    event.getEventName(),
                    event.getType(),
                    event.getDescription(),
                    event.getEventDate(),
                    event.getEventTime(),
                    event.getCreator().getName(),
                    event.getEventAttendants().toString()

            );;
            viewEventPresenter.successesView(outputData);
        } // додати фейл сітуейшн
    }



}
