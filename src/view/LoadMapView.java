package view;

import interface_adapter.search.SearchController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeListener;
import java.util.Set;

public class LoadMapView extends JPanel implements ActionListener, PropertyChangeListener {

    private final JTextField searchInputField = new JTextField(15);

    private final JButton search;
    private final JButton filter;
    private final SearchController searchController;
    public LoadMapView(SearchController searchController) {

        this.searchController = searchController;
        LabelTextPanel searchInfo = new LabelTextPanel(new JLabel(LoadMapViewModel.SEARCH_LABEL), searchInputField);

        JPanel buttons = new JPanel();
        search = new JButton(LoadMapViewModel.SEARCH_BUTTON_LABEL);
        buttons.add(search);
        filter = new JButton(LoadMapViewModel.FILTER_BUTTON_LABEL);
        buttons.add(filter);

        search.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(search)) {
                            SearchState currentState = LoadMapViewModel.getState();

                            searchController.execute(
                                    currentState.getSearch(),
                                    currentState.getEvents()
                            );
                        }
                    }
                }
        );

        searchInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SearchState currentState = LoadMapViewModel.getState();
                        String text = searchInputField.getText() + e.getKeyChar();
                        currentState.setSearch(text);
                        LoadMapViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        filter.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(filter)) {
                            createFilterFrame();
//                            Set<String> usernames = clearController.execute();
//                            String message = String.join(",", usernames);
//                            JOptionPane.showMessageDialog(null, message);
                        }
                    }
                }
        );
    }

    private static void createFilterFrame(){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame filterFrame = new JFrame("Filter");
                JButton apply = new JButton("Apply");
                filterFrame.add(apply);
                JButton cancel = new JButton("Cancel");
                filterFrame.add(cancel);




                JRadioButton sportsButton = new JRadioButton("Sports");
                sportsButton.setActionCommand("Sports");
                filterFrame.add(sportsButton);

                JRadioButton musicButton = new JRadioButton("Music");
                musicButton.setActionCommand("Music");
                filterFrame.add(musicButton);

                JRadioButton foodButton = new JRadioButton("Food");
                musicButton.setActionCommand("Food");
                filterFrame.add(foodButton);

                JRadioButton otherButton = new JRadioButton("Other");
                musicButton.setActionCommand("Other");
                filterFrame.add(otherButton);

                JRadioButton gamingButton = new JRadioButton("Gaming");
                musicButton.setActionCommand("Gaming");
                filterFrame.add(gamingButton);

                JRadioButton moreButton = new JRadioButton("More");
                musicButton.setActionCommand("More");
                filterFrame.add(moreButton);

                ButtonGroup typeGroup = new ButtonGroup();
                typeGroup.add(sportsButton);
                typeGroup.add(musicButton);
                typeGroup.add(foodButton);
                typeGroup.add(otherButton);
                typeGroup.add(gamingButton);
                typeGroup.add(moreButton);

            }
        });

    }



}
