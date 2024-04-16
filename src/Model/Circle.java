package Model;

import java.awt.*;

public class Circle implements Shape {
    private int x, y, diameter;
    private Color color;

    public Circle() {
    }

    public Circle(int x, int y, int diameter, Color color) {
        this.x = x;
        this.y = y;
        this.diameter = diameter;
        this.color=color;
    }

    @Override
    public void draw(Graphics g) {
        g.drawOval(x, y, diameter, diameter);
    }

    @Override
    public ShapeType getType() {
        return ShapeType.CIRCLE;
    }

    @Override
    public Point getStart() {
        return new Point(x, y);
    }
    @Override
    public Color getColor() {
        return color;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }
}