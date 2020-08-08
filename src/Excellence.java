/**
 * An interface that is used to get information about an animation.
 */
public interface Excellence {

  /**
   * Provide a text description of an animation. The output text groups motions by the shape they
   * are occurring on. The text log of each motion includes the name of the shape being moved, as
   * well as the starting and ending states of that shape.
   *
   * <p>ex.)
   * shape rect A Rectangle
   * motion rect A 0 5 0 30 20 255 0 0   5 5 1 30 20 255 0 0
   * motion rect A 5 5 1 30 20 255 0 0   10 5 3 30 20 255 0 0
   * motion rect A 12 5 3 30 20 255 0 0   20 5 2 30 20 255 0 0</p>
   *
   * @return a string documenting all of the actions done within an animation.
   */
  public String getTextDescription();

  // TODO startAnimation (or is that part of the controller)

}
