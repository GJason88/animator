import java.awt.Color;

/**
 * An interface for shapes that can be in an animation.
 */
public interface Shape {


  /**
   * Moves a shape to a given position.
   *
   * @param toX the X position to move to (can be negative).
   * @param toY the Y position to move to (can be negative).
   */
  void move(int toX, int toY);

  /**
   * Resize a shape to the given width and height.
   *
   * @param width  the new width
   * @param height the new height
   * @throws IllegalArgumentException if the given length and width are negative
   */
  void resize(int width, int height);

  /**
   * Change the color of a shape.
   *
   * @param r the red value to set to (0-255)
   * @param g the green value to set to (0-255)
   * @param b the blue value to set to (0-255)
   * @throws IllegalArgumentException if any of the given RGB values are not between 0 and 255
   */
  void changeColor(int r, int g, int b);

  /**
   * Gets the name of a shape.
   *
   * @return the string name of this shape
   */
  String getName();

  /**
   * Gets the X position of a shape.
   *
   * @return the x position of this shape
   */
  int getX();

  /**
   * Gets the Y position of a shape.
   *
   * @return the y position of this shape
   */
  int getY();

  /**
   * Gets the color of a shape.
   *
   * @return the color of this shape
   */
  Color getColor();

  /**
   * Gets the width of a shape.
   *
   * @return the width of this shape
   */
  int getWidth();

  /**
   * Gets the length of a shape.
   *
   * @return the length of this shape
   */
  int getHeight();


  /**
   * Checks if two shapes are in the same state in terms of color, position, size, and class. Does
   * not compare names
   *
   * @return true if the two shapes are in the same state, false otherwise.
   * @throws NullPointerException if the given shape is null.
   */
  boolean isSameState(Shape other);

}
