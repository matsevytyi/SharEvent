
package VIEW;

import javax.swing.*;
import java.awt.*;

public class LabelTextPanel extends JPanel {
    private final JLabel label;
    private final JTextField textField;

    public LabelTextPanel(JLabel label, JTextField textField) {
        this.label = label;
        this.textField = textField;

        // Default styling
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setFont(new Font("Arial", Font.PLAIN, 14));

        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5)); // Adjust spacing as needed
        add(label);
        add(textField);
    }

    // Additional constructor for custom styling
    public LabelTextPanel(JLabel label, JTextField textField, Font labelFont, Font textFieldFont) {
        this.label = label;
        this.textField = textField;

        // Custom styling
        label.setFont(labelFont);
        textField.setFont(textFieldFont);

        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5)); // Adjust spacing as needed
        add(label);
        add(textField);
    }
}


