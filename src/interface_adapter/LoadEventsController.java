package interface_adapter;

import lombok.Getter;
import org.jxmapviewer.JXMapViewer;

import java.awt.*;
import java.util.Set;

import use_case.LoadEventsInputBoundary;

public class LoadEventsController {
    JXMapViewer mapViewer;

    @Getter
    LoadEventsInputBoundary loadEventsInteractor;

    public LoadEventsController(JXMapViewer mapViewer) {
        this.mapViewer = mapViewer;
        mapViewer.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                Point clickPoint = e.getPoint();
                loadEventsInteractor.checkForClickOnEvent(clickPoint, mapViewer);
            }
        });
    }


}
