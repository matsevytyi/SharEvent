package DAO;

import DATA_ACCESS.loadevents_dataaccess.LoadEventsDAO_InputData;
import INTERFACE_ADAPTER.loadevents_adapter.LoadEventsOuputData;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.jxmapviewer.viewer.GeoPosition;

public class DAOAdditionaTests {

    @Test
    public void test_DAO_loadEvents(){
        LoadEventsDAO_InputData loadEventsDAO_InputData = new LoadEventsDAO_InputData(new GeoPosition(0,0));
        Assert.assertEquals(loadEventsDAO_InputData.getLatitude1(), "-0.04504504504504504");
        Assert.assertEquals(loadEventsDAO_InputData.getLatitude2(), "0.04504504504504504");
        Assert.assertEquals(loadEventsDAO_InputData.getLongitude1(), "-0.04504504504504504");
        Assert.assertEquals(loadEventsDAO_InputData.getLongitude2(), "0.04504504504504504");
    }
}
