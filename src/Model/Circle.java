package Model;

import java.awt.*;
/**
 * Represents a Circle shape in a graphic application.
 * This class implements the {@link Shape} interface to provide methods for drawing and managing a Circle.
 */
public class Circle implements Shape {
  private int x, y, radius;
  private Color color;
  private boolean fill;

  public Circle(Color color, boolean fill) {
    this.color = color;
    this.fill = fill;
  }
  /**
   * Constructs a new circle with specified center, radius, color, and fill.
   *
   * @param x      The x-coordinate of the center of the circle.
   * @param y      The y-coordinate of the center of the circle.
   * @param radius The radius of the circle.
   * @param color  The color of the circle.
   * @param fill   A boolean value that indicates whether the circle should be drawn filled.
   */
  public Circle(int x, int y, int radius, Color color, boolean fill) {
    this.x = x;
    this.y = y;
    this.radius = radius;
    this.color = color;
    this.fill = fill;
  }
  /**
   * Sets the radius of the circle.
   *
   * @param radius The new radius of the circle.
   */
  public void setRadius(int radius) {
    this.radius = radius;
  }
  /**
   * Draws this circle using the provided graphics context.
   * The circle can be either filled or outlined based on its fill attribute.
   *
   * @param g The {@link Graphics} context used for drawing the circle.
   */
  @Override
  public void draw(Graphics g) {
    g.setColor(color);
    if (fill) {
      g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
    } else {
      g.drawOval(x - radius, y - radius, radius * 2, radius * 2);
    }
  }

  @Override
  public ShapeType getType() {
    return ShapeType.CIRCLE;
  }
}
