package cs3500.animator.model;

/**
 * Interface that contains methods that can be called on motions in an animation. Motions can either
 * have a starting and ending keyframe, or just one single keyframe where no change is happening.
 */
public interface IMotion {

  /**
   * Generate the text description of a motion that describes the changes in fields.
   *
   * @return a text description of a motion
   */
  String generateDescription();

  /**
   * Checks if a motion overlaps with another motion (it occurs within the same time frame, and
   * changes the same attribute).
   *
   * @param m the motion to check overlap with
   * @return true if the motions overlap, false otherwise
   * @throws IllegalArgumentException if the provided motion is null
   */
  boolean overlaps(IMotion m) throws IllegalArgumentException;

  /**
   * Get the starting keyframe of a motion.
   *
   * @return the starting keyframe of a motion
   */
  Keyframe getStartingKeyframe();

  /**
   * Get the ending keyframe of a motion.
   *
   * @return the ending keyframe of a motion
   */
  Keyframe getEndingKeyframe();

  /**
   * Checks if the starting state of this motion is the same as the starting state of the given
   * motion.
   *
   * @param other the motion to check consistency with
   * @return true if the given motion starts in the same state that this motion left off, false if
   *         it does not
   * @throws NullPointerException if the given motion is null.
   */
  boolean isConsistent(IMotion other);

  /**
   * Finds out the values of a motion at a particular tick, and returns a keyframe representing this
   * data.
   *
   * @param tick the tick to interpolate at
   * @return A keyframe containing interpolated fields at the given tick
   */
  Keyframe interpolateKeyframe(int tick);

  /**
   * Merge a motion with a given motion that provides an ending keyframe.
   *
   * @param m the motion to merge with that comes after this motion.
   * @return A new motion that has the starting keyframe of the motion this method was called on,
   *         and the ending keyframe of the provided motion.
   */
  Motion mergeMotions(IMotion m);
}
