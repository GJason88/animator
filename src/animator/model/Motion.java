package cs3500.animator.model;

/**
 * Class for representing a single motion that occurs on a shape. A motion has starting and ending
 * x, y, rgb, width, and height values. A motion also contains information on its starting and
 * ending tick.
 */
public class Motion implements IMotion {

  private final Keyframe startingKeyframe;
  private final Keyframe endingKeyframe;

  /**
   * Constructs a Motions with the given starting and ending parameters.
   *
   * @param startingKeyframe the keyframe the motion starts on
   * @param endingKeyframe   the keyframe the motion ends on
   * @throws IllegalArgumentException if the ending tick comes before the starting tick, if any of
   *                                  the tick values are negative, if the red green, or blue values
   *                                  are not between 0 and 255, if the width or height values are
   *                                  negative. Invariant - starting tick is always greater than or
   *                                  equal to ending tick - rgb values are always between 0 and 255
   *                                  - ticks are always non-negative - width and height values are
   *                                  always non-negative Invariants are enforced within
   *                                  constructors. Ticks only increase when the timer is running.
   *                                  If ticks decrease, they are set to 0. Changing size/color does
   *                                  not allow for illegal values.
   */
  public Motion(Keyframe startingKeyframe, Keyframe endingKeyframe) {

    this.startingKeyframe = startingKeyframe;
    this.endingKeyframe = endingKeyframe;

    if (endingKeyframe.getTick() < startingKeyframe.getTick()) {
      throw new IllegalArgumentException("Starting tick must occur first.");
    }

    if (startingKeyframe.getTick() < 0) {
      throw new IllegalArgumentException("Ticks cannot be negative");
    }

    if (startingKeyframe.getR() < 0 || startingKeyframe.getR() > 255 || endingKeyframe.getR() < 0
        || endingKeyframe.getR() > 255
        || startingKeyframe.getG() < 0 || startingKeyframe.getG() > 255 || endingKeyframe.getG() < 0
        || endingKeyframe.getG() > 255
        || startingKeyframe.getB() < 0 || startingKeyframe.getB() > 255 || endingKeyframe.getB() < 0
        || endingKeyframe.getB() > 255) {
      throw new IllegalArgumentException("RGB values must be between 0 and 255");
    }

    if (this.startingKeyframe.getWidth() < 0 || this.endingKeyframe.getWidth() < 0
        || this.startingKeyframe.getHeight() < 0 || this.endingKeyframe.getHeight() < 0) {
      throw new IllegalArgumentException("Widths or heights cannot be negative");
    }

  }


  /**
   * Creates a text description of a motion. Includes the information on the starting and ending
   * x,y,width,height,rgb values, and ticks.
   *
   * @return a string documenting the changes that occurs during a motion.
   */
  @Override
  public String generateDescription() {
    StringBuilder result = new StringBuilder();
    result.append(this.startingKeyframe.getTick())
        .append(" ")
        .append(this.startingKeyframe.getX())
        .append(" ")
        .append(this.startingKeyframe.getY())
        .append(" ")
        .append(this.startingKeyframe.getWidth())
        .append(" ")
        .append(this.startingKeyframe.getHeight())
        .append(" ")
        .append(this.startingKeyframe.getR())
        .append(" ")
        .append(this.startingKeyframe.getG())
        .append(" ")
        .append(this.startingKeyframe.getB())
        .append(" ")
        .append(this.endingKeyframe.getTick())
        .append(" ")
        .append(this.endingKeyframe.getX())
        .append(" ")
        .append(this.endingKeyframe.getY())
        .append(" ")
        .append(this.endingKeyframe.getWidth())
        .append(" ")
        .append(this.endingKeyframe.getHeight())
        .append(" ")
        .append(this.endingKeyframe.getR())
        .append(" ")
        .append(this.endingKeyframe.getG())
        .append(" ")
        .append(this.endingKeyframe.getB());

    return result.toString();
  }


  /**
   * Checks if two motions occur at the same time and also try to modify the same fields.
   *
   * @param m the motion to compare to.
   * @return true if the given motion overlaps with this motion, false otherwise.
   * @throws IllegalArgumentException if the provided motion is null
   */
  @Override
  public boolean overlaps(IMotion m) {

    if (m == null) {
      throw new IllegalArgumentException("Provided motion cannot be null");
    }

    // Check if two motions overlap time-wise
    if ((m.getStartingKeyframe().getTick() >= this.startingKeyframe.getTick()
        && m.getStartingKeyframe().getTick() < this.endingKeyframe.getTick())
        || (m.getEndingKeyframe().getTick() > this.startingKeyframe.getTick()
        && m.getEndingKeyframe().getTick() <= this.endingKeyframe.getTick())) {

      return ((this.startingKeyframe.getR() != this.endingKeyframe.getR()) && (
          m.getStartingKeyframe().getR() != m.getEndingKeyframe().getR())
          || (this.startingKeyframe.getG() != this.endingKeyframe.getG()) && (
          m.getStartingKeyframe().getG() != m.getEndingKeyframe().getG())
          || (this.startingKeyframe.getB() != this.endingKeyframe.getB()) && (
          m.getStartingKeyframe().getB() != m.getEndingKeyframe().getB())
          || (this.startingKeyframe.getX() != this.endingKeyframe.getX()
          && m.getStartingKeyframe().getX() != m.getEndingKeyframe().getX())
          || (this.startingKeyframe.getY() != this.endingKeyframe.getY()
          && m.getStartingKeyframe().getY() != m.getEndingKeyframe().getY())
          || (this.startingKeyframe.getWidth() != this.endingKeyframe.getWidth()
          && m.getStartingKeyframe().getWidth() != m.getEndingKeyframe().getWidth())
          || (this.startingKeyframe.getHeight() != this.endingKeyframe.getHeight()
          && m.getStartingKeyframe().getHeight() != m.getEndingKeyframe().getHeight()));
    }
    return false;
  }


