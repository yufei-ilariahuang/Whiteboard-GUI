import Model.Rectangle;
import Model.Shape;
import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
/**
 * The {@code Whiteboard} class represents a simple whiteboard drawing application using Java Swing.
 * Users can draw lines, circles, rectangles, ellipses, and freehand drawings with support for color and fill toggles.
 */
public class Whiteboard extends JFrame {
  private JPanel drawingPanel;//The main panel where all drawing actions are rendered
  private List<Shape> shapes = new ArrayList<>();  //A list of all shapes that have been drawn,allows for operations like undo and clear
  private Shape currentShape;//Default currentShape = null
  private boolean drawingMode = false;//Default drawingMode
  private boolean freeDrawMode = false;//Default freeDrawMode
  private boolean fillMode = false;// Default fillMode
  private Color currentColor = Color.BLACK; // Default color
  private Point startPoint; // For dragging to determine shape size
  /**
   * Constructs a new Whiteboard instance, setting up the UI components and event handlers.
   * It initializes a drawing panel for shapes, buttons for various drawing actions,
   * and manages both mouse and mouse motion events to handle drawing interactions.
   */
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

    /**
     * Responds to mouse press events on the drawing panel. Depending on the current mode,
     * it either initializes a new shape or starts a free draw path at the mouse location.
     * The starting point for the shape or path is set based on where the user clicked.
     *
     * @param e the MouseEvent containing details about the mouse press action
     */
    drawingPanel.addMouseListener(new MouseAdapter() {


      @Override
      public void mousePressed(MouseEvent e) {
        if (freeDrawMode) {
          currentShape = new FreeDraw(currentColor);// Start a free draw path
          shapes.add(currentShape);
          ((FreeDraw) currentShape).addPoint(e.getX(), e.getY());
        } else if (drawingMode && currentShape != null) {
          startPoint = e.getPoint(); // Store the starting point for dragging
          switch (currentShape.getType()) {// Initialize a specific shape based on the current tool selected
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
      /**
       * Handles the mouse release event on the drawing panel. This method finalizes the drawing
       * of a shape or prepares for a new segment in free draw mode. It updates the visual representation
       * based on the user's drawing interaction.
       *
       * @param e the MouseEvent containing details about the mouse release action
       */
      @Override
      public void mouseReleased(MouseEvent e) {
        if (freeDrawMode && currentShape != null) {
          currentShape = new FreeDraw(currentColor); // Create a new FreeDraw object for continuous drawing
          shapes.add(currentShape);
        }
      }
    });

    drawingPanel.addMouseMotionListener(new MouseAdapter() {
      /**
       * Manages the mouse drag event to update the size and orientation of the currently drawing shape
       * or extend the free draw path based on the mouse's new position. Provides real-time visual feedback
       * by redrawing the shape or path as the user moves the mouse.
       *
       * @param e the MouseEvent containing details about the mouse dragging action
       */
      @Override
      public void mouseDragged(MouseEvent e) {
        if (drawingMode && currentShape != null) {
          //Responds to mouse dragging events on the drawing panel by updating the properties of the current shape
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
          //being drawn or extends the path of a freehand drawing. This method handles real-time adjustments to the shape
          //based on the current mouse position, providing dynamic feedback as the user drags the mouse.
          ((FreeDraw) currentShape).addPoint(e.getX(), e.getY());
          //After adjusting the shape's properties or extending the path, it triggers a repaint of the drawing panel
          // to reflect the changes immediately.
          drawingPanel.repaint();
        }
      }
    });

    /**
     * Sets up the drawing environment for each shape. Activates line drawing mode, disables free draw mode,
     * and initializes a new shape object ready for drawing.
     *
     * @param e the ActionEvent triggered by clicking the corresponding buttons
     */

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
    /**
     * Toggles the fill setting for shapes. When activated, shapes drawn subsequently will be filled
     * with the currently selected color. Otherwise, they will be outlined.
     *
     * @param e the ItemEvent triggered by toggling the Fill button
     */
    JToggleButton fillToggleButton = new JToggleButton("Fill");
    fillToggleButton.addItemListener(e -> {
      fillMode = fillToggleButton.isSelected();
    });
    /**
     * Displays a color chooser dialog to allow the user to select a color. Updates the current drawing color
     * based on the user's selection. This affects the color of all subsequent shapes or freeform paths drawn.
     *
     * @param e the ActionEvent triggered by clicking the Color button
     */
    JButton colorButton = new JButton("Color");
    colorButton.addActionListener(e -> {
      Color selectedColor = JColorChooser.showDialog(null, "Select Color", currentColor);
      if (selectedColor != null) {
        currentColor = selectedColor;
      }
    });
    /**
     * Clears all shapes from the drawing area, effectively resetting the canvas. This action cannot be undone.
     *
     * @param e the ActionEvent triggered by clicking the Clear button
     */
    JButton clearButton = new JButton("Clear");
    clearButton.addActionListener(e -> {
      if (shapes.isEmpty()) {
        // Show error message if there are no shapes to clear
        JOptionPane.showMessageDialog(null, "The drawing area is already clear.", "Clear Error", JOptionPane.ERROR_MESSAGE);
      } else {
        shapes.clear();
        currentShape = null; // Reset currentShape
        drawingPanel.repaint();
      }
    });

    /**
     * Removes the most recently added shape from the drawing list, effectively undoing the last drawing action.
     * This action can be repeated until all shapes are removed.
     *
     * @param e the ActionEvent triggered by clicking the Undo button
     */
    JButton undoButton = new JButton("Undo");
    undoButton.addActionListener(e -> {
      if (!shapes.isEmpty()) {
//        for (Shape shape : shapes) {
//          System.out.println("1"+ shape);
//        }
        shapes.removeLast();
//        for (Shape shape : shapes) {
//          System.out.println("2"+ shape);
//        }
        currentShape = null;
        drawingPanel.repaint();
      } else {
        JOptionPane.showMessageDialog(null, "The drawing area is already clear.", "Clear Error", JOptionPane.ERROR_MESSAGE);

      }
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
    buttonPanel.add(undoButton);
/**
 * Configures the layout of the main content pane of the JFrame. Sets up the BorderLayout as the
 * container's layout manager, which allows for flexible arrangement of components in five regions:
 * north, south, east, west, and center.
 *
 * This method positions the drawing panel in the center region, providing the maximum space for drawing
 * activities. The button panel is placed in the north region, ensuring that the buttons are easily accessible
 * and do not interfere with the drawing area. This layout configuration is crucial for maintaining a
 * functional and user-friendly interface in the drawing application.
 */
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


