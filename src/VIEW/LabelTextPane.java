package VIEW;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class LabelTextPane extends HBox {
    public LabelTextPane(String labelText, javafx.scene.Node inputNode) {
        Label label = new Label(labelText);
        this.getChildren().addAll(label, inputNode);
        this.setSpacing(10);
    }
}