
package USE_CASE.loadmap;

import lombok.Getter;
import org.jxmapviewer.JXMapKit;

/** passes user IP and new mapKit (responsible for interactive map implementing)
 * to Interactor
 * (UPD: I am using API that automaticaly retrieves user IP
 * from the request, made on user device)*/
public class LoadMapOutputData {

    @Getter
    JXMapKit mapKit;

    public LoadMapOutputData() {
        mapKit = new JXMapKit();
    }
}
