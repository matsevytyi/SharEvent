package VIEW;



import INTERFACE_ADAPTER.load_events.LoadEventsController;
import INTERFACE_ADAPTER.load_events.LoadEventsPresenter;
import javafx.scene.layout.StackPane;
import lombok.Getter;
import org.jxmapviewer.JXMapViewer;

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

//        mapViewer.addMouseListener(new java.awt.event.MouseAdapter() {
//            @Override
//            public void mouseClicked(java.awt.event.MouseEvent e) {
//                Point clickPoint = e.getPoint();
//                System.out.println("clicked" + e.getX() + "\t" + e.getY() + "\t" +  e);
//                controller.checkForEvent(clickPoint, mapViewer);
//            }
//        });
    }

    //TODO: think of changing LoadMapView to LoadEventsView, where StackPane will be accessed via appropriate Getter, in future

    public void reloadEvents(VIEW.LoadMapView loadMapView){
        controller.execute(loadMapView);
    }


}
