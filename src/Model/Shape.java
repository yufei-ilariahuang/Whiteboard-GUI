package Model;

import java.awt.*;

public interface Shape {
    void draw(Graphics g);
    Color getColor();

    ShapeType getType();

    Point getStart();

    void setColor(Color newColor);
}