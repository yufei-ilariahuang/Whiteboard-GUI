package Model;

import java.awt.*;

public interface Shape {
    void draw(Graphics g);

    ShapeType getType();
}