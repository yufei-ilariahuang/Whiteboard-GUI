package Model;

import java.awt.*;

public class Text implements Shape {
    private Point position;
    private String text;
    private Color color;

    public Text(Point position, String text, Color color) {
        this.position = position;
        this.text = text;
        this.color = color;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.drawString(text, position.x, position.y);
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public ShapeType getType() {
        return null;
    }

    @Override
    public Point getStart() {
        return null;
    }
}
