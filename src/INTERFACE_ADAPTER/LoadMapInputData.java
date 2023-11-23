package INTERFACE_ADAPTER;

import lombok.Getter;
import org.jxmapviewer.JXMapKit;

public class LoadMapInputData {

    @Getter
    JXMapKit mapKit;

    public LoadMapInputData(JXMapKit mapKit) {
        this.mapKit = mapKit;
    }
}
