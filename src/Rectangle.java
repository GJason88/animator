import java.awt.Color;

/**
 * Representing a rectangle shape with a position, size, and color.
 */
public class Rectangle extends AbstractShape {


  /**
   * Constructs a Rectangle at the given x and y position, with the given color, name, height, and
   * width. Uses the {@link AbstractShape} constructor.
   *
   * @param x      the x position of the rectangle
   * @param y      the y position of the rectangle
   * @param color  the color of the rectangle
   * @param height the height of the rectangle
   * @param width  the width of the rectangle
   * @param name   the name of the rectangle
   * @throws NullPointerException     if the given String name or Color is null.
   * @throws IllegalArgumentException if the width or height are negative.
   */
  public Rectangle(int x, int y, Color color, int height, int width, String name) {
    super(x, y, color, height, width, name);
  }

  /**
   * Constructs a copy of the given rectangle using the {@link AbstractShape} constructor.
   *
   * @param r the rectangle to construct a copy of.
   */
  public Rectangle(Rectangle r) {
    super(r);
  }

}
