package Model;

import java.awt.*;

public class Rectangle implements Shape {
    private Point start;
    private Point end;
    private Color color;
    public Rectangle(){
        this.color = Color.BLACK;
    }


    // Default constructor if needed for initializing without parameters
    public Rectangle(int x1, int y1, Color color) {
        this.start = new Point(x1, y1);
        this.end = new Point(x1, y1);
        this.color =color;
    }

    // Main constructor for initializing with specific values
    public Rectangle(int x1, int y1, int x2, int y2, Color color) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
        this.color = color;
    }

    // Draw the rectangle using the Graphics context
    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        if(end.x>start.x && end.y>start.y){
            g.drawRect(start.x, start.y, Math.abs(end.x- start.x), Math.abs(end.y- start.y));
        } else if(end.x<start.x && end.y>start.y) {
            g.drawRect(end.x, start.y, Math.abs(end.x- start.x), Math.abs(end.y- start.y));
        } else if(end.x<start.x && end.y<start.y){
            g.drawRect(end.x, end.y, Math.abs(end.x- start.x), Math.abs(end.y- start.y));
        } else if(end.x>start.x && end.y<start.y) {
            g.drawRect(start.x, end.y, Math.abs(end.x - start.x), Math.abs(end.y - start.y));
        }
    }

    // Get the type of the shape
    @Override
    public ShapeType getType() {
        return ShapeType.RECTANGLE;
    }

    // Get the starting point of the rectangle
    @Override
    public Point getStart() {
        return start;
    }

    // Set a new color for the rectangle
    @Override
    public void setColor(Color newColor) {
        this.color = newColor;
    }

    // Get the current color of the rectangle
    @Override
    public Color getColor() {
        return color;
    }

    public void setEnd(Point end) {
        this.end = end;
    }
}
