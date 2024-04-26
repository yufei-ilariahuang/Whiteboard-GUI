package Model;
import java.awt.*;
/**
 * Represents a Ellipse shape in a graphic application.
 * This class implements the {@link Shape} interface to provide methods for drawing and managing a Ellipse.
 */
public class Ellipse implements Shape {
  private int x, y, width, height;
  private Color color;
  private boolean fill;

  public Ellipse(Color color, boolean fill) {
    this.color = color;
    this.fill = fill;
  }
  /**
   * Constructs a new ellipse with specified bounds, color, and fill.
   *
   * @param x      The x-coordinate of the upper-left corner bounding the ellipse.
   * @param y      The y-coordinate of the upper-left corner bounding the ellipse.
   * @param width  The width of the ellipse.
   * @param height The height of the ellipse.
   * @param color  The color of the ellipse.
   * @param fill   A boolean value that indicates whether the ellipse should be drawn filled.
   */
  public Ellipse(int x, int y, int width, int height, Color color, boolean fill) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.color = color;
    this.fill = fill;
  }
  /**
   * Sets the width of the ellipse.
   *
   * @param width The new width of the ellipse.
   */
  public void setWidth(int width) {
    this.width = width;
  }
  /**
   * Sets the height of the ellipse.
   *
   * @param height The new height of the ellipse.
   */
  public void setHeight(int height) {
    this.height = height;
  }
  /**
   * Sets the location of the upper-left corner bounding the ellipse.
   *
   * @param x The x-coordinate of the new location.
   * @param y The y-coordinate of the new location.
   */
  public void setLocation(int x, int y) {
    this.x = x;
    this.y = y;
  }
  /**
   * Draws this ellipse using the provided graphics context.
   * The ellipse can be either filled or outlined based on its fill attribute.
   *
   * @param g The {@link Graphics} context used for drawing the ellipse.
   */
  @Override
  public void draw(Graphics g) {
    g.setColor(color);
    if (fill) {
      g.fillOval(x, y, width, height);
    } else {
      g.drawOval(x, y, width, height);
    }
  }

  @Override
  public ShapeType getType() {
    return ShapeType.ELLIPSE;
  }
}
