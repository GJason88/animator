package cs3500.animator.model;

/**
 * Class for representing a single motion that occurs on a shape. A motion has starting and ending
 * x, y, rgb, width, and height values. A motion also contains information on its starting and
 * ending tick.
 */
public class Motion {

  private final int startingTick;
  private final int startX;
  private final int startY;
  private final int startR;
  private final int startG;
  private final int startB;
  private final int startHeight;
  private final int startWidth;

  private final int endingTick;
  private final int endX;
  private final int endY;
  private final int endR;
  private final int endG;
  private final int endB;
  private final int endHeight;
  private final int endWidth;

  /**
   * Constructs a Motions with the given starting and ending parameters.
   *
   * @param startX       the starting x value of the motion
   * @param startY       the starting y value of the motion
   * @param startR       the starting red value of the motion
   * @param startG       the starting green value of the motion
   * @param startB       the starting blue value of the motion
   * @param startHeight  the starting height value of the motion
   * @param startWidth   the starting width value of the motion
   * @param endX         the ending x value of the motion
   * @param endY         the ending y value of the motion
   * @param endR         the ending red value of the motion
   * @param endG         the ending green value of the motion
   * @param endB         the ending blue value of the motion
   * @param endHeight    the ending height value of the motion
   * @param endWidth     the ending width value of the motion
   * @param startingTick the staring tick value of the motion
   * @param endingTick   the ending tick value of the motion
   * @throws IllegalArgumentException if the ending tick comes before the starting tick, if any of
   *                                  the tick values are negative, if the red green, or blue values
   *                                  are not between 0 and 255, if the width or height values are
   *                                  negative.
   *                                  Invariant - starting tick is always greater than or equal to
   *                                  ending tick - rgb values are always between 0 and 255 - ticks
   *                                  are always non-negative - width and height values are always
   *                                  non-negative
   *                                  Invariants are enforced within constructors. Ticks only can
   *                                  increase, and changing size/color does not allow for illegal
   *                                  values
   */
  public Motion(int startingTick,
      int startX, int startY, int startR, int startG, int startB, int startHeight, int startWidth,
      int endingTick,
      int endX, int endY, int endR, int endG, int endB, int endHeight, int endWidth) {

    if (endingTick < startingTick) {
      throw new IllegalArgumentException("Starting tick must occur first.");
    }

    if (startingTick < 0) {
      throw new IllegalArgumentException("Ticks cannot be negative");
    }

    if (startR < 0 || startR > 255 || endR < 0 || endR > 255
        || startG < 0 || startG > 255 || endG < 0 || endG > 255
        || startB < 0 || startB > 255 || endB < 0 || endB > 255) {
      throw new IllegalArgumentException("RGB values must be between 0 and 255");
    }

    if (startWidth < 0 || endWidth < 0 || startHeight < 0 || endHeight < 0) {
      throw new IllegalArgumentException("Widths or heights cannot be negative");
    }

    this.startX = startX;
    this.startY = startY;
    this.startR = startR;
    this.startG = startG;
    this.startB = startB;
    this.startHeight = startHeight;
    this.startWidth = startWidth;
    this.endX = endX;
    this.endY = endY;
    this.endR = endR;
    this.endG = endG;
    this.endB = endB;
    this.endHeight = endHeight;
    this.endWidth = endWidth;
    this.startingTick = startingTick;
    this.endingTick = endingTick;
  }


  /**
   * Creates a text description of a motion. Includes the information on the starting and ending
   * x,y,width,height,rgb values, and ticks.
   *
   * @return a string documenting the changes that occurs during a motion.
   */
  public String generateDescription() {
    StringBuilder result = new StringBuilder();
    result.append(this.startingTick)
        .append(" ")
        .append(this.startX)
        .append(" ")
        .append(this.startY)
        .append(" ")
        .append(this.startWidth)
        .append(" ")
        .append(this.startHeight)
        .append(" ")
        .append(this.startR)
        .append(" ")
        .append(this.startG)
        .append(" ")
        .append(this.startB)
        .append(" ")
        .append(this.endingTick)
        .append(" ")
        .append(this.endX)
        .append(" ")
        .append(this.endY)
        .append(" ")
        .append(this.endWidth)
        .append(" ")
        .append(this.endHeight)
        .append(" ")
        .append(this.endR)
        .append(" ")
        .append(this.endG)
        .append(" ")
        .append(this.endB);

    return result.toString();
  }


