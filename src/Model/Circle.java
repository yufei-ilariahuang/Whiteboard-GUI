package Model;

import java.awt.*;

public class Circle implements Shape {
    private Point start;
    private Point end;
    private Color color;
    public Circle(){
        this.color = Color.BLACK;
    }

    public Circle(int x1, int y1,Color color) {
        this.start = new Point(x1, y1);
        this.end = new Point(x1, y1);
        this.color = color;
    }

    public Circle(int x1, int y1, int x2, int y2, Color color) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
        this.color = color;
    }

    // Draw the circle on the given graphics context
    @Override
    public void draw(Graphics g) {
        g.setColor(color);

        // Calculate the radius and center of the circle
        int radius = (int) (Math.sqrt(Math.pow(end.x - start.x, 2) + Math.pow(end.y - start.y, 2)) / 2);
        int centerX = (start.x + end.x) / 2;
        int centerY = (start.y + end.y) / 2;

        // The bounding rectangle of the circle based on the center and radius
        int upperLeftX = centerX - radius;
        int upperLeftY = centerY - radius;
        int diameter = 2 * radius;

        g.drawOval(upperLeftX, upperLeftY, diameter, diameter);
    }



    // Get the type of shape
    @Override
    public ShapeType getType() {
        return ShapeType.CIRCLE;
    }

    // Get the start point of the circle, defined as the center point
    @Override
    public Point getStart() {
        return start;
    }

    // Get the color of the circle
    @Override
    public Color getColor() {
        return color;
    }

    // Set the diameter of the circle
    public void setEnd(Point end) {
        this.end = end;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }
}