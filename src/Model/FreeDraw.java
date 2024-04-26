package Model;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Represents a freehand drawing in a graphic application.
 * This class implements the {@link Shape} interface to provide methods for drawing
 * a series of connected lines as determined by user input.
 */
public class FreeDraw implements Shape {
  private List<Point> points = new ArrayList<>();
  private Color color;
  /**
   * Constructs a new FreeDraw object with the specified color.
   *
   * @param color The color of the free draw lines.
   */
  public FreeDraw(Color color) {
    this.color = color;
  }
  /**
   * Adds a new point to the sequence of points that define the freehand drawing.
   *
   * @param x The x-coordinate of the point to add.
   * @param y The y-coordinate of the point to add.
   */
  public void addPoint(int x, int y) {
    points.add(new Point(x, y));
  }
  /**
   * Draws the freehand lines using the provided graphics context.
   * Connects consecutive points with lines.
   *
   * @param g The {@link Graphics} context used for drawing the lines.
   */
  @Override
  public void draw(Graphics g) {
    g.setColor(color);
    for (int i = 1; i < points.size(); i++) {
      Point prevPoint = points.get(i - 1);
      Point nextPoint = points.get(i);
      g.drawLine(prevPoint.x, prevPoint.y, nextPoint.x, nextPoint.y);
    }
  }
  /**
   * Returns the type of the shape. For FreeDraw, it returns LINE
   * @return The type of the shape as {@link ShapeType}.
   */
  @Override
  public ShapeType getType() {
    return ShapeType.LINE;
  }
}

