import Model.Rectangle;
import Model.Shape;
import Model.*;

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
  private boolean freeDrawMode = false;
  private boolean fillMode = false;
  private Color currentColor = Color.BLACK; // Default color
  private Point startPoint; // For dragging to determine shape size

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
        if (currentShape != null && !freeDrawMode) {
          currentShape.draw(g);
        }
      }
    };
    drawingPanel.setBackground(Color.WHITE);
    drawingPanel.setPreferredSize(new Dimension(400, 400));
    drawingPanel.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        if (freeDrawMode) {
          currentShape = new FreeDraw(currentColor);
          shapes.add(currentShape);
          ((FreeDraw) currentShape).addPoint(e.getX(), e.getY());
        } else if (drawingMode && currentShape != null) {
          startPoint = e.getPoint(); // Store the starting point for dragging
          switch (currentShape.getType()) {
            case LINE:
              currentShape = new Line(startPoint.x, startPoint.y, startPoint.x + 100, startPoint.y, currentColor, fillMode);
              break;
            case CIRCLE:
              currentShape = new Circle(startPoint.x, startPoint.y, 100, currentColor, fillMode);
              break;
            case RECTANGLE:
              currentShape = new Rectangle(startPoint.x, startPoint.y, 100, 100, currentColor, fillMode);
              break;
            case ELLIPSE:
              // Create a fixed-size ellipse at the click point
              currentShape = new Ellipse(startPoint.x - 75, startPoint.y - 50, 150, 100, currentColor, fillMode);
              break;
          }
          shapes.add(currentShape);
        }
        drawingPanel.repaint();
      }

      @Override
      public void mouseReleased(MouseEvent e) {
        if (freeDrawMode && currentShape != null) {
          currentShape = new FreeDraw(currentColor); // Create a new FreeDraw object for continuous drawing
          shapes.add(currentShape);
        }
      }
    });

    drawingPanel.addMouseMotionListener(new MouseAdapter() {
      @Override
      public void mouseDragged(MouseEvent e) {
        if (drawingMode && currentShape != null) {
          if (currentShape instanceof Line) {
            ((Line) currentShape).setEnd(e.getPoint());
          } else if (currentShape instanceof Circle) {
            int radius = (int) Math.sqrt(Math.pow(e.getX() - startPoint.x, 2) + Math.pow(e.getY() - startPoint.y, 2));
            ((Circle) currentShape).setRadius(radius);
          } else if (currentShape instanceof Rectangle) {
            int width = Math.abs(e.getX() - startPoint.x);
            int height = Math.abs(e.getY() - startPoint.y);
            ((Rectangle) currentShape).setWidth(width);
            ((Rectangle) currentShape).setHeight(height);
            ((Rectangle) currentShape).setLocation(Math.min(startPoint.x, e.getX()), Math.min(startPoint.y, e.getY()));
          } else if (currentShape instanceof Ellipse) {
            int width = Math.abs(e.getX() - startPoint.x);
            int height = Math.abs(e.getY() - startPoint.y);
            ((Ellipse) currentShape).setWidth(width);
            ((Ellipse) currentShape).setHeight(height);
            ((Ellipse) currentShape).setLocation(Math.min(startPoint.x, e.getX()), Math.min(startPoint.y, e.getY()));
          }
          drawingPanel.repaint();
        } else if (freeDrawMode && currentShape != null) {
          ((FreeDraw) currentShape).addPoint(e.getX(), e.getY());
          drawingPanel.repaint();
        }
      }
    });

    JButton lineButton = new JButton("Line");
    lineButton.addActionListener(e -> {
      drawingMode = true;
      freeDrawMode = false;
      currentShape = new Line(currentColor, fillMode);
    });

    JButton circleButton = new JButton("Circle");
    circleButton.addActionListener(e -> {
      drawingMode = true;
      freeDrawMode = false;
      currentShape = new Circle(currentColor, fillMode);
    });

    JButton rectangleButton = new JButton("Rectangle");
    rectangleButton.addActionListener(e -> {
      drawingMode = true;
      freeDrawMode = false;
      currentShape = new Rectangle(currentColor, fillMode);
    });

    JButton ellipseButton = new JButton("Ellipse");
    ellipseButton.addActionListener(e -> {
      drawingMode = true;
      freeDrawMode = false;
      currentShape = new Ellipse(currentColor, fillMode);
    });

    JButton freeDrawButton = new JButton("Free Draw");
    freeDrawButton.addActionListener(e -> {
      drawingMode = false;
      freeDrawMode = true;
    });

    JToggleButton fillToggleButton = new JToggleButton("Fill");
    fillToggleButton.addItemListener(e -> {
      fillMode = fillToggleButton.isSelected();
    });

    JButton colorButton = new JButton("Color");
    colorButton.addActionListener(e -> {
      Color selectedColor = JColorChooser.showDialog(null, "Select Color", currentColor);
      if (selectedColor != null) {
        currentColor = selectedColor;
      }
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
    buttonPanel.add(ellipseButton);
    buttonPanel.add(freeDrawButton);
    buttonPanel.add(fillToggleButton);
    buttonPanel.add(colorButton);
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
}

