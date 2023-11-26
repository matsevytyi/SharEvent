package INTERFACE_ADAPTER;

import lombok.Getter;
import org.jxmapviewer.JXMapKit;

public class LoadMapOutputData {

    @Getter
    JXMapKit mapKit;

    public LoadMapOutputData() {
        mapKit = new JXMapKit();
    }
}
