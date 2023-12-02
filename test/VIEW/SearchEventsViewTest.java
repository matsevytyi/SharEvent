package VIEW;

import VIEW_CREATOR.LoadMapViewModel;
import VIEW_CREATOR.SearchEventsViewFactory;

import javax.swing.*;
import java.awt.*;

import static org.junit.Assert.assertNotNull;

public class SearchEventsViewTest {
    public void MakeSearchEventsView(){
        LoadMapViewModel viewModel = new LoadMapViewModel();
        SearchEventsViewFactory searchEventsViewFactory = new SearchEventsViewFactory();
        searchEventsViewFactory.create(viewModel);
    }

    public JFrame getFrame(){
        JFrame mainFrame = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                mainFrame = (JFrame) window;
            }
        }

        assertNotNull(mainFrame); // found the window?
        return mainFrame;
    }

    public JButton getButton(int i) {
        JFrame mainFrame = getFrame();

        mainFrame.getComponent(0);
        Component root = mainFrame.getComponent(0);
        Component contentPant = ((JRootPane) root).getContentPane();
        JPanel jPanel = (JPanel) contentPant;
        JPanel mainPanel = (JPanel) jPanel.getComponent(0);
        JPanel buttonsPanel = (JPanel) mainPanel.getComponent(2);
        JButton button = (JButton) buttonsPanel.getComponent(i);

        return button;

    }

    @org.junit.Test
    public void testCancelButtonPresent() {
        MakeSearchEventsView();
        JButton button = getButton(0);
        assert(button.getText().equals("Cancel"));
    }

    @org.junit.Test
    public void testApplyButtonPresent() {
        MakeSearchEventsView();
        JButton button = getButton(1);
        assert(button.getText().equals("Apply"));
    }

    @org.junit.Test
    public void testCheckVisibility() {
        MakeSearchEventsView();
        JFrame mainFrame = getFrame();
        assert (mainFrame.isVisible());  // Search view is visible on start
    }

}
