package interface_adapter.load_map;

import lombok.Getter;
import org.jxmapviewer.JXMapKit;

public class LoadMapOutputData {

    @Getter
    JXMapKit mapKit;

    public LoadMapOutputData() {
        mapKit = new JXMapKit();
    }
}