  /**
   * Gets the starting tick for this motion.
   *
   * @return the starting tick of this motion.
   */
  @Override
  public Keyframe getStartingKeyframe() {
    return new Keyframe(this.startingKeyframe.getTick(),
        this.startingKeyframe.getX(),
        this.startingKeyframe.getY(),
        this.startingKeyframe.getR(),
        this.startingKeyframe.getG(),
        this.startingKeyframe.getB(),
        this.startingKeyframe.getHeight(),
        this.startingKeyframe.getWidth());
  }

  /**
   * Gets the ending tick for this motion.
   *
   * @return the ending tick of this motion
   */
  @Override
  public Keyframe getEndingKeyframe() {
    return new Keyframe(this.endingKeyframe.getTick(),
        this.endingKeyframe.getX(),
        this.endingKeyframe.getY(),
        this.endingKeyframe.getR(),
        this.endingKeyframe.getG(),
        this.endingKeyframe.getB(),
        this.endingKeyframe.getHeight(),
        this.endingKeyframe.getWidth());
  }


  @Override
  public boolean isConsistent(IMotion other) {
    if (other == null) {
      throw new IllegalArgumentException("Provided motion cannot be null");
    }
    return this.endingKeyframe.getX() == other.getStartingKeyframe().getX()
        && this.endingKeyframe.getY() == other.getStartingKeyframe().getY()
        && this.endingKeyframe.getR() == other.getStartingKeyframe().getR()
        && this.endingKeyframe.getG() == other.getStartingKeyframe().getG()
        && this.endingKeyframe.getB() == other.getStartingKeyframe().getB()
        && this.endingKeyframe.getWidth() == other.getStartingKeyframe().getWidth()
        && this.endingKeyframe.getHeight() == other.getStartingKeyframe().getHeight();
  }

  @Override
  public Keyframe interpolateKeyframe(int tick) {

    int startingTick = this.startingKeyframe.getTick();
    int endingTick = this.endingKeyframe.getTick();

    int interpolatedX = interpolate(startingTick, endingTick, this.startingKeyframe.getX(),
        this.endingKeyframe.getX(), tick);
    int interpolatedY = interpolate(startingTick, endingTick, this.startingKeyframe.getY(),
        this.endingKeyframe.getY(), tick);
    int interpolatedWidth = interpolate(startingTick, endingTick, this.startingKeyframe.getWidth(),
        this.endingKeyframe.getWidth(), tick);
    int interpolatedHeight = interpolate(startingTick, endingTick,
        this.startingKeyframe.getHeight(), this.endingKeyframe.getHeight(), tick);
    int interpolatedR = interpolate(startingTick, endingTick, this.startingKeyframe.getR(),
        this.endingKeyframe.getR(), tick);
    int interpolatedG = interpolate(startingTick, endingTick, this.startingKeyframe.getG(),
        this.endingKeyframe.getG(), tick);
    int interpolatedB = interpolate(startingTick, endingTick, this.startingKeyframe.getB(),
        this.endingKeyframe.getB(), tick);

    return new Keyframe(tick,
        interpolatedX, interpolatedY,
        interpolatedR, interpolatedG, interpolatedB, interpolatedHeight, interpolatedWidth);

  }


  /**
   * Interpolates the "between" values of a given field based on the current tick and the starting
   * and ending ticks of the motion of a Shape.
   *
   * @param startTick   the starting tick of the motion
   * @param endTick     the ending tick of the motion
   * @param startValue  the starting value of the field in the motion
   * @param endValue    the ending value of the field in the motion
   * @param currentTick the tick we are on
   * @return the interpolated value of the field in the motion.
   */
  private int interpolate(int startTick, int endTick, int startValue, int endValue,
      int currentTick) {
    return (int) Math.round(startValue * ((double) (endTick - currentTick) / (endTick - startTick))
        + endValue * ((double) (currentTick - startTick) / (endTick - startTick)));
  }

  @Override
  public Motion mergeMotions(IMotion m) {
    return new Motion(this.startingKeyframe, m.getEndingKeyframe());
  }

}
