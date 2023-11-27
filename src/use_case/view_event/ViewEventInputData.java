package use_case.view_event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jxmapviewer.JXMapViewer;

@Getter @AllArgsConstructor
public class ViewEventInputData {

    private final double latitude;
    private final double longitude;
    private final JXMapViewer mapViewer;

}