  /**
   * Checks if two motions occur at the same time and also try to modify the same fields.
   *
   * @param m the motion to compare to.
   * @return true if the given motion overlaps with this motion, false otherwise.
   * @throws IllegalArgumentException if the provided motion is null
   */
  public boolean overlaps(Motion m) {

    if (m == null) {
      throw new IllegalArgumentException("Provided motion cannot be null");
    }

    // Check if two motions overlap time-wise
    if ((m.getStartingTick() >= this.startingTick && m.getStartingTick() < this.endingTick)
        || (m.getEndingTick() > this.startingTick && m.getEndingTick() <= this.endingTick)) {

      return ((this.startR != this.endR) && (m.startR != m.endR)
          || (this.startG != this.endG) && (m.startG != m.endG)
          || (this.startB != this.endB) && (m.startB != m.endB)
          || (this.startX != this.endX && m.startX != m.endX)
          || (this.startY != this.endY && m.startY != m.endY)
          || (this.startWidth != this.endWidth && m.startWidth != m.endWidth)
          || (this.startHeight != this.endHeight && m.startHeight != m.endHeight));
    }
    return false;
  }


  /**
   * Gets the starting tick for this motion.
   *
   * @return the starting tick of this motion.
   */
  public int getStartingTick() {
    return this.startingTick;
  }

  /**
   * Gets the ending tick for this motion.
   *
   * @return the ending tick of this motion
   */
  public int getEndingTick() {
    return this.endingTick;
  }


  /**
   * Checks if the starting state of this motion is the same as the starting state of the given
   * motion.
   *
   * @param other the motion to check consistency with
   * @return true if the given motion starts in the same state that this motion left off, false if
   *         it does not
   * @throws NullPointerException if the given motion is null.
   */
  public boolean isConsistent(Motion other) {
    if (other == null) {
      throw new IllegalArgumentException("Provided motion cannot be null");
    }
    return this.endX == other.startX
        && this.endY == other.startY
        && this.endR == other.startR
        && this.endG == other.startG
        && this.endB == other.startB
        && this.endWidth == other.startWidth
        && this.endHeight == other.startHeight;
  }

  /**
   * Gets the starting x value for a motion.
   *
   * @return the starting x value for this motion
   */
  public int getStartX() {
    return this.startX;
  }

  /**
   * Gets the starting y value for a motion.
   *
   * @return the starting y value for this motion
   */
  public int getStartY() {
    return this.startY;
  }

  /**
   * Gets the starting red value for a motion.
   *
   * @return the starting red value for this motion
   */
  public int getStartR() {
    return this.startR;
  }

  /**
   * Gets the starting green value for a motion.
   *
   * @return the starting green value for this motion
   */
  public int getStartG() {
    return this.startG;
  }

  /**
   * Gets the starting blue value for a motion.
   *
   * @return the starting blue value for this motion
   */
  public int getStartB() {
    return this.startB;
  }

  /**
   * Gets the starting height value for a motion.
   *
   * @return the starting height value for this motion
   */
  public int getStartHeight() {
    return this.startHeight;
  }

  /**
   * Gets the starting width value for a motion.
   *
   * @return the starting width value for this motion
   */
  public int getStartWidth() {
    return this.startWidth;
  }

  /**
   * Gets the ending x value for a motion.
   *
   * @return the ending x value for this motion
   */
  public int getEndX() {
    return this.endX;
  }

  /**
   * Gets the ending y value for a motion.
   *
   * @return the ending y value for this motion
   */
  public int getEndY() {
    return this.endY;
  }

  /**
   * Gets the ending red value for a motion.
   *
   * @return the ending red value for this motion
   */
  public int getEndR() {
    return this.endR;
  }

  /**
   * Gets the ending green value for a motion.
   *
   * @return the ending green value for this motion
   */
  public int getEndG() {
    return this.endG;
  }

  /**
   * Gets the ending blue value for a motion.
   *
   * @return the ending blue value for this motion
   */
  public int getEndB() {
    return this.endB;
  }

  /**
   * Gets the ending height value for a motion.
   *
   * @return the ending height value for this motion
   */
  public int getEndHeight() {
    return this.endHeight;
  }

  /**
   * Gets the ending width value for a motion.
   *
   * @return the ending width value for this motion
   */
  public int getEndWidth() {
    return this.endWidth;
  }
}
