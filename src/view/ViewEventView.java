package view;

import interface_adapter.view_event.ViewEventViewModel;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


public class ViewEventView extends VBox {
    private final Label eventNameLabel = new Label();
    private final Label typeLabel = new Label();
    private final Label eventDateLabel = new Label();
    private final Label eventTimeLabel = new Label();
    private final Label descriptionLabel = new Label();
    private final Label creatorLabel = new Label();
    private final Label attendantsLabel = new Label();

    private final ViewEventViewModel viewEventViewModel;

    public ViewEventView(ViewEventViewModel viewEventViewModel) {
        this.viewEventViewModel = viewEventViewModel;
        initUI();
    }

    private void initUI() {
        getChildren().addAll(
                new Label("Event Name: "), eventNameLabel,
                new Label("Type: "), typeLabel,
                new Label("Event Date: "), eventDateLabel,
                new Label("Event Time: "), eventTimeLabel,
                new Label("Description: "), descriptionLabel,
                new Label("Creator: "), creatorLabel,
                new Label("Attendants: "), attendantsLabel
        );
        setSpacing(5);
        setPadding(new Insets(10));
    }

    public void updateView() {
        // Assuming ViewEventViewModel has appropriate getters
        eventNameLabel.setText(viewEventViewModel.getState().getEventName());
        typeLabel.setText(viewEventViewModel.getState().getType());
        eventDateLabel.setText(String.valueOf(viewEventViewModel.getState().getDate()));
        eventTimeLabel.setText(String.valueOf(viewEventViewModel.getState().getTime()));
        descriptionLabel.setText(viewEventViewModel.getState().getDescription());
        creatorLabel.setText(viewEventViewModel.getState().getCreatedBy());
        attendantsLabel.setText(viewEventViewModel.getState().getRegisteredUsers());
    }
}
