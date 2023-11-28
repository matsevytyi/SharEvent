
package INTERFACE_ADAPTER.loadmap_adapter;

import lombok.Getter;
import org.jxmapviewer.JXMapKit;

public class LoadMapOutputData {

    @Getter
    JXMapKit mapKit;

    public LoadMapOutputData() {
        mapKit = new JXMapKit();
    }
}
