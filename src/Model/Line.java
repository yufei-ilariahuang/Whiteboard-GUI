package Model;
import java.awt.*;
import java.awt.*;
/**
 * Represents a line shape in a graphic application.
 * This class implements the {@link Shape} interface to provide methods for drawing and managing a line.
 */
public class Line implements Shape {
  private Point start;// Starting point of the line
  private Point end;// Ending point of the line
  private Color color; // Color of the line
  private boolean fill;// Flag to determine whether the line is filled
  /**
   * Constructs a new line with specified color and fill properties.
   *
   * @param color The color of the line.
   * @param fill  A boolean value that indicates whether the line should be drawn filled.
   */
  public Line(Color color, boolean fill) {
    this.color = color;
    this.fill = fill;
  }
  /**
   * Constructs a new line from (x1, y1) to (x2, y2) with specified color and fill.
   *
   * @param x1    The x-coordinate of the starting point.
   * @param y1    The y-coordinate of the starting point.
   * @param x2    The x-coordinate of the ending point.
   * @param y2    The y-coordinate of the ending point.
   * @param color The color of the line.
   * @param fill  A boolean value that indicates whether the line should be drawn filled.
   */
  public Line(int x1, int y1, int x2, int y2, Color color, boolean fill) {
    start = new Point(x1, y1);
    end = new Point(x2, y2);
    this.color = color;
    this.fill = fill;
  }
  /**
   * Sets the ending point of the line.
   *
   * @param end The new ending point for the line.
   */
  public void setEnd(Point end) {
    this.end = end;
  }
  /**
   * Draws this line using the provided graphics context.
   * The line can be either filled or outlined based on its fill attribute.
   *
   * @param g The {@link Graphics} context used for drawing the line.
   */
  @Override
  public void draw(Graphics g) {
    if (start != null && end != null) {
      g.setColor(color);
      if (fill) {
        g.drawLine(start.x, start.y, end.x, end.y);
      } else {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setStroke(new BasicStroke(2)); // Set thicker stroke for unfilled shape
        g2d.drawLine(start.x, start.y, end.x, end.y);
        g2d.dispose();
      }
    }
  }
  /**
   * Returns the type of the shape, which is LINE in this case.
   *
   * @return The type of the shape as {@link ShapeType}.
   */
  @Override
  public ShapeType getType() {
    return ShapeType.LINE;
  }
}
