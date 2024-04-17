package View;

import Model.Shape;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DrawingPanel extends JPanel {
    private List<Shape> shapes = new ArrayList<>();
    private Shape currentShape;

    public DrawingPanel() {
        super();
        setPreferredSize(new Dimension(600, 600)); // Optionally set preferred size here
    }

    public void add(Shape shape) {
        shapes.add(shape);
        repaint();
    }

    public void setCurrentShape(Shape shape) {
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
        for (Shape shape : shapes) {
            shape.draw(g);
        }
        if (currentShape != null) {
            currentShape.draw(g);
        }
    }
    public void removeLastShape() {
        if (!shapes.isEmpty()) {
            shapes.remove(shapes.size() - 1);
            repaint();
        }
    }

}
