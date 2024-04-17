package Model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FreeDraw implements Shape {
    private List<Point> points = new ArrayList<>();  // Store all points
    private Color color;

    public FreeDraw() {
        this.color = Color.BLACK;
    }

    public FreeDraw(Color color) {
        this.color = color;
    }

    public void addPoint(Point point) {
        points.add(point);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        for (int i = 1; i < points.size(); i++) {
            Point p1 = points.get(i - 1);
            Point p2 = points.get(i);
            g.drawLine(p1.x, p1.y, p2.x, p2.y);
        }
    }

    @Override
    public ShapeType getType() {
        return ShapeType.FreeDraw; // Ensure this is correctly implemented in your ShapeType enum
    }

    @Override
    public Point getStart() {
        return points.isEmpty() ? null : points.get(0);
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
    }
    public void clearPoints() {
        points.clear();  // Clear all points in the free draw
    }
}
