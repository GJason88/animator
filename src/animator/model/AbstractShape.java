package cs3500.animator.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * Abstract class representing a shape that has a name and a list of motions that are applied to
 * it.
 */
public abstract class AbstractShape implements Shape {

  private final String name;

  private final List<IMotion> motions;

  /**
   * An abstract constructor for a shape that sets the shape's name and list of motions to whatever
   * the provided name and list of motions are.
   *
   * @param name    the name of the shape
   * @param motions the list of motions for this shape
   * @throws IllegalArgumentException if the given String name or list of motions is null.
   */
  public AbstractShape(String name, List<IMotion> motions) {
    if (name == null || motions == null) {
      throw new IllegalArgumentException("Arguments cannot be null");
    }
    // sorts the list of motions by starting tick
    motions.sort(
        Comparator.comparingInt(o -> o.getStartingKeyframe().getTick()));
    this.name = name;
    this.motions = motions;
  }

  // Getter methods

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public List<IMotion> getMotions() {
    return new ArrayList<>(this.motions);
  }

  @Override
  public void addMotion(IMotion m) throws IllegalArgumentException {
    if (m == null) {
      throw new IllegalArgumentException("motion cannot be null.");
    }
    if (this.motions.contains(m)) {
      throw new IllegalArgumentException("This shape already contains Motion m.");
    }
    this.motions.add(m);
    // sorts the list of motions by starting tick
    motions.sort(
        Comparator.comparingInt(o -> o.getStartingKeyframe().getTick()));
  }

  @Override
  public void removeMotion(IMotion m) throws IllegalArgumentException {
    if (m == null) {
      throw new IllegalArgumentException("motion cannot be null.");
    }
    if (!this.motions.contains(m)) {
      throw new IllegalArgumentException("Shape must contain the motion.");
    }
    this.motions.remove(m);
    // sorts the list of motions by starting tick
    motions.sort(
        Comparator.comparingInt(o -> o.getStartingKeyframe().getTick()));

  }

