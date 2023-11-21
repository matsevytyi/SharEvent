package use_case;

import org.jxmapviewer.JXMapViewer;

import java.awt.*;

public interface LoadEventsInputBoundary {
    public void execute();

    public boolean checkForClickOnEvent(Point clickPoint, JXMapViewer mapViewer);
}
