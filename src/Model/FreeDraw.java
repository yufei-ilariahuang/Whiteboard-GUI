package Model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FreeDraw implements Shape {
    private List<Point> points = new ArrayList<>();
    private Color color;
    public FreeDraw(){
        this.color=Color.BLACK;
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

    @Override
    public void setColor(Color newColor) {
        this.color = color;
    }
}
