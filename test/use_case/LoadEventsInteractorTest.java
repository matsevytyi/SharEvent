package USE_CASE;

import INTERFACE_ADAPTER.loadevents_adapter.LoadEventsOuputData;
import INTERFACE_ADAPTER.loadmap_adapter.LoadMapOutputData;
import USE_CASE.loadevents.LoadEventsInteractor;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.jxmapviewer.viewer.GeoPosition;

import java.lang.reflect.Field;

public class LoadEventsInteractorTest {
    @Test
    public void test_getters(){
        LoadEventsInteractor loadEventsInteractor = new LoadEventsInteractor(new LoadEventsOuputData(new GeoPosition(0, 0)));
        Assert.assertNotNull(loadEventsInteractor.getEvents());
        Assert.assertNotNull(loadEventsInteractor.getCurrentGeoposition());
        Assert.assertNotNull(loadEventsInteractor.getProblem());
    }
}