  /**
   * Method used in the creation of SVG files. Creates a map that links the name of a shape's
   * attribute with a bifunction. This bifunction takes in the name of a shape attribute and a
   * motion, which is uses to return a formatted string that is an SVG animation.
   *
   * @param tempo the tempo of the animation, used to calculate the duration and start time of each
   *              SVG motion
   * @return a Map for creating animations within an SVG file.
   */
  protected Map<String, BiFunction<String, IMotion, String>> animateMap(int tempo,
      int offsetX, int offsetY) {
    Map<String, BiFunction<String, IMotion, String>> animateMap = new HashMap<>();
    String toFormat = "<animate attributeType=\"xml\" begin=\"%sms\" dur=\"%sms\" "
        + "attributeName=\"%s\" from=\"%s\" to=\"%s\" fill=\"freeze\" />\n";

    animateMap.put("x", (attName, m) -> String.format(toFormat,
        m.getStartingKeyframe().getTick() * (1000 / tempo),
        (m.getEndingKeyframe().getTick() - m.getStartingKeyframe().getTick()) * (1000 / tempo),
        attName,
        m.getStartingKeyframe().getX() - offsetX, m.getEndingKeyframe().getX() - offsetX));
    animateMap.put("y", (attName, m) -> String.format(toFormat,
        m.getStartingKeyframe().getTick() * (1000 / tempo),
        (m.getEndingKeyframe().getTick() - m.getStartingKeyframe().getTick()) * (1000 / tempo),
        attName,
        m.getStartingKeyframe().getY() - offsetY, m.getEndingKeyframe().getY() - offsetY));
    animateMap.put("ellipseX", (attName, m) -> String.format(toFormat,
        m.getStartingKeyframe().getTick() * (1000 / tempo),
        (m.getEndingKeyframe().getTick() - m.getStartingKeyframe().getTick()) * (1000 / tempo),
        attName,
        m.getStartingKeyframe().getX() - offsetX + m.getStartingKeyframe().getWidth() / 2,
        m.getEndingKeyframe().getX() - offsetX + m.getEndingKeyframe().getWidth() / 2));
    animateMap.put("ellipseY", (attName, m) -> String.format(toFormat,
        m.getStartingKeyframe().getTick() * (1000 / tempo),
        (m.getEndingKeyframe().getTick() - m.getStartingKeyframe().getTick()) * (1000 / tempo),
        attName,
        m.getStartingKeyframe().getY() - offsetY + m.getStartingKeyframe().getHeight() / 2,
        m.getEndingKeyframe().getY() - offsetY + m.getEndingKeyframe().getHeight() / 2));
    animateMap.put("width", (attName, m) -> String.format(toFormat,
        m.getStartingKeyframe().getTick() * (1000 / tempo),
        (m.getEndingKeyframe().getTick() - m.getStartingKeyframe().getTick()) * (1000 / tempo),
        attName,
        m.getStartingKeyframe().getWidth(), m.getEndingKeyframe().getWidth()));
    animateMap.put("height", (attName, m) -> String.format(toFormat,
        m.getStartingKeyframe().getTick() * (1000 / tempo),
        (m.getEndingKeyframe().getTick() - m.getStartingKeyframe().getTick()) * (1000 / tempo),
        attName,
        m.getStartingKeyframe().getHeight(), m.getEndingKeyframe().getHeight()));
    animateMap.put("rWidth", (attName, m) -> String.format(toFormat,
        m.getStartingKeyframe().getTick() * (1000 / tempo),
        (m.getEndingKeyframe().getTick() - m.getStartingKeyframe().getTick()) * (1000 / tempo),
        attName,
        m.getStartingKeyframe().getHeight() / 2, m.getEndingKeyframe().getHeight() / 2));
    animateMap.put("rHeight", (attName, m) -> String.format(toFormat,
        m.getStartingKeyframe().getTick() * (1000 / tempo),
        (m.getEndingKeyframe().getTick() - m.getStartingKeyframe().getTick()) * (1000 / tempo),
        attName,
        m.getStartingKeyframe().getHeight() / 2, m.getEndingKeyframe().getHeight() / 2));
    animateMap.put("color", (attName, m) -> String.format(toFormat,
        m.getStartingKeyframe().getTick() * (1000 / tempo),
        (m.getEndingKeyframe().getTick() - m.getStartingKeyframe().getTick()) * (1000 / tempo),
        attName,
        "rgb(" + m.getStartingKeyframe().getR() + "," + m.getStartingKeyframe().getG() + "," + m
            .getStartingKeyframe().getB() + ")",
        "rgb(" + m.getEndingKeyframe().getR() + "," + m.getEndingKeyframe().getG() + "," + m
            .getEndingKeyframe().getB() + ")"));
    animateMap.put("show", (attName, m) -> String.format(toFormat,
        m.getStartingKeyframe().getTick() * (1000 / tempo), "1", attName,
        "0", "1"));
    return animateMap;
  }

  @Override
  public List<Keyframe> getKeyframes() {

    List<Keyframe> result = new ArrayList<>();
    for (int i = 0; i < this.motions.size(); i++) {
      if (this.motions.get(i).getStartingKeyframe().getTick() != this.motions.get(i)
          .getEndingKeyframe().getTick()) {
        result.add(this.motions.get(i).getStartingKeyframe());
      }
      if (i == motions.size() - 1) {
        result.add(this.motions.get(i).getEndingKeyframe());
      }
    }
    return result;
  }

  @Override
  public void addKeyframe(int tick) {

    Keyframe interpolatedKeyframe;
    IMotion toRemove = null;
    List<IMotion> toAdd = new ArrayList<>();

    // If there are no keyframes, add a single keyframe motion in.
    if (this.motions.size() == 0 && tick >= 0) {
      addMotion(new SingleKeyframeMotion(new Keyframe(tick, 0, 0, 0, 0, 0, 0, 0)));
    }

    // If you add a keyframe before the first keyframe, then set that new keyframe's value to the
    // starting keyframe's start value.
    else if (this.motions.size() > 0 && tick >= 0) {

      Keyframe firstKeyframe = motions.get(0).getStartingKeyframe();
      Keyframe lastKeyframe = this.motions.get(this.motions.size() - 1).getEndingKeyframe();

      if (tick < firstKeyframe.getTick()) {
        addMotion(new Motion(new Keyframe(tick,
            firstKeyframe.getX(), firstKeyframe.getY(),
            firstKeyframe.getR(), firstKeyframe.getG(), firstKeyframe.getB(),
            firstKeyframe.getHeight(), firstKeyframe.getWidth()),
            firstKeyframe));
        return;
      } else if (tick > lastKeyframe.getTick()) {
        addMotion(new Motion(lastKeyframe,
            new Keyframe(tick, lastKeyframe.getX(), lastKeyframe.getY(),
                lastKeyframe.getR(), lastKeyframe.getG(), lastKeyframe.getB(),
                lastKeyframe.getHeight(), lastKeyframe.getWidth())));
        return;
      }

      for (IMotion m : this.motions) {
        if (tick == m.getStartingKeyframe().getTick() || tick == m.getEndingKeyframe().getTick()) {
          throw new IllegalArgumentException("There is already a keyframe at this tick");
        }
        interpolatedKeyframe = m.interpolateKeyframe(tick);

        if (tick > m.getStartingKeyframe().getTick() && tick < m.getEndingKeyframe().getTick()) {

          toAdd.add(new Motion(m.getStartingKeyframe(), interpolatedKeyframe));
          toAdd.add(new Motion(interpolatedKeyframe, m.getEndingKeyframe()));
          toRemove = m;
          break;
        }
      }

      for (IMotion m : toAdd) {
        addMotion(m);
      }
      // Remove the old motion, which sorts the list of motions
      if (toRemove != null) {
        this.motions.remove(toRemove);
      }
    }

  }


