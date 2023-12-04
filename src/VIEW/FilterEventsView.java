package VIEW;

import INTERFACE_ADAPTER.filter.FilterController;
import VIEW_CREATOR.FilterEventsViewModel;
import VIEW_CREATOR.LoadMapViewModel;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;

@Getter
public class FilterEventsView extends JPanel {
    private final FilterController controller;
    private final Frame frame;
    private final LoadMapViewModel viewModel;
    public FilterEventsView(FilterController controller, LoadMapViewModel viewModel, FilterEventsViewModel filterEventsViewModel) {
        this.viewModel = viewModel;
        this.controller = controller;

        JFrame filterFrame = new JFrame(filterEventsViewModel.getFILTER_FRAME_TITLE());
        JPanel filterPanel = new JPanel();
        JPanel filterPanelType= new JPanel();
        JPanel filterPanelButtons = new JPanel();
        filterPanelType.setAlignmentX(Component.CENTER_ALIGNMENT);
        filterPanel.add(Box.createVerticalStrut(filterEventsViewModel.getVERTICAL_GAP()));
        filterFrame.setBounds(0, 0, filterEventsViewModel.getFRAME_WIDTH(), filterEventsViewModel.getFRAME_HEIGHT());
        filterFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        filterFrame.setResizable(false);

        // Buttons for Apply and Cancel
        JButton apply = new JButton(filterEventsViewModel.getAPPLY_BUTTON_TITLE());
        JButton cancel = new JButton(filterEventsViewModel.getCANCEL_BUTTON_TITLE());
        filterPanelButtons.add(cancel);
        filterPanelButtons.add(apply);

        JLabel typeLabel = new JLabel(filterEventsViewModel.getLABEL_TEXT());
        filterPanelType.add(typeLabel, BorderLayout.NORTH);
        filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.Y_AXIS));
        filterPanelType.setLayout(new BoxLayout(filterPanelType, BoxLayout.Y_AXIS));

        // Buttons for Type selection
        JRadioButton sportsButton = new JRadioButton(filterEventsViewModel.getTYPE1());
        JRadioButton musicButton = new JRadioButton(filterEventsViewModel.getTYPE2());
        JRadioButton foodButton = new JRadioButton(filterEventsViewModel.getTYPE3());
        JRadioButton gamingButton = new JRadioButton(filterEventsViewModel.getTYPE4());
        JRadioButton educationButton = new JRadioButton(filterEventsViewModel.getTYPE5());
        JRadioButton outdoorsButton = new JRadioButton(filterEventsViewModel.getTYPE6());
        JRadioButton otherButton = new JRadioButton(filterEventsViewModel.getTYPE7());
        JRadioButton allButton = new JRadioButton(filterEventsViewModel.getTYPE8());
        ButtonGroup typeGroup = new ButtonGroup();

        ArrayList<JRadioButton> typeList = new ArrayList<>();
        typeList.add(sportsButton);
        typeList.add(musicButton);
        typeList.add(foodButton);
        typeList.add(gamingButton);
        typeList.add(educationButton);
        typeList.add(outdoorsButton);
        typeList.add(otherButton);
        typeList.add(allButton);
        setButtons(typeList, filterPanelType, typeGroup);

        apply.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        AbstractButton selectedType = getSelectedOptions(typeGroup);
                        if(selectedType != null) {
                            filterFrame.dispose();
                            controller.execute(selectedType.getText(), viewModel);
                        } else { JOptionPane.showMessageDialog(frame, "No Filters Selected");}
                    }
                }
        );

        cancel.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {filterFrame.dispose();}
                }
        );
        filterPanel.add(filterPanelType);
        filterPanel.add(filterPanelButtons);
        filterFrame.add(filterPanel);
        filterFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        filterFrame.setLocationRelativeTo(null);
        frame = filterFrame;
        filterFrame.setVisible(false);
    }

    /**
     * This method displays the frame that contains the filter options.
     */
    public void showMenu(){
        frame.setVisible(true);
    }

    /**
     * This method returns the button that has been selected in the specified button group.
     * @param buttonGroup the ButtonGroup object we want to check
     * @return the button that has been selected
     */
    public static AbstractButton getSelectedOptions(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements();
             buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button;
            }
        }
        return null;
    }

    private static void setButtons(ArrayList<JRadioButton> buttons, JPanel panel, ButtonGroup group){
        buttons.forEach((button) -> {
            panel.add(button);
            group.add(button);
        });
    }
}
