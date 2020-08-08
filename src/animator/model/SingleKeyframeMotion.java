package cs3500.animator.model;

/**
 * Class to represent Motions with only one keyframe. These motions have no change in attributes
 * from the starting tick to the ending tick.
 */
public class SingleKeyframeMotion implements IMotion {


  private Keyframe singleKeyframe;

  /**
   * Constructs a SingleKeyframeMotion with the given keyframe.
   *
   * @param singleKeyframe the given keyframe in which to construct this SingleKeyframeMotion.
   */
  public SingleKeyframeMotion(Keyframe singleKeyframe) {
    this.singleKeyframe = singleKeyframe;
  }

  @Override
  public String generateDescription() {
    StringBuilder result = new StringBuilder();
    result.append(this.singleKeyframe.getTick())
        .append(" ")
        .append(this.singleKeyframe.getX())
        .append(" ")
        .append(this.singleKeyframe.getY())
        .append(" ")
        .append(this.singleKeyframe.getWidth())
        .append(" ")
        .append(this.singleKeyframe.getHeight())
        .append(" ")
        .append(this.singleKeyframe.getR())
        .append(" ")
        .append(this.singleKeyframe.getG())
        .append(" ")
        .append(this.singleKeyframe.getB())
        .append(" ")
        .append(this.singleKeyframe.getTick())
        .append(" ")
        .append(this.singleKeyframe.getX())
        .append(" ")
        .append(this.singleKeyframe.getY())
        .append(" ")
        .append(this.singleKeyframe.getWidth())
        .append(" ")
        .append(this.singleKeyframe.getHeight())
        .append(" ")
        .append(this.singleKeyframe.getR())
        .append(" ")
        .append(this.singleKeyframe.getG())
        .append(" ")
        .append(this.singleKeyframe.getB());

    return result.toString();
  }


  /**
   * Gets a copy of the single keyframe.
   * @return a copy of the single keyframe field
   */
  @Override
  public Keyframe getStartingKeyframe() {
    return copySingleKeyframe();
  }

  /**
   * Gets a copy of the single keyframe.
   * @return a copy of the single keyframe field
   */
  @Override
  public Keyframe getEndingKeyframe() {
    return copySingleKeyframe();
  }

  @Override
  public boolean isConsistent(IMotion other) {
    if (other == null) {
      throw new IllegalArgumentException("Provided motion cannot be null");
    }
    return this.singleKeyframe.getX() == other.getStartingKeyframe().getX()
        && this.singleKeyframe.getY() == other.getStartingKeyframe().getY()
        && this.singleKeyframe.getR() == other.getStartingKeyframe().getR()
        && this.singleKeyframe.getG() == other.getStartingKeyframe().getG()
        && this.singleKeyframe.getB() == other.getStartingKeyframe().getB()
        && this.singleKeyframe.getWidth() == other.getStartingKeyframe().getWidth()
        && this.singleKeyframe.getHeight() == other.getStartingKeyframe().getHeight();
  }

  @Override
  public Keyframe interpolateKeyframe(int tick) {
    return copySingleKeyframe();
  }


  @Override
  public Motion mergeMotions(IMotion m) {
    return new Motion(this.singleKeyframe, m.getEndingKeyframe());
  }

  @Override
  public boolean overlaps(IMotion m) {
    if (m == null) {
      throw new IllegalArgumentException("Provided motion cannot be null");
    }
    return false;
  }

  /**
   * Creates a copy of the single keyframe used to make this class compatible with IMotion methods.
   * @return a copy of the single keyframe for this motion.
   */
  private Keyframe copySingleKeyframe() {
    return new Keyframe(this.singleKeyframe.getTick(),
        this.singleKeyframe.getX(),
        this.singleKeyframe.getY(),
        this.singleKeyframe.getR(),
        this.singleKeyframe.getG(),
        this.singleKeyframe.getB(),
        this.singleKeyframe.getHeight(),
        this.singleKeyframe.getWidth());
  }
}
