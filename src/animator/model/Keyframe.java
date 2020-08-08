package cs3500.animator.model;

/**
 * A class representing a keyframe that stores values a particular point in time for a motion in an
 * animation. Keyframes keep track of position, size, and color at a certain tick.
 */
public class Keyframe {

  private final int tick;
  private int x;
  private int y;
  private int r;
  private int g;
  private int b;
  private int height;
  private int width;


  /**
   * Construct a keyframe at the given tick with the provided position, size, and color values.
   *
   * @param tick   the tick of the keyframe
   * @param x      the x value of the keyframe
   * @param y      the y value of the keyframe
   * @param r      the red value of the keyframe
   * @param g      the green value of the keyframe
   * @param b      the blue value of the keyframe
   * @param height the height value of the keyframe
   * @param width  the width value value of the keyframe
   */
  public Keyframe(int tick, int x, int y, int r, int g, int b, int height, int width) {
    this.tick = tick;
    this.x = x;
    this.y = y;
    this.r = r;
    this.g = g;
    this.b = b;
    this.height = height;
    this.width = width;
  }

  /**
   * Get the tick value of the keyframe.
   *
   * @return the tick value of the keyframe
   */
  public int getTick() {
    return tick;
  }

  /**
   * Get the x value of the keyframe.
   *
   * @return the x value of the keyframe
   */
  public int getX() {
    return x;
  }

  /**
   * Get the y value of the keyframe.
   *
   * @return the y value of the keyframe
   */
  public int getY() {
    return y;
  }

  /**
   * Get the red value of the keyframe.
   *
   * @return the red value of the keyframe
   */
  public int getR() {
    return r;
  }

  /**
   * Get the green value of the keyframe.
   *
   * @return the green value of the keyframe
   */
  public int getG() {
    return g;
  }

  /**
   * Get the blue value of the keyframe.
   *
   * @return the blue value of the keyframe
   */
  public int getB() {
    return b;
  }

  /**
   * Get the height value of the keyframe.
   *
   * @return the height value of the keyframe
   */
  public int getHeight() {
    return height;
  }

  /**
   * Get the width value of the keyframe.
   *
   * @return the width value of the keyframe
   */
  public int getWidth() {
    return width;
  }

  /**
   * Returns a string representation of a keyframe containting all of its field data. Used for
   * displaying a list of keyframes in the GUI.
   *
   * @return a string representation of a keyframe.
   */
  @Override
  public String toString() {
    StringBuilder result = new StringBuilder();
    result.append("Tick: ").append(this.tick)
        .append(" X: ").append(this.x)
        .append(" Y: ").append(this.y)
        .append(" Width: ").append(this.width)
        .append(" Height: ").append(this.height)
        .append(" Red: ").append(this.r)
        .append(" Green: ").append(this.g)
        .append(" Blue: ").append(this.b);
    return result.toString();
  }
}
