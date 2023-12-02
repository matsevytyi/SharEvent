package VIEW;

import VIEW_CREATOR.FilterEventsViewFactory;
import VIEW_CREATOR.LoadMapViewModel;

import javax.swing.*;
import java.awt.*;

import static org.junit.Assert.assertNotNull;

public class FilterEventsViewTest {
    public FilterEventsView MakeFilterEventsView(){
        LoadMapViewModel viewModel = new LoadMapViewModel();
        FilterEventsViewFactory filterEventsViewFactory = new FilterEventsViewFactory();
        return filterEventsViewFactory.create(viewModel);
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

    public JRadioButton getFilterButton(int i) {
        JFrame mainFrame = getFrame();

        mainFrame.getComponent(0);
        Component root = mainFrame.getComponent(0);
        Component contentPant = ((JRootPane) root).getContentPane();
        JPanel jPanel = (JPanel) contentPant;
        JPanel mainPanel = (JPanel) jPanel.getComponent(0);
        JPanel filterButtonsPanel = (JPanel) mainPanel.getComponent(1);
        JRadioButton button = (JRadioButton) filterButtonsPanel.getComponent(i);

        return button;

    }

    @org.junit.Test
    public void testCancelButtonPresent() {
        MakeFilterEventsView();
        JButton button = getButton(0);
        assert(button.getText().equals("Cancel"));
    }

    @org.junit.Test
    public void testApplyButtonPresent() {
        MakeFilterEventsView();
        JButton button = getButton(1);
        assert(button.getText().equals("Apply"));
    }

    @org.junit.Test
    public void testTypeButtonPresent() {
        MakeFilterEventsView();
        JRadioButton button1 = getFilterButton(1);
        JRadioButton button2 = getFilterButton(2);
        JRadioButton button3 = getFilterButton(3);
        JRadioButton button4 = getFilterButton(4);
        JRadioButton button5 = getFilterButton(5);
        JRadioButton button6 = getFilterButton(6);
        JRadioButton button7 = getFilterButton(7);
        JRadioButton button8 = getFilterButton(8);
        assert(button1.getText().equals("Sports and Fitness"));
        assert(button2.getText().equals("Music"));
        assert(button3.getText().equals("Food and Drinks"));
        assert(button4.getText().equals("Gaming"));
        assert(button5.getText().equals("Education and Learning"));
        assert(button6.getText().equals("Outdoors and Adventure"));
        assert(button7.getText().equals("Other"));
        assert(button8.getText().equals("Show All"));

    }

    @org.junit.Test
    public void testGetSelectedOptions() {
        MakeFilterEventsView();
        JRadioButton button = getFilterButton(1);
        button.setSelected(true);
        ButtonGroup buttonGroup = button.getModel().getGroup();
        JRadioButton received = (JRadioButton) FilterEventsView.getSelectedOptions(buttonGroup);
        assert (button.equals(received));
    }

    @org.junit.Test
    public void testCheckVisibility() {
        FilterEventsView filterEventsView = MakeFilterEventsView();
        JFrame mainFrame = getFrame();
        assert (!mainFrame.isVisible());  // Filter view is not visible on starting
        filterEventsView.showMenu();
        assert (mainFrame.isVisible());  // Filter view is visible after method call
    }

}
