package Model;

import java.awt.*;

public class Line implements Shape {
    private Point start;
    private Point end;
    private Color color;
    public Line(){
        this.color = Color.BLACK;
    }
    public Line(int x1, int y1,Color color){
        this.start = new Point(x1, y1);
        this.end = new Point(x1, y1);
        this.color=color;
    }

    // Removed the redundant constructor
    public Line(int x1, int y1, int x2, int y2, Color color) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
        this.color = color;
    }

    @Override
    public void draw(Graphics g) {
        if (start != null && end != null) {
            g.setColor(color); // Set color before drawing
            g.drawLine(start.x, start.y, end.x, end.y);
        }
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

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
