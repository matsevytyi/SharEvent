
package USE_CASE.loadmap;

import lombok.Getter;
import org.jxmapviewer.JXMapKit;

public class LoadMapOutputData {

    @Getter
    JXMapKit mapKit;

    public LoadMapOutputData() {
        mapKit = new JXMapKit();
    }
}
