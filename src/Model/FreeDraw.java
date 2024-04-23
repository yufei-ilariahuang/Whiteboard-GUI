package Model;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FreeDraw implements Shape {
    private List<Point> points = new ArrayList<>();
    private Color color;

    public FreeDraw(Color color) {
        this.color = color;
    }

    public void addPoint(int x, int y) {
        points.add(new Point(x, y));
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        for (int i = 1; i < points.size(); i++) {
            Point prevPoint = points.get(i - 1);
            Point nextPoint = points.get(i);
            g.drawLine(prevPoint.x, prevPoint.y, nextPoint.x, nextPoint.y);
        }
    }

    @Override
    public ShapeType getType() {
        return ShapeType.LINE; // Return LINE for now, but it doesn't really matter for free draw
    }
}

