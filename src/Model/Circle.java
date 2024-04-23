package Model;

import java.awt.*;

public class Circle implements Shape {
    private int x, y, radius;
    private Color color;
    private boolean fill;

    public Circle(Color color, boolean fill) {
        this.color = color;
        this.fill = fill;
    }

    public Circle(int x, int y, int radius, Color color, boolean fill) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
        this.fill = fill;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        if (fill) {
            g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
        } else {
            g.drawOval(x - radius, y - radius, radius * 2, radius * 2);
        }
    }

    @Override
    public ShapeType getType() {
        return ShapeType.CIRCLE;
    }
}
