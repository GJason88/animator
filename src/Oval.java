import java.awt.Color;

/**
 * Representing an oval with an x,y position, color, and length and width.
 */
public class Oval extends AbstractShape {

  /**
   * Constructs an Oval at the given x and y position, with the given color, name, height, and
   * width. Uses the {@link AbstractShape} constructor.
   *
   * @param x      the x position of the oval
   * @param y      the y position of the oval
   * @param color  the color of the oval
   * @param height the height of the oval
   * @param width  the width of the oval
   * @param name   the name of the oval
   * @throws NullPointerException     if the given String name or Color is null.
   * @throws IllegalArgumentException if the width or height are negative.
   */
  public Oval(int x, int y, Color color, int height, int width, String name) {
    super(x, y, color, height, width, name);
  }

  /**
   * Constructs a copy of the given oval using the {@link AbstractShape} constructor.
   *
   * @param o the oval to construct a copy of.
   */
  public Oval(Oval o) {
    super(o);
  }

}
