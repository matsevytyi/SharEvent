package USE_CASE;

import USE_CASE.loadevents.LoadEventsOuputData;
import USE_CASE.loadevents.LoadEventsInteractor;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.jxmapviewer.viewer.GeoPosition;

public class LoadEventsInteractorTest {
    @Test
    public void test_getters(){
        LoadEventsInteractor loadEventsInteractor = new LoadEventsInteractor(new LoadEventsOuputData(new GeoPosition(0, 0)));
        Assert.assertNotNull(loadEventsInteractor.getEvents());
        Assert.assertNotNull(loadEventsInteractor.getCurrentGeoposition());
        Assert.assertNotNull(loadEventsInteractor.getProblem());
    }
}
