import Model.Rectangle;
import Model.Shape;
import Model.*;
import View.ButtonPanel;
import View.DrawingPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Whiteboard extends JFrame {
  private DrawingPanel drawingPanel;
  private Color currentColor = Color.BLACK; // Default color
  private Shape currentShape;
  private boolean drawingMode = false;

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
              e -> switchToShape(e.getActionCommand()),
              e -> switchToFreeDraw(),
              e -> chooseColor(),
              e -> clearDrawing(),
              e -> undoLastAction()
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
        int x = e.getX();
        int y = e.getY();
        if(currentShape instanceof FreeDraw){
          if (!drawingMode) {
            drawingMode = true;
            currentShape = new FreeDraw(currentColor);
            drawingPanel.add(currentShape);
          }
          ((FreeDraw) currentShape).addPoint(e.getPoint());
          currentShape.setColor(currentColor);
          drawingPanel.add(currentShape);
          drawingPanel.repaint();
        }else{
          if (!drawingMode) {
            drawingMode = true;

            initiateShape(x, y); // Start a new shape at the click coordinates
          }else {
            drawingMode = false;
            updateShape(x, y);// Disable drawing mode, shape is finalized on second click
            drawingPanel.repaint();
          }
        }
      }
      @Override
      public void mouseDragged(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if (drawingMode && currentShape instanceof FreeDraw) {
          ((FreeDraw) currentShape).addPoint(e.getPoint());
          drawingPanel.repaint(); // Repaint to update the drawing
        }else if (drawingMode && currentShape != null) {
          updateShape(x,y);
          drawingPanel.repaint();
        }
      }
    });

  }
  private void initiateShape(int x, int y) {
    if (currentShape != null) {

      switch (currentShape.getType()) {
        case LINE:
          currentShape = new Line(x, y,currentColor);
          break;
        case CIRCLE:
          currentShape = new Circle(x, y,currentColor);
          break;
        case RECTANGLE:
          currentShape = new Rectangle(x, y,currentColor);
          break;
      }
      drawingPanel.add(currentShape); // Add the newly started shape to the list
    }
  }

  private void updateShape(int x, int y) {
    // Calculate differences once, use many times.
    Point end = new Point(x, y);

    if (currentShape instanceof Line) {
      ((Line) currentShape).setEnd(end);
    } else if (currentShape instanceof Circle) {
      // The distance formula calculates the radius directly.
      ((Circle) currentShape).setEnd(end);
    } else if (currentShape instanceof Rectangle) {
      ((Rectangle) currentShape).setEnd(end);
    }
  }
  private void switchToShape(String command) {
    drawingMode = true;
    switch (command) {
      case "Line":
        currentShape = new Line();
        break;
      case "Circle":
        currentShape = new Circle();
        break;
      case "Rectangle":
        currentShape = new Rectangle();
        break;
    }
  }


  private void switchToFreeDraw() {
    drawingMode = true;
    currentShape = new FreeDraw();

  }


  private void chooseColor() {
    Color newColor = JColorChooser.showDialog(this, "Choose a color", currentColor);
    if (newColor != null) {
      currentColor = newColor;
    }
  }
  private void clearDrawing() {
    // Clear the list of shapes
    drawingPanel.clearShapes();

    // Repaint the drawing panel to reflect the cleared drawing
    drawingPanel.repaint();
  }
  private void undoLastAction() {
    drawingPanel.removeLastShape();  // Call the method to remove the last shape
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new Whiteboard().setVisible(true));
  }

}
