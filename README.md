# Whiteboard Application 

## Overview
The Whiteboard application is a Java-based interactive drawing software. It empowers users to draw and manipulate geometric shapes as well as engage in free-form drawing. 

## System Requirements
- Java Runtime Environment (JRE) 8 or higher
- Standard Java AWT and Swing libraries

## Features
- Support for drawing basic shapes: Line, Circle, Rectangle, Ellipse
- Free-form drawing capabilities
- Color customization for individual shapes
- Option to toggle shapes between filled and unfilled
- Facility to clear the drawing area with a single action

## Usage
by Clone using the web URL.
by run .jar file
Users can select the drawing mode via the UI buttons, adjust colors with a color chooser, and toggle the fill mode for shapes. The application translates user interactions into graphical representations.

## Design Evaluation

### Good Utilization of Inheritance
Shapes share common functionalities via the `Shape` interface, demonstrating an effective use of inheritance for polymorphic capabilities.

### Good Utilization of Abstraction
The `Shape` interface abstracts the drawing methods, enforcing a consistent drawing operation across various shape types.

### Implementation of MVC
The application's architecture suggests a blend of control and view components. However, a clear MVC pattern separation is not evident and could be an area for future enhancement.

#### DrawingPanel
A JPanel where shapes are drawn. It overrides the paintComponent method to render shapes held in a list.

#### Shape and ShapeType
The Shape interface defines a contract for drawable objects, requiring a draw method and a method to retrieve the type of shape.

ShapeType is an enumeration defining types of shapes supported by the application, including LINE, CIRCLE, RECTANGLE, and ELLIPSE.

#### Specific Shapes
Specific shapes like Circle, Rectangle, Line, Ellipse and FreeDraw implement the Shape interface, providing the draw method for rendering themselves on the DrawingPanel.

##### Features
Circle draws circles based on a center point and radius.
Ellipse draws circles based on a center point and radius.
Rectangle draws rectangles based on starting point, width, and height.
Line draws straight lines between two points.
FreeDraw allows for free-form drawing, capturing a sequence of points.

#### User Interface
The application includes a toolbar with buttons for each shape, a free draw button, a color picker, and a fill toggle. The color picker uses JColorChooser to allow users to select custom colors.

Users can create shapes by selecting the corresponding shape button and clicking on the drawing panel. The starting click defines the initial point of the shape, and dragging defines its size. Releasing the mouse finalizes the shape. In free draw mode, dragging the mouse draws a continuous line.

### Validations / Exception Handling
Current implementation provides minimal exception handling. Robust validation and error management are recommended for stability.

## Future Enhancements
- Implementation of an MVC pattern for clear separation of concerns
- Advanced error handling for user inputs and system exceptions
- Features for undo/redo actions
- Support for additional shapes and drawing tools
- Improved free drawing with variable brush sizes and styles

## Getting Started
Compile the source files and run the `Whiteboard` main class to start the application. Detailed steps for setting up the environment and running the software are provided.

