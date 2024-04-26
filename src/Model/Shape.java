package Model;

import java.awt.*;
/**
 * Defines the functionality for shapes that can be drawn on the screen.
 * This interface requires implementing classes to provide drawing capabilities
 * and a method to identify the type of the shape.
 */
public interface Shape {
  /**
   * Draws the shape using the provided graphics context.
   * Implementations should specify exactly how the shape is rendered on the screen.
   *
   * @param g The graphics context used for drawing the shape.
   */
  void draw(Graphics g);
  /**
   * Returns the type of the shape, as defined in {@link ShapeType}.
   * This method helps in identifying the type of shape at runtime.
   *
   * @return The specific {@link ShapeType} of the shape.
   */
  ShapeType getType();
}
