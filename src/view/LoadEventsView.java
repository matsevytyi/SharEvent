package view;

import interface_adapter.LoadEventsController;
import interface_adapter.LoadEventsPresenter;
import javafx.scene.layout.StackPane;
import lombok.Getter;
import org.jxmapviewer.JXMapViewer;

import java.awt.*;

public class LoadEventsView {

    @Getter
    private static LoadEventsPresenter presenter;

    @Getter
    private LoadEventsController controller;

    private LoadMapView loadMapViewResult;

    private JXMapViewer mapViewer;


    public StackPane getInitialPane() {
        return loadMapViewResult.getStackPane();
    }

    public LoadEventsView(LoadMapView loadMapViewResult) {
        presenter = new LoadEventsPresenter(loadMapViewResult, this);
        mapViewer = presenter.getMapKit().getMainMap();
        controller = new LoadEventsController(mapViewer, presenter);
        this.loadMapViewResult = loadMapViewResult;

        mapViewer.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                Point clickPoint = e.getPoint();
                System.out.println("clicked" + e.getX() + "\t" + e.getY() + "\t" +  e);
                controller.execute(clickPoint, mapViewer);
            }
        });
    }


}
