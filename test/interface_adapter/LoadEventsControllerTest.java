package interface_adapter;

import INTERFACE_ADAPTER.loadevents_adapter.LoadEventsController;
import org.junit.Assert;
import org.junit.Test;

public class LoadEventsControllerTest {
    @Test
    public void test_getters() {
        LoadEventsController loadEventsController = new LoadEventsController();
        Assert.assertNull(loadEventsController.getLoadEventsInteractor());
    }
}