package view2;

import interface_adapter2.LoadEventsController;
import interface_adapter2.LoadEventsPresenter;
import javafx.scene.layout.StackPane;
import lombok.Getter;
import org.jxmapviewer.JXMapViewer;

import java.awt.*;

public class LoadEventsView {

    @Getter
    private static LoadEventsPresenter presenter;

    @Getter
    private LoadEventsController controller;

    private JXMapViewer mapViewer;

    private LoadMapView loadMapView;


    public StackPane getInitialPane() {
        return loadMapView.getStackPane();
    }

    public LoadEventsView(LoadMapView loadMapView) {

        this.mapViewer = loadMapView.getViewModel().getMapKit().getMainMap();
        this.controller = new LoadEventsController();
        this.loadMapView = loadMapView;

        //TODO: For VIEW_EVENT USECASE, you may move it somewhere else if needed

        mapViewer.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                Point clickPoint = e.getPoint();
                System.out.println("clicked" + e.getX() + "\t" + e.getY() + "\t" +  e);
                controller.checkForEvent(clickPoint, mapViewer);
            }
        });
    }

    //TODO: think of changing LoadMapView to LoadEventsView, where StackPane will be accessed via appropriate Getter, in future

    public void reloadEvents(LoadMapView loadMapView){
        controller.execute(loadMapView);
    }


}
