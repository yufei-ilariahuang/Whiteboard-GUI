package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class ButtonPanel extends JPanel {
    public ButtonPanel(ActionListener shapeListener, ActionListener freeDrawListener,
                       ActionListener textListener, ActionListener colorChooserListener,
                       ActionListener clearListener) {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        addButton("Line", shapeListener);
        addButton("Circle", shapeListener);
        addButton("Rectangle", shapeListener);
        addButton("Free Draw", freeDrawListener);
        addButton("Text", textListener);
        addButton("Color", colorChooserListener);
        addButton("Clear", clearListener);
    }

    private void addButton(String label, ActionListener listener) {
        JButton button = new JButton(label);
        button.addActionListener(listener);
        add(button);
    }
}