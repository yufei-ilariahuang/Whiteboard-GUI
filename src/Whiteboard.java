import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Whiteboard extends JFrame {
  private JPanel drawingPanel;
  private List<Shape> shapes = new ArrayList<>();
  private Shape currentShape;
  private boolean drawingMode = false;
  private static final int FIXED_SHAPE_SIZE = 100; // Define the fixed size of the shapes

  public Whiteboard() {
    setTitle("Whiteboard");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    drawingPanel = new JPanel() {
      @Override
      protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Shape shape : shapes) {
          shape.draw(g);
        }
        if (currentShape != null) {
          currentShape.draw(g);
        }
      }
    };
    drawingPanel.setBackground(Color.WHITE);
    drawingPanel.setPreferredSize(new Dimension(400, 400));
    drawingPanel.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        if (drawingMode && currentShape != null) {
          int x = e.getX();
          int y = e.getY();
          switch (currentShape.getType()) {
            case LINE:
              currentShape = new Line(x, y, x + FIXED_SHAPE_SIZE, y);
              break;
            case CIRCLE:
              currentShape = new Circle(x, y, FIXED_SHAPE_SIZE);
              break;
            case RECTANGLE:
              currentShape = new Rectangle(x, y, FIXED_SHAPE_SIZE, FIXED_SHAPE_SIZE);
              break;
          }
          shapes.add(currentShape);
          drawingPanel.repaint();
        }
      }
    });

    drawingPanel.addMouseMotionListener(new MouseAdapter() {
      @Override
      public void mouseDragged(MouseEvent e) {
        if (drawingMode && currentShape != null) {
          int x = e.getX();
          int y = e.getY();
          switch (currentShape.getType()) {
            case LINE:
              ((Line) currentShape).setEnd(new Point(x, y));
              break;
            case CIRCLE:
              ((Circle) currentShape).setDiameter(Math.max(Math.abs(currentShape.getStart().x - x), Math.abs(currentShape.getStart().y - y)));
              break;
            case RECTANGLE:
              ((Rectangle) currentShape).setWidth(Math.abs(currentShape.getStart().x - x));
              ((Rectangle) currentShape).setHeight(Math.abs(currentShape.getStart().y - y));
              break;
          }
          drawingPanel.repaint();
        }
      }
    });

    JButton lineButton = new JButton("Line");
    lineButton.addActionListener(e -> {
      drawingMode = true;
      currentShape = new Line();
    });

    JButton circleButton = new JButton("Circle");
    circleButton.addActionListener(e -> {
      drawingMode = true;
      currentShape = new Circle();
    });

    JButton rectangleButton = new JButton("Rectangle");
    rectangleButton.addActionListener(e -> {
      drawingMode = true;
      currentShape = new Rectangle();
    });

    JButton clearButton = new JButton("Clear");
    clearButton.addActionListener(e -> {
      shapes.clear();
      currentShape = null; // Reset currentShape
      drawingPanel.repaint();
    });

    JPanel buttonPanel = new JPanel();
    buttonPanel.add(lineButton);
    buttonPanel.add(circleButton);
    buttonPanel.add(rectangleButton);
    buttonPanel.add(clearButton);

    getContentPane().setLayout(new BorderLayout());
    getContentPane().add(drawingPanel, BorderLayout.CENTER);
    getContentPane().add(buttonPanel, BorderLayout.NORTH);

    pack();
    setLocationRelativeTo(null);
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new Whiteboard().setVisible(true));
  }

  interface Shape {
    void draw(Graphics g);

    ShapeType getType();

    Point getStart();
  }

  enum ShapeType {
    LINE, CIRCLE, RECTANGLE
  }

  private static class Line implements Shape {
    private Point start;
    private Point end;

    public Line() {
    }

    public Line(int x1, int y1, int x2, int y2) {
      start = new Point(x1, y1);
      end = new Point(x2, y2);
    }

    @Override
    public void draw(Graphics g) {
      if (start != null && end != null) {
        g.drawLine(start.x, start.y, end.x, end.y);
      }
    }

    @Override
    public ShapeType getType() {
      return ShapeType.LINE;
    }

    @Override
    public Point getStart() {
      return start;
    }

    public void setEnd(Point end) {
      this.end = end;
    }
  }

  private static class Circle implements Shape {
    private int x, y, diameter;

    public Circle() {
    }

    public Circle(int x, int y, int diameter) {
      this.x = x;
      this.y = y;
      this.diameter = diameter;
    }

    @Override
    public void draw(Graphics g) {
      g.drawOval(x, y, diameter, diameter);
    }

    @Override
    public ShapeType getType() {
      return ShapeType.CIRCLE;
    }

    @Override
    public Point getStart() {
      return new Point(x, y);
    }

    public void setDiameter(int diameter) {
      this.diameter = diameter;
    }
  }

  private static class Rectangle implements Shape {
    private int x, y, width, height;

    public Rectangle() {
    }

    public Rectangle(int x, int y, int width, int height) {
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
    }

    @Override
    public void draw(Graphics g) {
      g.drawRect(x, y, width, height);
    }

    @Override
    public ShapeType getType() {
      return ShapeType.RECTANGLE;
    }

    @Override
    public Point getStart() {
      return new Point(x, y);
    }

    public void setWidth(int width) {
      this.width = width;
    }

    public void setHeight(int height) {
      this.height = height;
    }
  }
}
