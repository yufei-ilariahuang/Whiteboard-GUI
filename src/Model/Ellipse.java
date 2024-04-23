package Model;
import java.awt.*;

public class Ellipse implements Shape {
    private int x, y, width, height;
    private Color color;
    private boolean fill;

    public Ellipse(Color color, boolean fill) {
        this.color = color;
        this.fill = fill;
    }

    public Ellipse(int x, int y, int width, int height, Color color, boolean fill) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.fill = fill;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        if (fill) {
            g.fillOval(x, y, width, height);
        } else {
            g.drawOval(x, y, width, height);
        }
    }

    @Override
    public ShapeType getType() {
        return ShapeType.ELLIPSE;
    }
}
