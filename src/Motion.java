import java.util.Objects;

/**
 * Class for representing a motion, which is a single motion for changing the state of a given
 * startingShape to endingState from the startingTick to endingTick tick frame.
 */
public class Motion {

  private final Shape startingShape;
  private final Shape endingState;
  private final int startingTick;
  private final int endingTick;

  /**
   * Creates a motion that a shape carries out.
   * Invariant - Ending tick is always greater than the starting tick. Enforced by constructor and
   * tick fields are final so they can't be changed after being set.
   *
   * @param start        the starting shape to apply the motion to.
   * @param end          the ending state that the shape should become
   * @param startingTick the tick this motion starts on
   * @param endingTick   the tick this motion ends on
   * @throws IllegalArgumentException if the starting tick is not greater than the ending tick
   * @throws NullPointerException     if the provided starting or ending shapes are null
   */
  public Motion(Shape start, Shape end, int startingTick, int endingTick) {
    Objects.requireNonNull(start);
    Objects.requireNonNull(end);
    if (endingTick <= startingTick) {
      throw new IllegalArgumentException("Starting tick must occur first.");
    }
    this.startingShape = start;
    this.endingState = end;
    this.startingTick = startingTick;
    this.endingTick = endingTick;
  }

  /**
   * Creates a text description of a motion. Includes the information on the starting shape and
   * tick, and the ending state and tick.
   *
   * @return a string documenting the changes that occur to shapes within a motion.
   */
  public String generateDescription() {
    StringBuilder result = new StringBuilder();
    result.append("motion ")
        .append(startingShape.getName())
        .append(" ")
        .append(this.startingTick)
        .append(" ")
        .append(this.startingShape.toString())

        .append("   ")

        .append(this.endingTick)
        .append(" ")
        .append(this.endingState.toString());
    return result.toString();
  }


  /**
   * Retrieves the starting shape of this motion.
   *
   * @return the starting shape of this animation
   */
  public Shape getStartingShape() {
    return this.startingShape;
  }


  /**
   * Retrieves the ending state of this motion.
   *
   * @return the ending state of this animation
   */
  public Shape getEndingState() {
    return this.endingState;
  }

  /**
   * Checks if two motions overlap. This helper method is only called on two of the same shape, so
   * we don't check to see if the motions are occurring on the same shape here. Two motions overlap
   * if they occur at the same time, and are both changing the color, size, or position of the same
   * shape.
   *
   * @param m the motion to compare to.
   * @return true if the given motion overlaps with this motion, false otherwise.
   */
  public boolean overlaps(Motion m) {

    // Check if two motions overlap time-wise
    if ((m.getStartingTick() >= this.startingTick && m.getStartingTick() < this.endingTick)
        || (m.getEndingTick() > this.startingTick && m.getEndingTick() <= this.endingTick)) {

      return (!(this.startingShape.getColor().equals(this.endingState.getColor())) && !(m
          .getStartingShape().getColor().equals(m.getEndingState().getColor())))
          || (this.startingShape.getX() != this.endingState.getX()
          && m.getStartingShape().getX() != m.getEndingState().getX())
          || (this.startingShape.getY() != this.endingState.getY()
          && m.getStartingShape().getY() != m.getEndingState().getY())
          || (this.startingShape.getWidth() != this.endingState.getWidth()
          && m.getStartingShape().getWidth() != m.getEndingState().getWidth())
          || (this.startingShape.getHeight() != this.endingState.getHeight()
          && m.getStartingShape().getHeight() != m.getEndingState().getHeight());
    }
    return false;
  }


  /**
   * Gets the starting tick for this motion.
   * @return the starting tick of this motion.
   */
  public int getStartingTick() {
    return this.startingTick;
  }

  /**
   * Gets the ending tick for this motion.
   * @return the ending tick of this motion
   */
  public int getEndingTick() {
    return this.endingTick;
  }


}



