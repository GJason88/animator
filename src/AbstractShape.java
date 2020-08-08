import java.awt.Color;
import java.util.Objects;

/**
 * Abstract class representing the fields and methods of a shape that can be animated.
 */
public abstract class AbstractShape implements Shape {

  private int x;
  private int y;
  private Color color;
  private int height;
  private int width;

  private final String name;

  /**
   * An abstract constructor for a shape that sets the shapes fields to the given values. A shape is
   * allowed to have a negative X and Y position. Shapes are considered to be the same as each other
   * if they have the same name.
   * Invariant - width and height can never be negative. This is
   * enforced by constructors, and within the resize method.
   *
   * @param x      the x position of the shape
   * @param y      the y position of the shape
   * @param color  the color of the shape
   * @param height the height of the shape
   * @param width  the width of the shape
   * @param name   the name of the shape
   * @throws NullPointerException     if the given String name or Color is null.
   * @throws IllegalArgumentException if the width or height are negative.
   */
  public AbstractShape(int x, int y, Color color, int height, int width, String name) {
    Objects.requireNonNull(name);
    Objects.requireNonNull(color);
    if (height < 0 || width < 0) {
      throw new IllegalArgumentException("Width and height must be positive");
    }
    this.x = x;
    this.y = y;
    this.color = color;
    this.height = height;
    this.width = width;
    this.name = name;
  }

  /**
   * An abstract constructor used to create a copy shape of the given shape.
   *
   * @param shape the shape to copy
   * @throws NullPointerException if the given shape is null.
   */
  public AbstractShape(AbstractShape shape) {
    Objects.requireNonNull(shape);
    this.x = shape.x;
    this.y = shape.y;
    this.color = shape.color;
    this.height = shape.height;
    this.width = shape.width;
    this.name = shape.name;
  }


  @Override
  public void move(int toX, int toY) {
    this.x = toX;
    this.y = toY;
  }

  @Override
  public void resize(int width, int height) {
    if (height < 0 || width < 0) {
      throw new IllegalArgumentException("Length and width must both be positive");
    }
    this.width = width;
    this.height = height;
  }

  @Override
  public void changeColor(int r, int g, int b) {
    if (r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255) {
      throw new IllegalArgumentException("RGB values must be between 0 and 255");
    }

    this.color = new Color(r, g, b);
  }

  /**
   * Creates a string representation of a shape.
   *
   * @return a string containing the x and y coordinates of this shape, the width and height of this
   *         shape, and the RGB value of this shape
   */
  @Override
  public String toString() {
    StringBuilder result = new StringBuilder();

    result
        .append(this.x)
        .append(" ")
        .append(this.y)
        .append(" ")
        .append(this.width)
        .append(" ")
        .append(this.height)
        .append(" ")
        .append(this.color.getRed())
        .append(" ")
        .append(this.color.getGreen())
        .append(" ")
        .append(this.color.getBlue());

    return result.toString();
  }

  // Getter methods


  @Override
  public String getName() {
    return this.name;
  }


  @Override
  public int getX() {
    return this.x;
  }

  @Override
  public int getY() {
    return this.y;
  }

  @Override
  public Color getColor() {
    return this.color;
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public boolean isSameState(Shape other) {
    Objects.requireNonNull(other);
    return this.color.equals(other.getColor())
        && this.x == other.getX()
        && this.y == other.getY()
        && this.width == other.getWidth()
        && this.height == other.getHeight()
        && this.getClass().equals(other.getClass());
  }

}
