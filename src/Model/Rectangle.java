package Model;
import java.awt.*;
/**
 * Represents a Rectangle shape in a graphic application.
 * This class implements the {@link Shape} interface to provide methods for drawing and managing a Rectangle.
 */
public class Rectangle implements Shape {
  private int x, y, width, height;// Coordinates and dimensions of the rectangle
  private Color color;// Color of the rectangle
  private boolean fill;// Flag to determine whether the rectangle is filled
  /**
   * Constructs a new rectangle with specified color and fill properties.
   *
   * @param color The color of the rectangle.
   * @param fill  A boolean value that indicates whether the rectangle should be drawn filled.
   */
  public Rectangle(Color color, boolean fill) {
    this.color = color;
    this.fill = fill;
  }
  /**
   * Constructs a new rectangle with specified dimensions, color, and fill.
   *
   * @param x      The x-coordinate of the upper-left corner of the rectangle.
   * @param y      The y-coordinate of the upper-left corner of the rectangle.
   * @param width  The width of the rectangle.
   * @param height The height of the rectangle.
   * @param color  The color of the rectangle.
   * @param fill   A boolean value that indicates whether the rectangle should be drawn filled.
   */
  public Rectangle(int x, int y, int width, int height, Color color, boolean fill) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.color = color;
    this.fill = fill;
  }
  /**
   * Sets the width of the rectangle.
   *
   * @param width The new width of the rectangle.
   */
  public void setWidth(int width) {
    this.width = width;
  }
  /**
   * Sets the height of the rectangle.
   *
   * @param height The new height of the rectangle.
   */
  public void setHeight(int height) {
    this.height = height;
  }
  /**
   * Sets the location of the rectangle.
   *
   * @param x The x-coordinate of the upper-left corner of the rectangle.
   * @param y The y-coordinate of the upper-left corner of the rectangle.
   */
  public void setLocation(int x, int y) {
    this.x = x;
    this.y = y;
  }
  /**
   * Draws this rectangle using the provided graphics context.
   * The rectangle can be either filled or outlined based on its fill attribute.
   *
   * @param g The {@link Graphics} context used for drawing the rectangle.
   */
  @Override
  public void draw(Graphics g) {
    g.setColor(color);
    if (fill) {
      g.fillRect(x, y, width, height);
    } else {
      g.drawRect(x, y, width, height);
    }
  }
  /**
   * Returns the type of the shape, which is RECTANGLE in this case.
   *
   * @return The type of the shape as {@link ShapeType}.
   */
  @Override
  public ShapeType getType() {
    return ShapeType.RECTANGLE;
  }
}
