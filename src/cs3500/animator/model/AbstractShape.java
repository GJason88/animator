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

  private final List<Motion> motions;

  /**
   * An abstract constructor for a shape that sets the shape's name and list of motions to whatever
   * the provided name and list of motions are.
   *
   * @param name    the name of the shape
   * @param motions the list of motions for this shape
   * @throws IllegalArgumentException if the given String name or list of motions is null.
   */
  public AbstractShape(String name, List<Motion> motions) {
    if (name == null || motions == null) {
      throw new IllegalArgumentException("Arguments cannot be null");
    }
    // sorts the list of motions by starting tick
    motions.sort(Comparator.comparingInt(Motion::getStartingTick));
    this.name = name;
    this.motions = motions;
  }

  // Getter methods

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public List<Motion> getMotions() {
    return new ArrayList<>(this.motions);
  }

  @Override
  public void addMotion(Motion m) throws IllegalArgumentException {
    if (m == null) {
      throw new IllegalArgumentException("motion cannot be null.");
    }
    if (this.motions.contains(m)) {
      throw new IllegalArgumentException("This shape already contains Motion m.");
    }
    this.motions.add(m);
    motions.sort(Comparator.comparingInt(Motion::getStartingTick));
  }

  @Override
  public void removeMotion(Motion m) throws IllegalArgumentException {
    if (m == null) {
      throw new IllegalArgumentException("motion cannot be null.");
    }
    if (!this.motions.contains(m)) {
      throw new IllegalArgumentException("Shape must contain the motion.");
    }
    this.motions.remove(m);
    motions.sort(Comparator.comparingInt(Motion::getStartingTick));

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
  protected Map<String, BiFunction<String, Motion, String>> animateMap(int tempo,
      int offsetX, int offsetY) {
    Map<String, BiFunction<String, Motion, String>> animateMap = new HashMap<>();
    String toFormat = "<animate attributeType=\"xml\" begin=\"%sms\" dur=\"%sms\" "
        + "attributeName=\"%s\" from=\"%s\" to=\"%s\" fill=\"freeze\" />\n";

    animateMap.put("x", (attName, m) -> String.format(toFormat,
        m.getStartingTick() * (1000 / tempo),
        (m.getEndingTick() - m.getStartingTick()) * (1000 / tempo), attName,
        m.getStartX() - offsetX, m.getEndX() - offsetX));
    animateMap.put("y", (attName, m) -> String.format(toFormat,
        m.getStartingTick() * (1000 / tempo),
        (m.getEndingTick() - m.getStartingTick()) * (1000 / tempo), attName,
        m.getStartY() - offsetY, m.getEndY() - offsetY));
    animateMap.put("ellipseX", (attName, m) -> String.format(toFormat,
        m.getStartingTick() * (1000 / tempo),
        (m.getEndingTick() - m.getStartingTick()) * (1000 / tempo), attName,
        m.getStartX() - offsetX + m.getStartWidth() / 2,
        m.getEndX() - offsetX + m.getEndWidth() / 2));
    animateMap.put("ellipseY", (attName, m) -> String.format(toFormat,
        m.getStartingTick() * (1000 / tempo),
        (m.getEndingTick() - m.getStartingTick()) * (1000 / tempo), attName,
        m.getStartY() - offsetY + m.getStartHeight() / 2,
        m.getEndY() - offsetY + m.getEndHeight() / 2));
    animateMap.put("width", (attName, m) -> String.format(toFormat,
        m.getStartingTick() * (1000 / tempo),
        (m.getEndingTick() - m.getStartingTick()) * (1000 / tempo), attName,
        m.getStartWidth(), m.getEndWidth()));
    animateMap.put("height", (attName, m) -> String.format(toFormat,
        m.getStartingTick() * (1000 / tempo),
        (m.getEndingTick() - m.getStartingTick()) * (1000 / tempo), attName,
        m.getStartHeight(), m.getEndHeight()));
    animateMap.put("rWidth", (attName, m) -> String.format(toFormat,
        m.getStartingTick() * (1000 / tempo),
        (m.getEndingTick() - m.getStartingTick()) * (1000 / tempo), attName,
        m.getStartHeight() / 2, m.getEndHeight() / 2));
    animateMap.put("rHeight", (attName, m) -> String.format(toFormat,
        m.getStartingTick() * (1000 / tempo),
        (m.getEndingTick() - m.getStartingTick()) * (1000 / tempo), attName,
        m.getStartHeight() / 2, m.getEndHeight() / 2));
    animateMap.put("color", (attName, m) -> String.format(toFormat,
        m.getStartingTick() * (1000 / tempo),
        (m.getEndingTick() - m.getStartingTick()) * (1000 / tempo), attName,
        "rgb(" + m.getStartR() + "," + m.getStartG() + "," + m.getStartB() + ")",
        "rgb(" + m.getEndR() + "," + m.getEndG() + "," + m.getEndB() + ")"));
    animateMap.put("show", (attName, m) -> String.format(toFormat,
        m.getStartingTick() * (1000 / tempo), "1", attName,
        "0", "1"));
    return animateMap;
  }
}
