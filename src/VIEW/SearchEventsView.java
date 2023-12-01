package VIEW;

import INTERFACE_ADAPTER.search.SearchController;
import VIEW_CREATOR.LoadMapViewModel;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


@Getter
public class SearchEventsView extends JPanel {
    private final SearchController controller;
    private final Frame frame;
    public SearchEventsView(SearchController controller, LoadMapViewModel viewModel) {
        this.controller = controller;
        JFrame searchFrame = new JFrame("Search");
        JLabel searchLabel = new JLabel("Search Events by name: ");
        JPanel mainPanel =  new JPanel();
        JPanel searchPanelButtons = new JPanel();
        JTextField searchTextField = new JTextField(15);
        mainPanel.add(searchLabel);
        mainPanel.add(searchTextField);


        // Buttons for Apply and Cancel
        JButton apply = new JButton("Apply");
        JButton cancel = new JButton("Cancel");
        searchPanelButtons.add(cancel);
        searchPanelButtons.add(apply);

        apply.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String searchInput = searchTextField.getText();
                        if (searchInput.isEmpty()) {
                            JOptionPane.showMessageDialog(frame, "No Search To Apply");
                        } else {
                            searchFrame.dispose();
                            controller.execute(searchInput);
                        }
                    }
                }
        );

        cancel.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        searchFrame.dispose();
                        controller.executeShowAll();
                    }
                }
        );

        JTextField textField = new JTextField(8);
        textField.setFont(textField.getFont().deriveFont(50f));

        setVisible(true);

        mainPanel.add(searchPanelButtons);

        searchFrame.add(mainPanel);
        searchFrame.setBounds(0, 0, 250, 130);

        searchFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        searchFrame.setLocationRelativeTo(null);
        searchFrame.setVisible(true);
        this.frame = searchFrame;


    }

}