  @Override
  public void editKeyframe(int tick, Keyframe newKeyframe) {
    List<IMotion> toRemove = new ArrayList<>();
    List<IMotion> toAdd = new ArrayList<>();

    // If we find a motion that has this keyframe, create a new motion to replace it.
    for (IMotion m : this.motions) {
      if (m.getStartingKeyframe().getTick() == tick) {
        toRemove.add(m);
        toAdd.add(new Motion(newKeyframe, m.getEndingKeyframe()));
      } else if (m.getEndingKeyframe().getTick() == tick) {
        toRemove.add(m);
        toAdd.add(new Motion(m.getStartingKeyframe(), newKeyframe));
      }
    }
    // Once the for loop has completed, add and remove motions, then sort them.
    for (IMotion m : toAdd) {
      addMotion(m);
    }
    for (IMotion m : toRemove) {
      removeMotion(m);
    }
  }

  @Override
  public void deleteKeyframe(int tick) {
    if (getKeyframes().size() == 1) {
      this.motions.clear();
      return;
    }

    IMotion motionBefore = null;
    IMotion motionAfter = null;
    List<IMotion> motionsToRemove = new ArrayList<>();

    // Find which motion of this shape ends on that tick
    for (IMotion m : this.motions) {
      if (m.getEndingKeyframe().getTick() == tick && m.getStartingKeyframe().getTick() != m
          .getEndingKeyframe().getTick()) {
        motionBefore = m;
        motionsToRemove.add(m);
        break;

      } else if (m.getEndingKeyframe().getTick() == tick && m.getStartingKeyframe().getTick() == m
          .getEndingKeyframe().getTick()) {
        motionsToRemove.add(m);
      }
    }
    for (IMotion m : this.motions) {
      if (m.getStartingKeyframe().getTick() == tick && m.getStartingKeyframe().getTick() != m
          .getEndingKeyframe().getTick()) {

        motionAfter = m;
        motionsToRemove.add(m);
        break;

      } else if (m.getEndingKeyframe().getTick() == tick && m.getStartingKeyframe().getTick() == m
          .getEndingKeyframe().getTick() && !motionsToRemove.contains(m)) {
        motionsToRemove.add(m);
      }
    }

    // Take out the two motions that touched this keyframe
    removeMotions(motionsToRemove);

    // The basic case of removal. The keyframe is in the middle of other keyframes
    if (motionBefore != null && motionAfter != null) {
      Motion motionToAdd = motionBefore.mergeMotions(motionAfter);
      addMotion(motionToAdd);
    } else if (motionBefore != null) {
      addMotion(new SingleKeyframeMotion(motionBefore.getStartingKeyframe()));
    } else if (motionAfter != null) {
      addMotion(new SingleKeyframeMotion(motionAfter.getEndingKeyframe()));
    }

    this.motions.sort(Comparator.comparingInt(o -> o.getEndingKeyframe().getTick()));
  }

  /**
   * Removes a list of motions from the motions for this shape.
   *
   * @param motionsToRemove the list of motions to remove.
   */
  private void removeMotions(List<IMotion> motionsToRemove) {
    if (motionsToRemove == null) {
      throw new IllegalArgumentException("Provided list of motions must be non null");
    }
    for (IMotion m : motionsToRemove) {
      if (this.motions.contains(m)) {
        removeMotion(m);
      }
    }
  }

}
