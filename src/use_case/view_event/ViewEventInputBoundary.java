package use_case.view_event;


import org.jxmapviewer.JXMapViewer;
import use_case.add_event.AddEventInputData;

import java.awt.*;

public interface ViewEventInputBoundary {
    void execute(ViewEventInputData viewEventInputData);


    //void checkForEvent(Point clickPoint, JXMapViewer mapViewer);
}

