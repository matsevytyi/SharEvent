package VIEW;

import INTERFACE_ADAPTER.search.SearchController;
import VIEW_CREATOR.LoadMapViewModel;
import VIEW_CREATOR.SearchEventsViewModel;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchEventsView extends JPanel {
    @Getter
    private final SearchController controller;
    private final Frame frame;
    @Getter
    private final LoadMapViewModel viewModel;
    public SearchEventsView(SearchController controller, LoadMapViewModel viewModel, SearchEventsViewModel searchEventsViewModel) {
        this.viewModel = viewModel;
        this.controller = controller;
        JFrame searchFrame = new JFrame(searchEventsViewModel.getSEARCH_FRAME_TITLE());
        JLabel searchLabel = new JLabel(searchEventsViewModel.getSEARCH_LABEL());
        JPanel mainPanel =  new JPanel();
        JPanel searchPanelButtons = new JPanel();
        JTextField searchTextField = new JTextField(searchEventsViewModel.getSEARCH_FIELD_SIZE());
        mainPanel.add(searchLabel);
        mainPanel.add(searchTextField);


        // Buttons for Apply and Cancel
        JButton apply = new JButton(searchEventsViewModel.getAPPLY_BUTTON_LABEL());
        JButton cancel = new JButton(searchEventsViewModel.getCANCEL_BUTTON_LABEL());
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

        JTextField textField = new JTextField(searchEventsViewModel.getSEARCH_FIELD_SIZE());
        textField.setFont(textField.getFont().deriveFont(searchEventsViewModel.getSEARCH_FIELD_FONT()));

        setVisible(true);

        mainPanel.add(searchPanelButtons);

        searchFrame.add(mainPanel);
        searchFrame.setBounds(0, 0, searchEventsViewModel.getSEARCH_FRAME_WIDTH(), searchEventsViewModel.getSEARCH_FRAME_HEIGHT());

        searchFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        searchFrame.setLocationRelativeTo(null);
        searchFrame.setVisible(true);
        this.frame = searchFrame;

    }

}
