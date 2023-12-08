package USE_CASE.loadmap;

import lombok.Getter;
import org.jxmapviewer.JXMapKit;

/** passes ready-to-be-interacted and adjusted map
 * from Interactor to Presenter.
 * This is the ma[p that user will interact with in future*/
public class LoadMapInputData {

    @Getter
    JXMapKit mapKit;

    public LoadMapInputData(JXMapKit mapKit) {
        this.mapKit = mapKit;
    }
}
