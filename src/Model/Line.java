package Model;
import java.awt.*;

public class Line implements Shape {
    private Point start;
    private Point end;
    private Color color;
    private boolean fill;

    public Line(Color color, boolean fill) {
        this.color = color;
        this.fill = fill;
    }

    public Line(int x1, int y1, int x2, int y2, Color color, boolean fill) {
        start = new Point(x1, y1);
        end = new Point(x2, y2);
        this.color = color;
        this.fill = fill;
    }

    public void setEnd(Point end) {
        this.end = end;
    }

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

    @Override
    public ShapeType getType() {
        return ShapeType.LINE;
    }
}
