package View;

import Model.Shape;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DrawingPanel extends JPanel {
    private List<Model.Shape> shapes = new ArrayList<>();
    private Model.Shape currentShape;
    public DrawingPanel() {
        super();
        setPreferredSize(new Dimension(600, 600)); // Optionally set preferred size here
    }
    public void addShape(Shape shape) {
        shapes.add(shape);
        repaint();
    }

    public void setCurrentShape(Model.Shape shape) {
        currentShape = shape;
        repaint();
    }

    public void clearShapes() {
        shapes.clear();
        currentShape = null; // Reset current shape on clear
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Model.Shape shape : shapes) {
            shape.draw(g);
        }
        if (currentShape != null) {
            currentShape.draw(g);
        }
    }
}
