package Model;

import java.awt.*;

public class Rectangle implements Shape {
    private int x, y, width, height;
    private Color color;

    public Rectangle() {
    }

    public Rectangle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(Graphics g) {
        g.drawRect(x, y, width, height);
    }

    @Override
    public ShapeType getType() {
        return ShapeType.RECTANGLE;
    }

    @Override
    public Point getStart() {
        return new Point(x, y);
    }
    @Override
    public Color getColor() {
        return color;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}