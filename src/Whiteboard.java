import Model.Circle;
import Model.Line;
import Model.Shape;
import View.ButtonPanel;
import View.DrawingPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Whiteboard extends JFrame {
  private DrawingPanel drawingPanel;
  private List<Model.Shape> shapes = new ArrayList<>();
  private Color currentColor = Color.BLACK; // Default color
  private Shape currentShape;
  private boolean drawingMode = false;
  private boolean textMode = false;
  private String textContent = "";

  private static final int FIXED_SHAPE_SIZE = 100; // Define the fixed size of the shapes

  public Whiteboard() {
    setTitle("Whiteboard");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setupUI();
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
  }

  private void setupUI() {
    drawingPanel = new DrawingPanel();
    drawingPanel.setBackground(Color.WHITE);
    drawingPanel.setPreferredSize(new Dimension(600, 600));
    ButtonPanel buttonPanel = new ButtonPanel(
            e -> switchToShape(e),
            e -> switchToFreeDraw(),
            e -> switchToText(),
            e -> chooseColor(),
            e -> clearDrawing()
    );
    getContentPane().setLayout(new BorderLayout());
    getContentPane().add(drawingPanel, BorderLayout.CENTER);
    getContentPane().add(buttonPanel, BorderLayout.NORTH);

    setupDrawingPanelInteractions();
  }
  private void setupDrawingPanelInteractions() {
    drawingPanel.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        if (drawingMode) {
          int x = e.getX();
          int y = e.getY();
          // Initiate the shape based on the current selected type
          switch (currentShape.getType()) {
            case LINE:
              currentShape = new Line(x, y, x, y, currentColor); // Start and end points the same initially
              break;
            case CIRCLE:
              currentShape = new Circle(x, y, 0, currentColor); // Start with 0 radius
              break;
            case RECTANGLE:
              currentShape = new Rectangle(x, y, 0, 0, currentColor); // Start with 0 width and height
              break;
          }
          shapes.add(currentShape); // Add shape to the list
          drawingMode = false; // Optional: turn off drawing mode after a shape is started (toggle this based on your app's behavior)
        }
      }
    });

    drawingPanel.addMouseMotionListener(new MouseAdapter() {
      @Override
      public void mouseDragged(MouseEvent e) {
        if (currentShape != null) {
          int x = e.getX();
          int y = e.getY();
          // Update the shape's dimensions based on mouse position
          if (currentShape instanceof Line) {
            ((Line) currentShape).setEndPoint(x, y);
          } else if (currentShape instanceof Circle) {
            int radius = (int) Math.sqrt(Math.pow(x - currentShape.getX(), 2) + Math.pow(y - currentShape.getY(), 2));
            ((Circle) currentShape).setRadius(radius);
          } else if (currentShape instanceof Rectangle) {
            int width = Math.abs(x - currentShape.getX());
            int height = Math.abs(y - currentShape.getY());
            ((Rectangle) currentShape).setWidth(width);
            ((Rectangle) currentShape).setHeight(height);
          }
          drawingPanel.repaint();
        }
      }
    });
  }

  private void switchToShape(ActionEvent e) {
    String command = ((JButton) e.getSource()).getText();
    drawingMode = true; // Enable drawing mode
    // Determine which shape to initialize based on the button text
    if ("Line".equals(command)) {
      currentShape = new Line(0, 0, 0, 0, currentColor); // Default initialization
    } else if ("Circle".equals(command)) {
      currentShape = new Circle(0, 0, 0, currentColor); // Default initialization
    } else if ("Rectangle".equals(command)) {
      currentShape = new Rectangle(0, 0, 0, 0, currentColor); // Default initialization
    }
  }

  private void switchToFreeDraw() {
    drawingMode = true;
    currentShape = new FreeDraw(currentColor); // Assume FreeDraw is a class that handles free drawing
    // Add points to the FreeDraw shape during mouse drag events
  }

  private void switchToText() {
    textMode = true; // Enable text mode
    String inputText = JOptionPane.showInputDialog(this, "Enter text:");
    if (inputText != null && !inputText.isEmpty()) {
      textContent = inputText;
      currentShape = new Text(0, 0, inputText, currentColor); // Default position, update later as needed
      shapes.add(currentShape);
    }
  }

  private void chooseColor() {
    Color newColor = JColorChooser.showDialog(this, "Choose a color", currentColor);
    if (newColor != null) {
      currentColor = newColor;
      if (currentShape != null) {
        currentShape.setColor(newColor);
      }
    }
  }

  private void clearDrawing() {
    shapes.clear();
    drawingPanel.repaint();
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new Whiteboard().setVisible(true));
  }

}