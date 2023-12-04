package interface_adapter;

import ENTITY.User;
import INTERFACE_ADAPTER.loadmap_adapter.LoadMapState;
import USE_CASE.login.LoginOutputData;
import org.junit.Assert;
import org.junit.Test;

public class LoadMapStateTest {
    @Test
    public void testLoadMapState() {
        LoadMapState loadMapState = new LoadMapState();
        LoadMapState copy = new LoadMapState(loadMapState);
        Assert.assertEquals(copy.getUsername(), loadMapState.getUsername());
        LoginOutputData User = new LoginOutputData(new User("test", "test", "test", "test"), false);
        loadMapState.setUsername(User);
        Assert.assertNotEquals(copy.getUsername(), loadMapState.getUsername());
    }
}
