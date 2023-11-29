package VIEW;

import INTERFACE_ADAPTER.filter.FilterController;
import INTERFACE_ADAPTER.filter.FilterPresenter;
import USE_CASE.filter.FilterOutputBoundary;
import VIEW_CREATOR.LoadMapViewModel;
import lombok.Getter;
import org.jxmapviewer.viewer.GeoPosition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;

public class FilterEventsView extends JPanel {
    @Getter
    private FilterController controller;
    @Getter
    private Frame frame;
    public FilterEventsView(FilterController controller, LoadMapViewModel viewModel) {
        JFrame filterFrame = new JFrame("Filter");
        JPanel filterPanel = new JPanel();
        JPanel filterPanelType= new JPanel();
        JPanel filterPanelButtons = new JPanel();
        filterPanelType.setAlignmentX(Component.CENTER_ALIGNMENT);
        filterPanel.add(Box.createVerticalStrut(25));
        filterFrame.setTitle("Filters");
        filterFrame.setBounds(650, 200, 300, 400);
        filterFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        filterFrame.setResizable(false);

        // Buttons for Apply and Cancel
        JButton apply = new JButton("Apply");
        JButton cancel = new JButton("Cancel");
        filterPanelButtons.add(cancel);
        filterPanelButtons.add(apply);

        JLabel typeLabel = new JLabel("Filter by Type: ");
        filterPanelType.add(typeLabel, BorderLayout.NORTH);
        filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.Y_AXIS));
        filterPanelType.setLayout(new BoxLayout(filterPanelType, BoxLayout.Y_AXIS));

        // Buttons for Type selection
        JRadioButton sportsButton = new JRadioButton("Sports");
        JRadioButton musicButton = new JRadioButton("Music");
        JRadioButton foodButton = new JRadioButton("Food");
        JRadioButton otherButton = new JRadioButton("Other");
        JRadioButton gamingButton = new JRadioButton("Gaming");
        JRadioButton moreButton = new JRadioButton("More");
        ButtonGroup typeGroup = new ButtonGroup();

        ArrayList<JRadioButton> typeList = new ArrayList<>();
        typeList.add(sportsButton);
        typeList.add(musicButton);
        typeList.add(foodButton);
        typeList.add(otherButton);
        typeList.add(gamingButton);
        typeList.add(moreButton);
        setButtons(typeList, filterPanelType, typeGroup);

        apply.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        AbstractButton selectedType = getSelectedOptions(typeGroup);
                        if(selectedType != null) {
                            System.out.println(selectedType.getText());
                            filterFrame.dispose();
                            controller.execute(selectedType.getText(), viewModel);
                        } else {
                            JOptionPane.showMessageDialog(frame, "No Filters Selected");
                        }
                    }
                }
        );

        cancel.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        AbstractButton selectedType = getSelectedOptions(typeGroup);
                        if (selectedType != null){
                            selectedType.setSelected(false);
                        }
                        filterFrame.dispose();
                    }
                }
        );

        filterPanel.add(filterPanelType);
        filterPanel.add(filterPanelButtons);
        filterFrame.add(filterPanel);
        this.frame = filterFrame;
        filterFrame.setVisible(false);

    }

    public void showMenu(){
        this.frame.setVisible(true);
    }

    public void hideMenu(){
        this.frame.setVisible(false);
    }

    private static AbstractButton getSelectedOptions(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements();
             buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button;
            }
        }
        return null;
    }

    static void setButtons(ArrayList<JRadioButton> buttons, JPanel panel, ButtonGroup group){
        buttons.forEach((button) -> {
            panel.add(button);
            group.add(button);
        });
    }
}
