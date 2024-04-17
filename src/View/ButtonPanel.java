package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class ButtonPanel extends JPanel {
    public ButtonPanel(ActionListener shapeListener, ActionListener freeDrawListener,
                       ActionListener colorChooserListener, ActionListener clearListener, ActionListener undoListener) {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        addButton("Line", shapeListener);
        addButton("Circle", shapeListener);
        addButton("Rectangle", shapeListener);
        addButton("Free Draw", freeDrawListener);
        addButton("Color", colorChooserListener);
        addButton("Clear", clearListener);
        addButton("Undo",undoListener);
    }

    private void addButton(String label, ActionListener listener) {
        JButton button = new JButton(label);
        button.addActionListener(listener);
        add(button);
    }
}