package Model;

import java.awt.*;

public class Line implements Shape {
    private Point start;
    private Point end;
    private Color color;

    public Line(int x, int y, int i, int y1, Color currentColor) {
    }

    public Line(int x1, int y1, int x2, int y2, Color currentColor) {
        start = new Point(x1, y1);
        end = new Point(x2, y2);

    }

    @Override
    public void draw(Graphics g) {
        if (start != null && end != null) {
            g.drawLine(start.x, start.y, end.x, end.y);
        }
    }
    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public ShapeType getType() {
        return ShapeType.LINE;
    }

    @Override
    public Point getStart() {
        return start;
    }

    public void setEnd(Point end) {
        this.end = end;
    }
}