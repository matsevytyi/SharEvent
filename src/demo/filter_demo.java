package demo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;

public class filter_demo extends JPanel{

    public static void main(String[] args) {
        // making all the different panels and the frame
        JFrame filterFrame = new JFrame("Filter");
        JPanel filterPanel = new JPanel();
        JPanel filterPanelType= new JPanel();
        JPanel filterPanelTime = new JPanel();
        JPanel filterPanelButtons = new JPanel();
        filterPanelTime.setAlignmentX(Component.CENTER_ALIGNMENT);
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
        JLabel timeLabel = new JLabel("Filter by Time: ");
        filterPanelType.add(typeLabel, BorderLayout.NORTH);
        filterPanelTime.add(timeLabel, BorderLayout.NORTH);
        filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.Y_AXIS));
        filterPanelType.setLayout(new BoxLayout(filterPanelType, BoxLayout.Y_AXIS));
        filterPanelTime.setLayout(new BoxLayout(filterPanelTime, BoxLayout.Y_AXIS));

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

        // Buttons for time selection
        JRadioButton todayButton = new JRadioButton("Today");
        JRadioButton tomorrowButton = new JRadioButton("Tomorrow");
        JRadioButton thisWeekButton = new JRadioButton("This Week");
        JRadioButton thisMonthButton = new JRadioButton("This Month");
        ButtonGroup timeGroup = new ButtonGroup();

        ArrayList<JRadioButton> timeList = new ArrayList<>();
        timeList.add(todayButton);
        timeList.add(tomorrowButton);
        timeList.add(thisWeekButton);
        timeList.add(thisMonthButton);
        setButtons(timeList, filterPanelTime, timeGroup);



        apply.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String selectedType = getSelectedOptions(typeGroup);
                        String selectedTime = getSelectedOptions(timeGroup);
                        System.out.println(selectedType);
                        System.out.println(selectedTime);
                    }
                }
        );

        cancel.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        filterFrame.dispose();
                    }
                }
        );

        filterPanel.add(filterPanelType);
        filterPanel.add(filterPanelTime);
        filterPanel.add(filterPanelButtons);
        filterFrame.add(filterPanel);
        filterFrame.setVisible(true);

    }

    private static String getSelectedOptions(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements();
             buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
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
